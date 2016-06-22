package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.HelpBoardDao;
import cn.scau.lcj.entity.HelpBoard;
@Repository
public class HelpBoardDaoImpl implements HelpBoardDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurSession(){
		return this.sessionFactory.getCurrentSession();
	} 

	@Override
	public HelpBoard load(Integer id) {
		// TODO Auto-generated method stub
		return (HelpBoard) this.getCurSession().load(HelpBoard.class, id);
	}

	@Override
	public HelpBoard get(Integer id) {
		// TODO Auto-generated method stub
		return (HelpBoard) this.getCurSession().get(HelpBoard.class, id);
	}

	@Override
	public List<HelpBoard> findAll() {
		// TODO Auto-generated method stub		
		return this.getCurSession().createQuery("from HelpBoard ORDER BY helpBuildTime DESC ").list();
	}

	@Override
	public void persist(HelpBoard entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer save(HelpBoard entity) {
		// TODO Auto-generated method stub
		
		return (Integer) this.getCurSession().save(entity);
	}

	@Override
	public void saveOrUpdate(HelpBoard entity) {
		// TODO Auto-generated method stub
		this.getCurSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		HelpBoard helpBoard = this.load(id);
		this.getCurSession().delete(helpBoard);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurSession().flush();
	}

}
