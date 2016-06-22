package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.createVote.RequestRecord;

public interface RequestRecordDao extends GenericDao<RequestRecord,Integer> {
	
	List<RequestRecord> selectRequestRecordBySqlString(String sqlString);
}
