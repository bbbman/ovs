package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.RequestRecordDao;
import cn.scau.lcj.entity.createVote.RequestRecord;
@Repository
public class RequestRecordDaoImpl implements RequestRecordDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public RequestRecord load(Integer id) {
		// TODO Auto-generated method stub
		return (RequestRecord) this.getCurSession().load(RequestRecord.class, id);
	}

	@Override
	public RequestRecord get(Integer id) {
		// TODO Auto-generated method stub
		return (RequestRecord) this.getCurSession().get(RequestRecord.class, id);
	}

	@Override
	public List<RequestRecord> findAll() {
		// TODO Auto-generated method stub
		List<RequestRecord> list = this.getCurSession().createQuery("from RequestRecord").list();
		return list;
	}

	@Override
	public void persist(RequestRecord entity) {
		// TODO Auto-generated method stub
		this.getCurSession().persist(entity);
	}

	@Override
	public Integer save(RequestRecord entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurSession().save(entity);
	}

	@Override
	public void saveOrUpdate(RequestRecord entity) {
		// TODO Auto-generated method stub
		this.getCurSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getCurSession().delete(this.load(id));
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurSession().flush();
	}

	@Override
	public List<RequestRecord> selectRequestRecordBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		List<RequestRecord> list = this.getCurSession().createSQLQuery(sqlString).addEntity(RequestRecord.class).list();
		return list;
	}
	
}
