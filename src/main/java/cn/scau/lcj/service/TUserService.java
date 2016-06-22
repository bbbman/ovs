package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.TUser;

public interface TUserService {
	TUser load(Integer id);  
	  
	TUser get(Integer id);  
	  
	List<TUser> findAll();  
	  
	void persist(TUser entity);  
	  
	Integer save(TUser entity);  
	  
	void saveOrUpdate(TUser entity);  
	  
	void delete(Integer id);  
	  
	void flush();  
}
