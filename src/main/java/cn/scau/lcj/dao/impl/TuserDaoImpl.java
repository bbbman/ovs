package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.TUserDao;
import cn.scau.lcj.entity.TUser;
//我们采用@Repository注解将其注入为dao的bean，交由spring管理。
@Repository
public class TuserDaoImpl implements TUserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public TUser load(Integer id) {
		// TODO Auto-generated method stub
		return (TUser) this.getCurrentSession().load(TUser.class, id);
	}

	@Override
	public TUser get(Integer id) {
		// TODO Auto-generated method stub
		return (TUser) this.getCurrentSession().get(TUser.class, id);
	}

	@Override
	public List<TUser> findAll() {
		// TODO Auto-generated method stub
		List<TUser> tUserList = this.getCurrentSession().createQuery("from TUser").list();
		return tUserList;
	}

	@Override
	public void persist(TUser entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity); 
	}
	@Override
	public Integer save(TUser entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(TUser entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		TUser entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}
	
}
