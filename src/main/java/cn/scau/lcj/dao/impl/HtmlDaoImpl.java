package cn.scau.lcj.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.scau.common.FileUtil;
import cn.scau.common.UpLoadUtil;
import cn.scau.lcj.dao.HtmlDao;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.utils.common.Log;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Repository
public class HtmlDaoImpl implements HtmlDao {

	@Override
	public String crateHTML(ServletContext context, Map<String, Object> data,
			String templatePath) {
		Log.log("生成文件开始");
		Configuration freemarkerCfg = new Configuration();
		// 加载模版文件的路径
		freemarkerCfg.setServletContextForTemplateLoading(context,
				"/WEB-INF/ftl/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		try {
			// 指定模版路径
			Template template = freemarkerCfg
					.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静态页面路径
			String fileName = UpLoadUtil.biuldFileName("html");
			String htmlPath = context.getRealPath("/vp/")+"\\"+fileName;
			
			String url =  (String) data.get("basePath")+"/vp/"+fileName;
			data.put("url", url);
						
			File htmlFile = FileUtil.createFile(htmlPath);			
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版并开始输出静态页面
			template.process(data, out);
			out.flush();
			out.close();
			// 返回文件路径
			Log.log("c创建的文件路径:"+htmlPath);
			Log.log("basePath:"+context.getContextPath());
			Log.log("生成文件结束");
			return "/vp/"+fileName;
		} catch (Exception e) {			
			e.printStackTrace();
			Log.log("生成静态网页出错");
			return null;
		}
	}

}
