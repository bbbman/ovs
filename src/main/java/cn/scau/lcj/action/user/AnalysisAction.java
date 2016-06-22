package cn.scau.lcj.action.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.common.DateUtil;
import cn.scau.common.ObjectUtil;
import cn.scau.common.StringUtil;
import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.RequestRecord;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.OptionService;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.service.RequestRecordService;
import cn.scau.lcj.service.TitleService;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.scau.lcj.service.AnalysisService;

public class AnalysisAction extends ExtendSupport {
	
	private Integer pageSeq;
	
	private Integer parentSeq;
	
	private Integer dataType;
	
	private Integer dataSeq;
	
	@Autowired
	private PageService pageService;
	@Autowired
	private TitleService titleService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private RequestRecordService requestRecordService;
	@Autowired
	private AnalysisService analysisService;
	
	public String execute(){
		request.setAttribute("pageSeq", pageSeq);
		return SUCCESS;
	}
	
	public void getTree(){
		Log.log("获取tree数据");
		JSONObject json = new JSONObject();
		JSONArray tree = new JSONArray();
		if(pageSeq==null){
			setReturnJson(json,2,1,"该网页不存在","");
			responseJson(json);
			Log.log("获取tree数据结束");
			return ;
		}
		Page page=pageService.selectPageByPrimaryKey(pageSeq);
		if(page==null){
			setReturnJson(json,2,1,"该网页不存在","");
			responseJson(json);
			Log.log("获取tree数据结束");
			return;
		}
		JSONObject root = new JSONObject();
		root.put("id", page.getPageId());
		root.put("pId", 0);
		root.put("name", "无标题");
		if(StringUtil.isEmpty(page.getMainTitle()))
			setTreeContent(root,page.getMainTitle());
		root.put("open", true);
		tree.add(root);
		List<Title> secendTrees = titleService.selectByPageSeq(pageSeq);
		if(!ObjectUtil.isEmptyList(secendTrees)){
			for(int i=0;i<secendTrees.size();i++){
				Title title = secendTrees.get(i);
				JSONObject secondTree = new JSONObject();
				secondTree.put("id", title.getTitleId());
				secondTree.put("pId", pageSeq);
				setTreeContent(secondTree,title.getTitleContent());
				tree.add(secondTree);
				List<Option> thirdTrees = optionService.selectByTitleSeq(title.getTitleId());
				if(!ObjectUtil.isEmptyList(thirdTrees)){
					for(int j=0;j<thirdTrees.size();j++){
						Option option = thirdTrees.get(j);
						JSONObject thirdTree = new JSONObject();
						thirdTree.put("id", option.getOptionId());
						thirdTree.put("pId", option.getTitleId());
						setTreeContent(thirdTree,option.getOptionContent());						
						tree.add(thirdTree);
					}		
					if(title.getOtherOption()==1){
						JSONObject thirdTree = new JSONObject();
						thirdTree.put("id", -1);
						thirdTree.put("pId", title.getTitleId());
						thirdTree.put("name", "其他");
						tree.add(thirdTree);
					}
				}
			}
		}
		setReturnJson(json,1,0,"获取tree成功",tree);
		responseJson(json);
		Log.log("获取tree数据结束");
		return ;
	}		
	
	public void getRecData(){
		Log.log("分析开始:");
		JSONObject json = new JSONObject();
		JSONObject result = new JSONObject();
		if(pageSeq==null){
			setReturnJson(json,2,1,"该网页不存在","");
			responseJson(json);
			Log.log("分析结束");
			return ;
		}
		Page page=pageService.selectPageByPrimaryKey(pageSeq);
		if(page==null){
			setReturnJson(json,2,1,"该网页不存在","");
			responseJson(json);
			Log.log("分析结束");
			return;
		}
		result.put("text", page.getMainTitle());
		result.put("url", page.getUrl());			
		List<Title> titleList = titleService.selectByPageSeq(pageSeq);
		
		JSONArray titleSeriesData = new JSONArray();
		JSONArray optionSeries = new JSONArray();
		for(int i=0; i<titleList.size();i++){
			Title title = titleList.get(i);
			JSONObject titleData = new JSONObject();
			titleData.put("name", title.getTitleContent());
			titleData.put("y", title.getTitleSelectTimes());
			titleData.put("drilldown", title.getTitleId());
			
			
			JSONObject optionJson = new JSONObject();			
			JSONArray data = new JSONArray();									
			optionJson.put("name", title.getTitleContent());
			optionJson.put("id", title.getTitleId());
			
			List<Option> optionList = optionService.selectByTitleSeq(title.getTitleId());
			for(int j=0; j<optionList.size(); j++){
				Option option = optionList.get(j);
				JSONArray optionData =new JSONArray();
				optionData.add(option.getOptionContent());
				optionData.add(option.getOptionSelectTimes());
				data.add(optionData);				
			}
			if(title.getOtherOption()==1){
				JSONArray otherOptionData =new JSONArray();
				otherOptionData.add("其他");
				otherOptionData.add(title.getOtherOptionSelectTimes());
				data.add(otherOptionData);
			}			
			titleSeriesData.add(titleData);				
			optionJson.put("data", data);
			optionSeries.add(optionJson);
		}			
		result.put("seriesData", titleSeriesData);
		result.put("drilldown", optionSeries);
		setReturnJson(json,1,0,"获取数据成功",result);
		responseJson(json);
		Log.log("分析结束"+json.toString());
	}
	/**
	 * parentSeq:父id
	 * dataType:0为按日。1位按时
	 * dataSeq:本身id
	 * 如果parentSeq为pageSeq，则是投票项
	 * 如果parentSeq为0，则是该page的所有投票项
	 * 否则，为投票子项
	 */
	public void getLineData(){
		Log.log("line分析开始:");
		JSONObject result = new JSONObject();
		Integer choseType = 0;//默认是返回该page下的所有title
		if(dataType==null)
			dataType=0;//默认是按日
		if(parentSeq==null||parentSeq==0)
			choseType=0;//选择该pageSeq下的所有title
		else if(parentSeq==pageSeq)
			choseType=1;//选择title=dataSeq下的所有option
		else{			
		}	
		JSONObject lineData =null;
		Page page = pageService.selectPageByPrimaryKey(pageSeq);
		switch(choseType){
		case 0 :lineData = analysisService.getTitleAnalis(dataType, page);break;
		case 1 :Title title = titleService.selectTitleBySeq(pageSeq, dataSeq);
		        lineData =analysisService.getOptionAnalis(dataType, page, title);break;
		default:break;
		}
		setReturnJson(result,1,0,"获取成功",lineData);
		responseJson(result);
		Log.log("line分析结束");
	}	
	
	public void setTreeContent(JSONObject treeNode,String name){
		if(StringUtil.isEmpty(name))
			name="未知";
		if(name.length()>=10){
			name = name.substring(0, 10);
			name =name+"...";
		}			
		treeNode.put("name", name);
	}
	
	public void getTitleByPageSeq(Page page){
		List<Title> titleList = titleService.selectByPageSeq(page.getPageId());
		Timestamp startTime = page.getBuildTime();
		Timestamp deadLine = page.getDeadLine();
		if(deadLine == null)
			deadLine = DateUtil.getCurTimestamp();		
		List<RequestRecord> recordList = requestRecordService.selectByPageIdAndDeadLine(page.getPageId(),StringUtil.Timestamp2String(deadLine));
		//获取时间刻度				
		List<Timestamp> dayPeriod =  DateUtil.getDayPeriod(startTime, deadLine);
		List<String> categories = StringUtil.getMD_TimeStrList(dayPeriod);
		
	}
	
	public Integer getPageSeq() {
		return pageSeq;
	}
	public void setPageSeq(Integer pageSeq) {
		this.pageSeq = pageSeq;
	}

	public Integer getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(Integer parentSeq) {
		this.parentSeq = parentSeq;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getDataSeq() {
		return dataSeq;
	}

	public void setDataSeq(Integer dataSeq) {
		this.dataSeq = dataSeq;
	}		
	
}
