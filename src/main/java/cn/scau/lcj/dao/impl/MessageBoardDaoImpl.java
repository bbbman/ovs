package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import cn.scau.lcj.dao.MessageBoardDao;
import cn.scau.lcj.entity.MessageBoard;
import cn.scau.lcj.utils.common.Log;
//我们采用@Repository注解将其注入为dao的bean，交由spring管理。
@Repository
public class MessageBoardDaoImpl implements MessageBoardDao  {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public MessageBoard load(Integer id) {
		// TODO Auto-generated method stub
		return (MessageBoard) this.getCurSession().load(MessageBoard.class, id);
	}

	@Override
	public MessageBoard get(Integer id) {
		// TODO Auto-generated method stub
		return (MessageBoard) this.getCurSession().get(MessageBoard.class, id);
	}

	@Override
	public List<MessageBoard> findAll() {
		// TODO Auto-generated method stub
		List<MessageBoard> list =this.getCurSession().createQuery("from MessageBoard order by buildTime desc").list();
		return list;
	}

	@Override
	public void persist(MessageBoard entity) {
		// TODO Auto-generated method stub
		this.getCurSession().persist(entity);
	}

	@Override
	public Integer save(MessageBoard entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurSession().save(entity);
	}

	@Override
	public void saveOrUpdate(MessageBoard entity) {
		// TODO Auto-generated method stub
		this.getCurSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		MessageBoard mb = this.load(id);
		this.getCurSession().delete(mb);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurSession().flush();
	}

	@Override
	public Integer countBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		Integer count = Integer.parseInt(this.getCurSession().createSQLQuery(sqlString).list().get(0).toString());
		return count;
	}

	@Override
	public List<MessageBoard> selectBySql(String sqlString) {
		// TODO Auto-generated method stub
		List<MessageBoard> list = this.getCurSession().createSQLQuery(sqlString).addEntity(MessageBoard.class).list();
		return list;
	}

	@Override
	public void updateByMessageBoard(MessageBoard messageBoard) {
		// TODO Auto-generated method stub
		this.getCurSession().update(messageBoard);
	}

	@Override
	public void deleteBySql(String sqlString) {
		// TODO Auto-generated method stub
		this.getCurSession().createSQLQuery(sqlString).executeUpdate();
	}

}
