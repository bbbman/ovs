package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.UserDao;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Override
	public boolean isContain(String columnName,String param) {
		// TODO Auto-generated method stub
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("select * from user where ");
		sqlString.append(columnName+" = '"+param+"'");		
		return userDao.isContain(sqlString.toString());
	}
	@Override
	public Integer insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}
	@Override
	public List<User> doLogin(String email, String password) {
		// TODO Auto-generated method stub
		StringBuilder sqlString =new StringBuilder();
		sqlString.append("select * from user where ");
		sqlString.append(" email = '"+email+"'");
		sqlString.append(" and password = '"+password+"'");
		sqlString.append(" and enable = 0 and is_delete = 0 ");
		return userDao.findUserBySql(sqlString.toString());
	}
	@Override
	public void updateUserByObject(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}
	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		StringBuilder sqlString =new StringBuilder();
		sqlString.append("select * from user where ");
		sqlString.append(" is_delete = 0 and user_type = 0 ");
		return userDao.findUserBySql(sqlString.toString());
	}
	@Override
	public List<User> selectByUsername(String username) {
		// TODO Auto-generated method stub
		StringBuilder sqlString =new StringBuilder();
		sqlString.append("select * from user where  ");
		sqlString.append(" username like '%"+username+"%'");
		sqlString.append(" and is_delete = 0 and user_type = 0 ");
		return userDao.findUserBySql(sqlString.toString());
	}
	@Override
	public User selectByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		if(userId==null) return null;
		return userDao.get(userId);
	}
	@Override
	public void deleteUserByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		userDao.delete(userId);
	}

}
