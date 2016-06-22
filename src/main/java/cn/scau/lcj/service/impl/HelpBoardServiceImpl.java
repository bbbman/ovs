package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.entity.HelpBoard;
import cn.scau.lcj.service.HelpBoardService;
import cn.scau.lcj.dao.HelpBoardDao;
@Service("HelpBoardService")
public class HelpBoardServiceImpl implements HelpBoardService  {
	
	@Autowired
	private HelpBoardDao HelpBoardDao;

	@Override
	public List<HelpBoard> findAll() {
		// TODO Auto-generated method stub
		return HelpBoardDao.findAll();
	}

	@Override
	public Integer saveHelpBoard(HelpBoard helpBoard) {
		// TODO Auto-generated method stub
		if(helpBoard==null)return null;
		return HelpBoardDao.save(helpBoard);
	}

	@Override
	public void deleteBySeq(Integer helpBoardSeq) {
		// TODO Auto-generated method stub
		HelpBoardDao.delete(helpBoardSeq);
	}
}
