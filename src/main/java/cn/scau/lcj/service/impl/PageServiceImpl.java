package cn.scau.lcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.PageDao;
import cn.scau.lcj.dao.TitleDao;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.service.PageService;

@Service("PageService")
public class PageServiceImpl implements PageService {
	@Autowired
	private PageDao pageDao;

	@Override
	public Integer savePage(Page page) {
		// TODO Auto-generated method stub
		return pageDao.save(page);
	}

	@Override
	public Page selectPageByPrimaryKey(Integer pageId) {
		// TODO Auto-generated method stub
		if (pageId == null)
			return null;
		
		return pageDao.get(pageId);
	}

	@Override
	public void updatePage(Page page) {
		// TODO Auto-generated method stub
		pageDao.updatePage(page);
	}

	@Override
	public List<Page> findNoImagePage(Integer num) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM page WHERE `disable` = 0 AND is_image_vote = 0 AND (dead_line IS NULL OR dead_line > NOW())");
		sb.append(" order by submit_times desc limit "+num);		
		return pageDao.findBysqlString(sb.toString());
	}

	@Override
	public List<Page> findImagePage(Integer num) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM page WHERE `disable` = 0 AND is_image_vote = 1 AND (dead_line IS NULL OR dead_line > NOW())");
		sb.append(" order by submit_times desc limit "+num);		
		return pageDao.findBysqlString(sb.toString());
	}

	@Override
	public List<Page> findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from page where user_id = "+userId);
		sb.append(" order by build_time desc");		
		return pageDao.findBysqlString(sb.toString());
	}

	@Override
	public void deleteByPrimaryKey(Integer pageSeq) {
		// TODO Auto-generated method stub
		pageDao.delete(pageSeq);
	}

	@Override
	public List<Page> findAll() {
		// TODO Auto-generated method stub		
		return pageDao.findAll();
	}

	@Override
	public List<Page> findBysearchContent(String content) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT p.* FROM  ");
		sb.append(" page p LEFT JOIN `user` u ON p.user_id = u.user_id ");
		sb.append(" WHERE u.username LIKE '%"+content+"%' ");
		sb.append(" OR p.main_title LIKE '%"+content+"%' ");		
		return pageDao.findBysqlString(sb.toString());
	}

}
