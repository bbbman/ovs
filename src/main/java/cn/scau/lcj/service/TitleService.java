package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.createVote.Title;

public interface TitleService {
	Integer saveTitle(Title title);
	
	Title selectTitleBySeq(Integer pageSeq,Integer titleSeq);
	
	void updateTitleByObject(Title title);
	
	void deleteByPageSeq(Integer pageSeq);
	
	List<Title> selectByPageSeq(Integer pageSeq);
}
