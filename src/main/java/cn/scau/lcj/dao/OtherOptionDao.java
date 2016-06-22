package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.createVote.OtherOption;

public interface OtherOptionDao extends GenericDao<OtherOption,Integer> {
	List<OtherOption> selectOtherOptionBySqlString(String SqlString);
	void deleteBySqlString(String sqlString);
}
