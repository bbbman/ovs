package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.createVote.Page;

public interface PageDao extends GenericDao<Page,Integer> {
	void updatePage(Page page);
	List<Page> findBysqlString(String sqlString);
}
