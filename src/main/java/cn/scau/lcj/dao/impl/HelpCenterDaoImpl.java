package cn.scau.lcj.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.lcj.dao.HelpCenterDao;
import cn.scau.lcj.entity.HelpCenter;

@Repository
public class HelpCenterDaoImpl implements HelpCenterDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public HelpCenter load(Integer id) {
		// TODO Auto-generated method stub
		return (HelpCenter) this.getCurSession().load(HelpCenter.class, id);
	}

	@Override
	public HelpCenter get(Integer id) {
		// TODO Auto-generated method stub
		return (HelpCenter) this.getCurSession().get(HelpCenter.class, id);
	}

	@Override
	public List<HelpCenter> findAll() {
		// TODO Auto-generated method stub
		return this.getCurSession()
				.createQuery("from HelpCenter order by buildTime desc").list();
	}

	@Override
	public void persist(HelpCenter entity) {
		// TODO Auto-generated method stub
		this.getCurSession().persist(entity);
	}

	@Override
	public Integer save(HelpCenter entity) {
		// TODO Auto-generated method stub
		return (Integer) this.getCurSession().save(entity);
	}

	@Override
	public void saveOrUpdate(HelpCenter entity) {
		// TODO Auto-generated method stub
		this.getCurSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		HelpCenter helpCenter = this.load(id);
		if (helpCenter != null)
			this.getCurSession().delete(helpCenter);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		this.getCurSession().flush();
	}

	@Override
	public List<HelpCenter> findUnRead() {
		// TODO Auto-generated method stubng
		StringBuilder sb = new StringBuilder();
		sb.append("select hc.help_id,hc.user_id,hc.content,hc.build_time,hc.parent_id,hc.help_deal_type,hc.help_level,u.username ");
		sb.append(" from ");
		sb.append(" help_center hc left join user u on hc.user_id = u.user_id ");
		sb.append(" where hc.help_deal_type = 0 order by hc.build_time desc");
		return this.getCurSession().createSQLQuery(sb.toString()).addEntity(HelpCenter.class).list();		
	}

	@Override
	public void updateByObject(HelpCenter helpCenter) {
		// TODO Auto-generated method stub
		this.getCurSession().update(helpCenter);
	}

}
