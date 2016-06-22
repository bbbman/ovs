package cn.scau.lcj.service;

import java.util.List;
import cn.scau.lcj.entity.HelpBoard;

public interface HelpBoardService {
	
	List<HelpBoard> findAll();
	
	Integer saveHelpBoard(HelpBoard helpBoard);
	
	void deleteBySeq(Integer helpBoardSeq);
}
