package cn.scau.lcj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.common.ObjectUtil;
import cn.scau.lcj.dao.MessageBoardDao;
import cn.scau.lcj.entity.MessageBoard;
import cn.scau.lcj.service.MessageBoardService;

@Service("MessageBoardService")
public class MessageBoardServiceImpl implements MessageBoardService {

	@Autowired
	private MessageBoardDao messageBoardDao;

	@Override
	public Integer save(MessageBoard messageBoard) {
		// TODO Auto-generated method stub

		return messageBoardDao.save(messageBoard);
	}

	@Override
	public Integer countAllMess(Integer userId) {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select count(*) from message_board");
		sqlString.append(" where user_id != " + userId);
		sqlString.append(" and parent_id = 0 and receive_id = 0 ");
		return messageBoardDao.countBySqlString(sqlString.toString());
	}

	@Override
	public Integer countByDealType(Integer dealType, Integer userId) {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select count(*) from message_board");
		sqlString.append(" where deal_type = " + dealType);
		sqlString.append(" and user_id != " + userId);
		sqlString.append(" and parent_id = 0 and receive_id = 0 ");
		return messageBoardDao.countBySqlString(sqlString.toString());
	}

	@Override
	public List<MessageBoard> findAll() {
		// TODO Auto-generated method stub
		List<MessageBoard> list = messageBoardDao.findAll();
		if (ObjectUtil.isEmptyList(list))
			return null;
		return list;
	}

	@Override
	public List<MessageBoard> selectByDealType(Integer dealType, Integer userId) {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" SELECT ");
		sqlString.append("mb.message_id,mb.user_id,mb.content,mb.build_time,");
		sqlString.append("mb.parent_id,mb.deal_type,mb.position,u.username,mb.receive_id,mb.receive_name ");
		sqlString.append("FROM ");
		sqlString.append("message_board mb LEFT JOIN user u on mb.user_id = u.user_id ");
		sqlString.append(" WHERE mb.parent_id = 0  and mb.receive_id = 0  ");
		if (dealType < 3 && dealType >= 0)
			sqlString.append(" and mb.deal_type = " + dealType);
		sqlString.append(" ORDER BY build_time DESC ");																	
		List<MessageBoard> list = messageBoardDao.selectBySql(sqlString
				.toString());
		if (ObjectUtil.isEmptyList(list))
			return null;
		return list;
	}

	@Override
	public MessageBoard selectByPrimaryKey(Integer mBSeq) {
		// TODO Auto-generated method stub
		if (mBSeq == null)
			return null;
		MessageBoard messageBoard = messageBoardDao.get(mBSeq);
		return messageBoard;
	}

	@Override
	public List<MessageBoard> findParents() {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" SELECT ");
		sqlString.append("mb.message_id,mb.user_id,mb.content,mb.build_time,");
		sqlString.append("mb.parent_id,mb.deal_type,mb.position,u.username,mb.receive_id,mb.receive_name ");
		sqlString.append("FROM ");
		sqlString.append("message_board mb LEFT JOIN user u on mb.user_id = u.user_id ");
		sqlString.append(" WHERE mb.parent_id = 0  and mb.receive_id = 0 ORDER BY build_time DESC ");
		return messageBoardDao.selectBySql(sqlString.toString());
	}

	@Override
	public List<MessageBoard> findChildren(Integer parentId) {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("SELECT ");
		sqlString.append("* ");
		sqlString.append("FROM ");
		sqlString.append("( SELECT ");
		sqlString.append("mb.message_id,mb.position,mb.build_time,mb.content,mb.receive_id,u.username, ");
		sqlString.append("mbs.user_id AS receiver ,mb.user_id,us.username AS receive_name,mb.parent_id,mb.deal_type ");
		sqlString.append("FROM message_board mb ");
		sqlString.append("LEFT JOIN user u ON mb.user_id = u.user_id " );
		sqlString.append("LEFT JOIN message_board mbs ON mbs.message_id = mb.receive_id ");
		sqlString.append("LEFT JOIN user us ON us.user_id = mbs.user_id");
		sqlString.append(") r ");
		sqlString.append("WHERE r.parent_id = "+parentId);
		sqlString.append(" ORDER BY r.position ,r.build_time ");
		return messageBoardDao.selectBySql(sqlString.toString());
	}

	@Override
	public void updateByObject(MessageBoard messageBoard) {
		// TODO Auto-generated method stub
		messageBoardDao.updateByMessageBoard(messageBoard);		
	}

	@Override
	public void deleteMessage(Integer mBSeq) {
		// TODO Auto-generated method stub
		messageBoardDao.delete(mBSeq);
	}

	@Override
	public void deleteChildByParentSeq(Integer parentSeq) {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("delete from message_board where parent_id = "+parentSeq);
		messageBoardDao.deleteBySql(sqlString.toString());
	}	
}
