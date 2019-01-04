package com.tj.multi_datasource;

import com.tj.multi_datasource.domain.User;
import com.tj.multi_datasource.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan("com.tj.multi_datasource.mapper")
public class MultiDatasourceApplicationTests {

	@Test
	public void contextLoads() {
	}

	private UserService userService;


//	public void getUser(){
//		int id=18;
//		User user=userService.getUser(id);
//		System.out.println(user.toString());
//	}

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	@Before
	public void setUp() {
		jdbcTemplate1.update("DELETE  FROM  USER ");
		jdbcTemplate2.update("DELETE  FROM  USER ");
	}

	@Test
	public void test(){
	    jdbcTemplate1.update("INSERT into user (name,age) values (?,?) ","aa","2");
		jdbcTemplate2.update("INSERT into user (name,age) values (?,?) ","bb","1");
	}
}

