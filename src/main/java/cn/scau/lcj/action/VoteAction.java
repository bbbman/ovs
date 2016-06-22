package cn.scau.lcj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.scau.common.ClientIp;
import cn.scau.common.DateUtil;
import cn.scau.common.ObjectUtil;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.OtherOption;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.RequestRecord;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.service.TitleService;
import cn.scau.lcj.service.OptionService;
import cn.scau.lcj.service.ImageService;
import cn.scau.lcj.service.RequestRecordService;
public class VoteAction extends ExtendSupport {
	
	private Integer pageId;
	
	private String requestParam;
	
	private String ip;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private TitleService titleService;
	
	@Autowired
	private OptionService optionService;
	@Autowired
	private ImageService ImageService;
	@Autowired
	private RequestRecordService requestRecordService;
	
	public void dealData(){
		Log.log("收集处理投票信息开始");
		JSONObject returnResult = new JSONObject();		
		try{									
			JSONObject paramJSON = JSONObject.parseObject(requestParam);
			Integer pageSeq = paramJSON.getInteger("pageSeq");//页id	
			JSONArray otherOptionArr = new JSONArray();
			Integer otherOptionSeq=null;
			boolean is_submitData = false;			
			//找到对应页面
			Page page = pageService.selectPageByPrimaryKey(pageSeq);
			if(page ==null){
				setReturnJson(returnResult,2,1,"该页投票已过时或不存在","");
				responseJson(returnResult);
				return;
			}
			if(!isCanVote(page)){
				//不能投票
				setReturnJson(returnResult,3,0,"您已投过该票","");
				responseJson(returnResult);
				Log.log("收集处理投票信息结束");
				return;
			}						
			JSONArray  titleList = paramJSON.getJSONArray("titleList");//页列表
			if(titleList.size()==0){
				setReturnJson(returnResult,2,1,"请选择投票项","");
				responseJson(returnResult);
				Log.log("收集处理投票信息结束");
				return;
			}
			for(int i = 0;i<titleList.size();i++){
				//找到该页下的对应title
				JSONObject titleJSON = titleList.getJSONObject(i);
				Integer titleSeq  =titleJSON.getInteger("titleSeq");
				Title title = titleService.selectTitleBySeq(pageSeq, titleSeq);
				if(title ==null){
					continue;//该项不存在算是没选择
//					setReturnJson(returnResult,2,1,"该页不存在该项","");
//					responseJson(returnResult);
//					return;
				}
				//获取该title下的被选择的对应的项											
				JSONArray  optionList = titleJSON.getJSONArray("optionList");
				Integer optionArray[] = new Integer[optionList.size()];
				for(int k=0;k < optionList.size() ;k++)
					optionArray[k] = optionList.getInteger(k);
							
				if(title.getTitleType()==0){
					//该标题只能单选
					if(optionArray.length !=0){
						//单选，选择一般项
						Integer singleOptionSeq = optionArray[0];
						Option option = optionService.selectOptionByTitleSeq(titleSeq, singleOptionSeq);
						if(option !=null){
							option.setOptionSelectTimes(option.getOptionSelectTimes()+1);
							title.setTitleSelectTimes(title.getTitleSelectTimes()+1);
							optionService.updateOptionTimesByObject(option);
							titleService.updateTitleByObject(title);
							is_submitData=true;//有数据更新，说明该页提交有用数据
							continue;
						}
						//找不到该单选项，算是没有投
						continue;
					}
					if(optionArray.length==0 && title.getOtherOption()==1 ){
						//单选，启用其他项，选择其他项
						String otherContent = titleJSON.getString("otherContent");
						if(StringUtil.isEmpty(otherContent)){
							continue;//其他项不存在算是没选择
						}
						//插入其他项
						OtherOption otherOption = new OtherOption(title.getTitleId(),otherContent);
						title.setOtherOptionSelectTimes(title.getOtherOptionSelectTimes()+1);
						title.setTitleSelectTimes(title.getTitleSelectTimes()+1);
						otherOptionSeq = optionService.saveOtherOption(otherOption);
						otherOptionArr.add(otherOptionSeq);
						titleService.updateTitleByObject(title);
						is_submitData=true;//有数据更新，说明该页提交有用数据
						continue;						
					}					
				}else if(title.getTitleType()==1){
					//多项选择
					if(optionArray.length !=0){
						//多项选择非其他选项
						//更新其他项的次数
						optionService.updateOptionTimesByArray(titleSeq, optionArray);
						is_submitData=true;//有数据更新，说明该页提交有用数据
					}
					if(title.getOtherOption()!=1){
						//没选择其他项，不处理
						if(optionArray.length !=0)
							title.setTitleSelectTimes(title.getTitleSelectTimes()+1);
						continue;
					}					
					//多项中有其他项
					String otherContent = titleJSON.getString("otherContent");
					if(StringUtil.isEmpty(otherContent)){
						if(optionArray.length !=0)
							title.setTitleSelectTimes(title.getTitleSelectTimes()+1);
						continue;//其他项不存在算是没选择
					}
					OtherOption otherOption = new OtherOption(title.getTitleId(),otherContent);
					title.setOtherOptionSelectTimes(title.getOtherOptionSelectTimes()+1);
					title.setTitleSelectTimes(title.getTitleSelectTimes()+1);
					otherOptionSeq = optionService.saveOtherOption(otherOption);
					otherOptionArr.add(otherOptionSeq);
					titleService.updateTitleByObject(title);
					is_submitData=true;//有数据更新，说明该页提交有用数据
				}			
			}
			
			if(is_submitData){
				//有数据更新
				page.setSubmitTimes(page.getSubmitTimes()+1);
				pageService.updatePage(page);
			}
			//设置返回值
			//根据页面的信息，是否添加data信息
			setReturnJson(returnResult,1,0,"投票成功","");
			//投票成功，保存投票记录
			RequestRecord requestRecord = new RequestRecord();
			requestRecord.setRequestContent(requestParam);
			requestRecord.setRequestDate(DateUtil.getCurTimestamp());
			requestRecord.setRequestIp(ip);
			requestRecord.setRequestPageId(page.getPageId());
			if(!otherOptionArr.isEmpty())
			   requestRecord.setOtherOptionIds(otherOptionArr.toJSONString());
			requestRecordService.saveRequestRecord(requestRecord);			
		}catch(Exception e){
			e.printStackTrace();
			setReturnJson(returnResult,2,1,"投票失败","");
		}
		responseJson(returnResult);		
		Log.log("收集处理投票信息结束");
		return;
	}
	//获取投票结果
	public void getVoteResult(){
		Log.log("获取投票结果开始:");
		JSONObject json = new JSONObject();
		JSONObject voteResult = new JSONObject();
		Page page = pageService.selectPageByPrimaryKey(pageId);
		if(page==null){
			setReturnJson(json,2,1,"该投票不存在或已结束","");
			responseJson(json);
			Log.log("获取投票结果结束");
			return;
		}
		
		if(page.getSeeAfterVote()!=0){
			//投票后再显示结果
			if(isCanVote(page)){
				//可以投票,则先投票后再显示内容
				setReturnJson(json,4,0,"先投票后显示结果",null);
				responseJson(json);
				return;
			}
		}
		
		voteResult.put("pageBrowseTimes", page.getBrowseTimes());
		voteResult.put("pageSubmitTimes", page.getSubmitTimes());
		List<Title> titleList = titleService.selectByPageSeq(pageId);
		JSONArray titleArr = new JSONArray();
		Integer totalTitleSelectTimes = 0;
		if(!ObjectUtil.isEmptyList(titleList)){
			for(int i=0 ; i<titleList.size();i++){
				JSONObject titleJson = new JSONObject();
				Integer totalOptionSelectTimes = 0;
				Title title = titleList.get(i);
				titleJson.put("titleSelectTimes", title.getTitleSelectTimes());
				titleJson.put("otherOptionSelectTimes", title.getOtherOptionSelectTimes());	
				totalTitleSelectTimes +=title.getTitleSelectTimes();
				totalOptionSelectTimes +=title.getOtherOptionSelectTimes();
				List<Option> optionList = optionService.selectByTitleSeq(title.getTitleId());				
				if(!ObjectUtil.isEmptyList(optionList)){
					JSONArray optionArr = new JSONArray();
					for(int j=0; j<optionList.size();j++){
						Option option = optionList.get(j);
						optionArr.add(option.getOptionSelectTimes());
						totalOptionSelectTimes +=option.getOptionSelectTimes();
					}
					titleJson.put("optionList", optionArr);
					titleJson.put("optionSelectTimes", totalOptionSelectTimes);
				}
				titleArr.add(titleJson);
			}			
		}
		voteResult.put("titleList", titleArr);
		voteResult.put("totalTitleSelectTimes", totalTitleSelectTimes);
		if(!isCanVote(page)){
			setReturnJson(json,3,0,"成功获取投票结果",voteResult);
			responseJson(json);
			Log.log("获取投票结果结束");
			return;
		}
		setReturnJson(json,1,0,"成功获取投票结果",voteResult);
		responseJson(json);
		Log.log("获取投票结果结束");
	}
	//判断该ip能否投票
	private boolean isCanVote(Page page){				
		try {
			//验证是否已经投过票，暂时只能根据公网ip，获取子网的ip可能在报文中
			ip = ClientIp.getIpAddr(request);
			RequestRecord requestRecord = requestRecordService.selectByPageIdAndIp(page.getPageId(), ip);
			if(requestRecord==null)
				return true;
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}
	
	//记录投票者的信息，主要是ip
	private void recordVote(String ip,Integer pageId,String requestParam){
		//String ip = request.get
	}
	public static void main(String[] args){
		JSONObject json = new JSONObject();
		JSONArray titleList =  new JSONArray();
		JSONArray optionList = new JSONArray();
		JSONObject title = new JSONObject();
		optionList.add(1);
		optionList.add(2);
		optionList.add(3);
		title.put("titleSeq", 1);
		title.put("optionSeq", optionList);
		title.put("otherContent", "haha");
		titleList.add(title);
		json.put("pageSeq", 1);
		json.put("titleList", titleList);
		System.out.println(json);
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
}
