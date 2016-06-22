package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.MessageBoard;

public interface MessageBoardService {
	
	Integer save(MessageBoard messageBoard);
	
	Integer countByDealType(Integer dealType,Integer userId);
	
	Integer countAllMess(Integer userId);
	
	List<MessageBoard> findAll();
									
	List<MessageBoard> selectByDealType(Integer dealType,Integer userId);
	
	MessageBoard selectByPrimaryKey(Integer mBSeq);
	//逆序
	List<MessageBoard> findParents();
	
	List<MessageBoard> findChildren(Integer parentId);
	
	void updateByObject(MessageBoard messageBoard);
	
	void deleteMessage(Integer mBSeq);
	
	void deleteChildByParentSeq(Integer parentSeq);
	
}
