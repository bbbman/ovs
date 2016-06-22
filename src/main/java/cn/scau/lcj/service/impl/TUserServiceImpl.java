package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.TUserDao;
import cn.scau.lcj.entity.TUser;
import cn.scau.lcj.service.TUserService;
//service层我们使用@Service注解将其注入为bean
@Service("TUserService")
public class TUserServiceImpl implements TUserService {
	
	@Autowired
	private TUserDao tUserDao;

	@Override
	public TUser load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TUser get(Integer id) {
		// TODO Auto-generated method stub
		return tUserDao.get(id);
	}

	@Override
	public List<TUser> findAll() {
		// TODO Auto-generated method stub
		return tUserDao.findAll();
	}

	@Override
	public void persist(TUser entity) {
		// TODO Auto-generated method stub
		tUserDao.persist(entity);
	}

	@Override
	public Integer save(TUser entity) {
		// TODO Auto-generated method stub
		return tUserDao.save(entity);
	}

	@Override
	public void saveOrUpdate(TUser entity) {
		// TODO Auto-generated method stub
		tUserDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tUserDao.delete(id);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		tUserDao.flush();
	}

}
