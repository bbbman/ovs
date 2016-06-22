package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.PageDao;
import cn.scau.lcj.entity.createVote.Page;
@Repository
public class PageDaoImpl implements PageDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public Page load(Integer id) {
		// TODO Auto-generated method stub
		return (Page) this.getCurrentSession().load(Page.class, id);
	}

	@Override
	public Page get(Integer id) {
		// TODO Auto-generated method stub
		return (Page) this.getCurrentSession().get(Page.class, id);
	}

	@Override
	public List<Page> findAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Page> list = this.getCurrentSession().createQuery("from Page").list();
		return list;
	}

	@Override
	public void persist(Page entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(Page entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Page entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Page page = this.load(id);
		this.getCurrentSession().delete(page);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurrentSession().flush();
	}

	@Override
	public void updatePage(Page page) {
		// TODO Auto-generated method stub
		this.getCurrentSession().update(page);
	}

	@Override
	public List<Page> findBysqlString(String sqlString) {
		// TODO Auto-generated method stub
		
		return this.getCurrentSession().createSQLQuery(sqlString).addEntity(Page.class).list();
	}

}
