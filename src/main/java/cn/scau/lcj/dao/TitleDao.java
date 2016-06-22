package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.createVote.Title;

public interface TitleDao extends GenericDao<Title,Integer> {
	List<Title> selectTitleBySqlStirng(String sqlString);
	void deleteBySqlString(String sqlString);
}
