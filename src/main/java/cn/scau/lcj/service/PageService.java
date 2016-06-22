package cn.scau.lcj.service;

import java.util.List;

import cn.scau.lcj.entity.createVote.Page;

public interface PageService {
 Integer savePage(Page page);
 Page selectPageByPrimaryKey(Integer pageId);
 void updatePage(Page page);
 List<Page> findNoImagePage(Integer num);
 List<Page> findImagePage(Integer num);
 List<Page> findByUserId(Integer userId);
 void deleteByPrimaryKey(Integer pageSeq);
 List<Page> findAll();
 List<Page> findBysearchContent(String content);
}
