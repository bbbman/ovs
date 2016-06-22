package cn.scau.lcj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import cn.scau.lcj.entity.TUser;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",  
//"classpath:spring-hibernate.xml" })

public class TestTUserService {
	
	private static final Logger LOGGER = Logger
			.getLogger(TestTUserService.class);
	@Autowired
	private TUserService tUserService;
	
	@Test
	public void save(){
		TUser tUser = new TUser();
		tUser.setUsername("lcj2");
		tUser.setPassword("12345678");
		Integer id = tUserService.save(tUser);
		JSON.toJSONString(id);
	}
}
