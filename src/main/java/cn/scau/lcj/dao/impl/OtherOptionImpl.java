package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.OtherOptionDao;
import cn.scau.lcj.entity.createVote.OtherOption;
@Repository
public class OtherOptionImpl implements OtherOptionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public OtherOption load(Integer id) {
		// TODO Auto-generated method stub
		return (OtherOption) this.getCurrentSession().load(OtherOption.class, id);
	}

	@Override
	public OtherOption get(Integer id) {
		// TODO Auto-generated method stub
		return (OtherOption) this.getCurrentSession().get(OtherOption.class, id);
	}

	@Override
	public List<OtherOption> findAll() {
		// TODO Auto-generated method stub
		List<OtherOption> list = this.getCurrentSession().createQuery("from OtherOption").list();
		return null;
	}

	@Override
	public void persist(OtherOption entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(OtherOption entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(OtherOption entity) {
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
	public List<OtherOption> selectOtherOptionBySqlString(String SqlString) {
		// TODO Auto-generated method stub		
		return this.getCurrentSession().createSQLQuery(SqlString).addEntity(OtherOption.class).list();
	}

	@Override
	public void deleteBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		this.getCurrentSession().createSQLQuery(sqlString).executeUpdate();
		this.flush();
	}

}
