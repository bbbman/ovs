package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.User;

public interface UserService {
	//检验数据
	public boolean isContain(String columnName,String param);
	//注册新用户
	public Integer insertUser(User user);
	//用户登录
	public List<User> doLogin(String username,String password);
	//更新用户
	void updateUserByObject(User user);
	//获取所有的未删除的普通用户
	List<User> selectAll();
	//根据username查找用户
	List<User> selectByUsername(String username);
	//根据id获取用户
	User selectByPrimaryKey(Integer userId);
	void deleteUserByPrimaryKey(Integer userId);
	
	
	
}
