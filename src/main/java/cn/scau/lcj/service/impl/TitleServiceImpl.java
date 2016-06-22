package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.TitleDao;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.TitleService;

@Service("TitleService")
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleDao titleDao;

	@Override
	public Integer saveTitle(Title title) {
		// TODO Auto-generated method stub
		return titleDao.save(title);
	}

	@Override
	public Title selectTitleBySeq(Integer pageSeq, Integer titleSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from title ");
		sb.append("where title_id = " + titleSeq);
		sb.append(" and page_id = " + pageSeq);
		List<Title> list = titleDao.selectTitleBySqlStirng(sb.toString());
		if (list == null)
			return null;
		return list.get(0);
	}

	@Override
	public void updateTitleByObject(Title title) {
		// TODO Auto-generated method stub
		titleDao.saveOrUpdate(title);
	}

	@Override
	public void deleteByPageSeq(Integer pageSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("delete from title where page_id = "+pageSeq);
		titleDao.deleteBySqlString(sb.toString());
	}

	@Override
	public List<Title> selectByPageSeq(Integer pageSeq) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from title ");
		sb.append(" where page_id = "+pageSeq);
		sb.append(" order by title_position ");
		return titleDao.selectTitleBySqlStirng(sb.toString());
	}

}
