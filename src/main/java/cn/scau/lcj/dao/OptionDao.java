package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.createVote.Option;

public interface OptionDao extends GenericDao<Option,Integer>{
	List<Option> selectOptionBySqlString(String sqlString);
	
	void updateOptionBySqlString(String sqlString);
	
	void deleteBySqlString(String sqlString);
}
