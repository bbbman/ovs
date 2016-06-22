package cn.scau.lcj.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.scau.common.DateUtil;
import cn.scau.common.ObjectUtil;
import cn.scau.common.StringUtil;
import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.RequestRecord;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.AnalysisService;
import cn.scau.lcj.service.OptionService;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.service.TitleService;
import cn.scau.lcj.service.RequestRecordService;
@Service("AnalysisService")
public class AnalysisServiceImpl implements AnalysisService {

	@Autowired
	private TitleService titleService;
	@Autowired
	private RequestRecordService requestRecordService;
	@Autowired
	private OptionService optionService;

	@Override
	public JSONObject getTitleAnalis(Integer timeType,Page page) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		Timestamp deadLine = page.getDeadLine();
		if(deadLine==null)
			deadLine = DateUtil.getCurTimestamp();		
		List<Title> titleList = titleService.selectByPageSeq(page.getPageId());
		List<RequestRecord> records = requestRecordService.selectByPageIdAndDeadLine(page.getPageId(), StringUtil.Timestamp2String(deadLine));
		List<String> timePeriod = getTimePeriod(timeType,page.getBuildTime(),deadLine);		
		Map<Integer,Map<String,Integer>> map = getTitleData(timeType,records);
		result.put("categories", timePeriod);
		result.put("series", dealTitlePeriod(map,titleList,timePeriod));
		if(StringUtil.isEmpty( page.getMainTitle()))
		    result.put("text", "无标题");
		else
			result.put("text", page.getMainTitle());			
		return result;
	}
	
	@Override
	public JSONObject getOptionAnalis(Integer timeType, Page page, Title title) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		Timestamp deadLine = page.getDeadLine();
		if(deadLine==null)
			deadLine = DateUtil.getCurTimestamp();
		List<Option> optionList = optionService.selectByTitleSeq(title.getTitleId());
		List<String> timePeriod = getTimePeriod(timeType,page.getBuildTime(),deadLine);
		List<RequestRecord> records = requestRecordService.selectByPageIdAndDeadLine(page.getPageId(), StringUtil.Timestamp2String(deadLine));		
		Map<Integer,Map<String,Integer>> map = getOptionData(timeType,records,title);		
		result.put("categories", timePeriod);
		result.put("series", dealOptionPeriod(map,optionList,timePeriod,title));
		if(StringUtil.isEmpty( page.getMainTitle()))
		    result.put("text", "无标题");
		else
			result.put("text", page.getMainTitle());
		return result;
	}
			
	public List<String> getTimePeriod(Integer timeType,Timestamp startTime,Timestamp endTime){
		switch(timeType){
		case 0: return StringUtil.getYYYYMDStrList(DateUtil.getDayPeriod(startTime, endTime));
		default:return StringUtil.getYYYYMDHList(DateUtil.getHourPeriod(startTime, endTime));
		}
	}
	
	public Map<Integer,Map<String,Integer>> getTitleData(Integer timeType,List<RequestRecord> records){
		Map<Integer,Map<String,Integer>> map = new HashMap<Integer,Map<String,Integer>>();
		if(!ObjectUtil.isEmptyList(records)){
			for(int i=0;i<records.size();i++){
				RequestRecord requestRecord = records.get(i);
				String requestDate;
				if(timeType==0)
				     requestDate = StringUtil.getYYYYMD(requestRecord.getRequestDate());
				else
					 requestDate = StringUtil.getYYYYMDH(requestRecord.getRequestDate());
				JSONObject pageJSON = JSONObject.parseObject(requestRecord.getRequestContent());
				JSONArray titleArr = pageJSON.getJSONArray("titleList");
				if(!titleArr.isEmpty()){
					for(int j=0;j<titleArr.size();j++){
						JSONObject titleJSON = titleArr.getJSONObject(j);
						Integer titleSeq = titleJSON.getInteger("titleSeq");						
						if(map.containsKey(titleSeq)){
							Map<String,Integer> dateMap = map.get(titleSeq);
							Integer count = dateMap.get(requestDate);
							if(count==null)
								dateMap.put(requestDate, 1);
							else
								dateMap.put(requestDate, count+1);
						}else{
							Map<String,Integer> dateMap = new HashMap<String,Integer>();
							dateMap.put(requestDate, 1);
							map.put(titleSeq, dateMap);
						}
					}
				}
			}
		}
		return map;
	}
	
	public Map<Integer,Map<String,Integer>> getOptionData(Integer timeType,List<RequestRecord> records,Title title){
		Map<Integer,Map<String,Integer>> map = new HashMap<Integer,Map<String,Integer>>();
		if(!ObjectUtil.isEmptyList(records)){
			for(int i=0;i<records.size();i++){
				RequestRecord requestRecord = records.get(i);
				String requestDate;
				if(timeType==0)
				     requestDate = StringUtil.getYYYYMD(requestRecord.getRequestDate());
				else
					 requestDate = StringUtil.getYYYYMDH(requestRecord.getRequestDate());
				JSONObject pageJSON = JSONObject.parseObject(requestRecord.getRequestContent());
				JSONArray titleArr = pageJSON.getJSONArray("titleList");
				if(!titleArr.isEmpty()){
					for(int j=0;j<titleArr.size();j++){
						JSONObject titleJSON = titleArr.getJSONObject(j);
						if(title.getTitleId().equals(titleJSON.getInteger("titleSeq"))){
							JSONArray optionArr = titleJSON.getJSONArray("optionList");
							if(optionArr!=null && !optionArr.isEmpty()){
								for(int k=0;k<optionArr.size();k++){
									Integer optionId = optionArr.getInteger(k);
									if(map.containsKey(optionId)){
										Map<String,Integer> dateMap = map.get(optionId);
										Integer count = dateMap.get(requestDate);
										if(count==null)
											dateMap.put(requestDate, 1);
										else
											dateMap.put(requestDate, count+1);
									}else{
										Map<String,Integer> dateMap = new HashMap<String,Integer>();
										dateMap.put(requestDate, 1);
										map.put(optionId, dateMap);
									}									
								}
							}
							//如果有其他项，则处理
							if(title.getOtherOption()==1&& !StringUtil.isEmpty(titleJSON.getString("otherContent"))){
								//启用了其他项								
								if(map.containsKey(-1)){
									Map<String,Integer> dateMap = map.get(-1);
									Integer count = dateMap.get(requestDate);
									if(count==null)
										dateMap.put(requestDate, 1);
									else
										dateMap.put(requestDate, count+1);
								}else{
									Map<String,Integer> dateMap = new HashMap<String,Integer>();
									dateMap.put(requestDate, 1);
									map.put(-1, dateMap);
								}
							}
						}						
					}
				}
			}
		}
		return map;
	}
	
	public JSONArray dealTitlePeriod(Map<Integer,Map<String,Integer>> map,List<Title> titleList,List<String> timePeriods){
		JSONArray series = new JSONArray();
		for(int i=0;i<titleList.size();i++){
			Title title = titleList.get(i);
			JSONObject titleJSON = new JSONObject();
			JSONArray  data = new JSONArray();
			titleJSON.put("name",setName(title.getTitleContent()));
			Map<String,Integer> dataMap = map.get(title.getTitleId());
			if(dataMap==null || dataMap.isEmpty()){
				for(int j=0;j<timePeriods.size();j++){
					data.add(0);
				}
				continue;
			}
			for(int j=0;j<timePeriods.size();j++){
				String timePeriod = timePeriods.get(j);
				Integer count = dataMap.get(timePeriod);
				if(count==null)
					data.add(0);
				else
					data.add(count);
			}
			titleJSON.put("data", data);
			series.add(titleJSON);
		}		
		return series;
	}	
	public JSONArray dealOptionPeriod(Map<Integer,Map<String,Integer>> map,List<Option> optionList,List<String> timePeriods,Title title){
		JSONArray series = new JSONArray();
		for(int i=0;i<optionList.size();i++){
			Option option = optionList.get(i);
			JSONObject optionJSON = new JSONObject();
			optionJSON.put("name", setName(option.getOptionContent()));
			JSONArray data = new JSONArray();
			Map<String,Integer> dateMap = map.get(option.getOptionId());			
			for(int j=0;j<timePeriods.size();j++){
				String YYYYMDH = timePeriods.get(j);
				if(dateMap==null){
					data.add(0);
					continue;
				}
				Integer count = dateMap.get(YYYYMDH);
				if(count==null)
					data.add(0);
				else
					data.add(count);
			}		
			optionJSON.put("data", data);
			series.add(optionJSON);
		}
		if(title.getOtherOption()==1){
			//启用其他项
			JSONObject otherOptionJSON = new JSONObject();
			otherOptionJSON.put("name", "其他");
			JSONArray data = new JSONArray();
			Map<String,Integer> dateMap = map.get(-1);			
			for(int j=0;j<timePeriods.size();j++){
				String YYYYMDH = timePeriods.get(j);
				if(dateMap==null){
					data.add(0);
					continue;
				}
				Integer count = dateMap.get(YYYYMDH);
				if(count==null)
					data.add(0);
				else
					data.add(count);
			}
			otherOptionJSON.put("data", data);
			series.add(otherOptionJSON);
		}
		return series;
	}
	public String setName(String name){
		if(name==null)
			return "--";
		else if(name.length()>7)
			return name.substring(0, 6)+"...";
		return name;
	}		
}
