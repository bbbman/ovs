package cn.scau.lcj.dao;

import java.util.List;

import cn.scau.lcj.entity.HelpCenter;

public interface HelpCenterDao extends GenericDao<HelpCenter,Integer>  {
	List<HelpCenter> findUnRead();
	
	void updateByObject(HelpCenter helpCenter);
}
