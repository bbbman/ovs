package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.common.ObjectUtil;
import cn.scau.lcj.dao.RequestRecordDao;
import cn.scau.lcj.entity.createVote.RequestRecord;
import cn.scau.lcj.service.RequestRecordService;
@Service("RequestRecordService")
public class RequestRecordServiceImpl implements RequestRecordService {
	
	@Autowired
	private RequestRecordDao requestRecordDao;
	
	@Override
	public RequestRecord selectByPageIdAndIp(Integer pageId, String ip) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from request_record where ");
		sb.append(" request_page_id = "+pageId);
		sb.append(" and request_ip = '"+ip+"'");
		sb.append(" limit 1");
		List<RequestRecord> list = requestRecordDao.selectRequestRecordBySqlString(sb.toString());
		if(!ObjectUtil.isEmptyList(list))
			return list.get(0);
		return null;
	}

	@Override
	public Integer saveRequestRecord(RequestRecord requestRecord) {
		// TODO Auto-generated method stub
		return requestRecordDao.save(requestRecord);
	}

	@Override
	public List<RequestRecord> selectByPageIdAndDeadLine(Integer pageId,String dealLine) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from request_record where ");
		sb.append(" request_page_id = "+pageId);
		sb.append(" and request_date <= '"+dealLine+"' ");
		sb.append(" order by request_date ");
		List<RequestRecord> list = requestRecordDao.selectRequestRecordBySqlString(sb.toString());		
		return list;
	}

}
