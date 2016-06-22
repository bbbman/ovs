package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.HelpCenter;

public interface HelpCenterService {	
	List<HelpCenter> findAll();
	Integer save(HelpCenter entity);
	List<HelpCenter> findUnRead();
	void updateHelpCenterBy(HelpCenter entity);
	HelpCenter getBySeq(Integer heplCenterSeq);
	void deleteBySeq(Integer questionSeq);
}
