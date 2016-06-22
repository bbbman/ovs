package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.HelpCenterDao;
import cn.scau.lcj.entity.HelpCenter;
import cn.scau.lcj.service.HelpCenterService;
@Service("HelpCenterService")
public class HelpCenterServiceImpl implements HelpCenterService {
	
	@Autowired
	private HelpCenterDao helpCenterDao;

	@Override
	public List<HelpCenter> findAll() {
		// TODO Auto-generated method stub
		return helpCenterDao.findAll();
	}

	@Override
	public Integer save(HelpCenter entity) {
		// TODO Auto-generated method stub
		return helpCenterDao.save(entity);
	}

	@Override
	public List<HelpCenter> findUnRead() {
		// TODO Auto-generated method stub

		return helpCenterDao.findUnRead();
	}

	@Override
	public HelpCenter getBySeq(Integer heplCenterSeq) {
		// TODO Auto-generated method stub
		return helpCenterDao.get(heplCenterSeq);
	}

	@Override
	public void updateHelpCenterBy(HelpCenter entity) {
		// TODO Auto-generated method stub
		helpCenterDao.updateByObject(entity);
	}

	@Override
	public void deleteBySeq(Integer questionSeq) {
		// TODO Auto-generated method stub
		helpCenterDao.delete(questionSeq);
	}
	
	

}
