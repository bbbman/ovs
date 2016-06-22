package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.TitleDao;
import cn.scau.lcj.entity.createVote.Title;
@Repository
public class TitleDaoImpl implements TitleDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public Title load(Integer id) {
		// TODO Auto-generated method stub
		return (Title) this.getCurrentSession().load(Title.class, id);
	}

	@Override
	public Title get(Integer id) {
		// TODO Auto-generated method stub
		return (Title) this.getCurrentSession().get(Title.class, id);
	}

	@Override
	public List<Title> findAll() {
		// TODO Auto-generated method stub
		List<Title> list = this.getCurrentSession().createQuery("from Title").list();
		return list;
	}

	@Override
	public void persist(Title entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(Title entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Title entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getCurrentSession().delete(id);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}
	@Override
	public List<Title> selectTitleBySqlStirng(String sqlString) {
		// TODO Auto-generated method stub
		List<Title> list = this.getCurrentSession().createSQLQuery(sqlString).addEntity(Title.class).list();
		if(list != null && !list.isEmpty())
			return list;
		return null;
	}
	@Override
	public void deleteBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		this.getCurrentSession().createSQLQuery(sqlString).executeUpdate();
	}


}
