package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.createVote.RequestRecord;

public interface RequestRecordService {
	
	RequestRecord selectByPageIdAndIp(Integer pageId,String ip);
	
	Integer saveRequestRecord(RequestRecord requestRecord);
	
	List<RequestRecord> selectByPageIdAndDeadLine(Integer pageId,String dealLine);
}
