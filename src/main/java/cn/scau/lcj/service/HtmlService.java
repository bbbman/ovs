package cn.scau.lcj.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.Title;

public interface HtmlService {
	String createHtml(ServletContext context,Map<String,Object> pageData);
}
