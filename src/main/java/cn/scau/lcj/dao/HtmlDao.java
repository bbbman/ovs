package cn.scau.lcj.dao;

import java.util.Map;

import javax.servlet.ServletContext;



public interface HtmlDao {
	 String crateHTML(ServletContext context,Map<String,Object> data,String templatePath);
}
