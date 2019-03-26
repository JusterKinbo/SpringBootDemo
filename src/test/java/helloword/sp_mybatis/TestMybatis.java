package helloword.sp_mybatis;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import demo.juster.spboot.SampleController;
import demo.juster.spboot.mapper.RoleMapper;
import demo.juster.spboot.mapper.UserMapper;
import demo.juster.spboot.pojo.user.Role;
import demo.juster.spboot.pojo.user.User;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)   
@SpringBootTest(classes={SampleController.class})// 指定启动类
public class TestMybatis {
	
	@Autowired
	RoleMapper rmp;
	
	@Autowired
	UserMapper ump;
	 @Test
	    public void testRole(){
	        
		 	Role u = rmp.findById((long) 5);
		 	System.out.println("test hello 1" + u);
		 	
		 	List<Role> rl = rmp.findAll();
		 	for(Role r : rl)
		 	{
		 		System.out.println("role find all"+r);
		 	}
	    }

	    @Test
	    public void testUser(){
	    	User u = ump.findByName("juster12");
		 	System.out.println("findByName" + u);
		 	u = ump.findById((long) 1);
		 	System.out.println("findById" + u);
		 	List<User> ul = ump.findAll();
		 	for(User u1 : ul)
		 	{
		 		System.out.println("findAll"+u1);
		 	}
		 	
	        TestCase.assertEquals(1, 1);
	    }

	    @Before
	    public void testBefore(){
	        System.out.println("before");
	    }

	    @After
	    public void testAfter(){
	        System.out.println("after");
	    }

}
