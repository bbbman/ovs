package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.User;

public interface UserDao extends GenericDao<User,Integer>{
	
	public boolean isContain(String  queryString);
	
	public List<User> findUserBySql(String queryString);
	
	void updateUser(User user);
}
