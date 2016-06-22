package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.OptionDao;
import cn.scau.lcj.entity.createVote.Option;
@Repository
public class OptionDaoImpl implements OptionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Option load(Integer id) {
		// TODO Auto-generated method stub
		return (Option) this.getCurrentSession().load(Option.class, id);
	}

	@Override
	public Option get(Integer id) {
		// TODO Auto-generated method stub
		return this.get(id);
	}

	@Override
	public List<Option> findAll() {
		// TODO Auto-generated method stub
		List<Option> list = this.getCurrentSession().createQuery("from Option").list();
		return list;
	}

	@Override
	public void persist(Option entity) {
		// TODO Auto-generated method stub
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(Option entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Option entity) {
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
	//批量保存
	public void saveAll(List<Option> list){
		Integer optionId ;
		Transaction tx = this.getCurrentSession().beginTransaction();
		for(int i=0;i<list.size();i++){
			optionId = this.save(list.get(i));
			if(i%100 ==0){//每一百条刷新并插进数据库
				this.flush();
				this.getCurrentSession().clear();
			}
			//通过获取这个id
		}
		tx.commit();
		
	}

	@Override
	public List<Option> selectOptionBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		List<Option> list = this.getCurrentSession().createSQLQuery(sqlString).addEntity(Option.class).list();
		return list;
	}

	@Override
	public void updateOptionBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		this.getCurrentSession().createSQLQuery(sqlString).executeUpdate();
		this.flush();
	}

	@Override
	public void deleteBySqlString(String sqlString) {
		// TODO Auto-generated method stub
		this.getCurrentSession().createSQLQuery(sqlString).executeUpdate();
		this.flush();
	}

}
