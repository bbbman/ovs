package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.OtherOption;

public interface OptionService {
	Integer save(Option entity);
	Integer saveOtherOption(OtherOption therOption);
	void saveOptionAndUpdateImage(String[] optionArray,Integer titleId,Integer pageId);
	Option selectOptionByTitleSeq(Integer titleSeq,Integer optionSeq);
	OtherOption selectOtherOptionByTitleSeq(Integer titleSeq);
	
	void updateOptionTimesByObject(Option option);
	
	void updateOptionTimesByArray(Integer titleSeq,Integer[] optionSeq);
	
	void deleteByTitleSeq(Integer titleSeq);
	
	void deleteOtherOptionByTitleSeq(Integer titleSeq);
	
	void update2ZreoByTitleSeq(Integer titleSeq);
	
	List<Option> selectByTitleSeq(Integer titleSeq);
}
