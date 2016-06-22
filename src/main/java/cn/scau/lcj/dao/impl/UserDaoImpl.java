package cn.scau.lcj.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.UserDao;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.utils.common.Log;
//我们采用@Repository注解将其注入为dao的bean，交由spring管理。
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public User load(Integer id) {
		// TODO Auto-generated method stub
		return (User) this.getCurSession().load(User.class, id);
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return (User) this.getCurSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> list =this.getCurSession().createQuery("from User").list();
		return list;
	}

	@Override
	public void persist(User entity) {
		// TODO Auto-generated method stub
		this.getCurSession().persist(entity);
	}

	@Override
	public Integer save(User entity) {
		// TODO Auto-generated method stub
		
		return (Integer) this.getCurSession().save(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {
		// TODO Auto-generated method stub
		this.getCurSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		User user = this.load(id);
		this.getCurSession().delete(user);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isContain(String queryString) {
		// TODO Auto-generated method stub
		List<User> list = this.getCurSession().createSQLQuery(queryString).list();
		return (list == null ||list.size()==0)?false:true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserBySql(String queryString) {
		// TODO Auto-generated method stub
		List<User> list = this.getCurSession().createSQLQuery(queryString).addEntity(User.class).list();			
		return list;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.getCurSession().update(user);
	}

}
