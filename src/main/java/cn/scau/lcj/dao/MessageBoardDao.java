package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.MessageBoard;

public interface MessageBoardDao extends GenericDao<MessageBoard,Integer> {
	Integer countBySqlString(String sqlString);
	
	List<MessageBoard> selectBySql(String sqlString);
	
	void updateByMessageBoard(MessageBoard messageBoard );
	
	void deleteBySql(String sqlString);
}
