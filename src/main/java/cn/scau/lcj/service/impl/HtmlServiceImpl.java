package cn.scau.lcj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scau.lcj.dao.HtmlDao;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.HtmlService;

@Service("HtmlService")
public class HtmlServiceImpl implements HtmlService {

	@Autowired
	private HtmlDao htmlDao;
	
	

	public String createHtml(ServletContext context,Map<String,Object> pageData) {
		try {
			// 准备数据
			// 调用静态页面方法
			return htmlDao.crateHTML(context, pageData, "example.ftl");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}