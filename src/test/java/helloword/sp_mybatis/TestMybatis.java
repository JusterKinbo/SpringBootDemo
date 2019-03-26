package helloword.sp_mybatis;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
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
import demo.juster.spboot.mapper.UserRoleMapper;
import demo.juster.spboot.pojo.user.Role;
import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.pojo.user.UserRole;
import demo.juster.spboot.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import junit.framework.TestCase;

@Slf4j
@RunWith(SpringRunner.class)   
@SpringBootTest(classes={SampleController.class})// 指定启动类
public class TestMybatis {
	
	@Autowired
	RoleMapper rmp;
	
	@Autowired
	UserMapper ump;
	
	@Autowired
	UserRoleMapper urmp;
	
	@Autowired
	IUserService ussv;
	
	@Test
	public void testUserSave()
	{
		try {
			User user = new User("lala5", "123");
	        List<Role> roles = new ArrayList<>();
	        Role role1 = new Role();
	        role1.setRolename("ROLE_USER");
	        roles.add(role1);
	        user.setRoles(roles);
			ussv.saveUser(user);
		}catch(Exception e)
		{
			
			System.out.println(e);
		}
		
	}
	
//	@Test
	public void testUserRole()
	{
	  UserRole ur = new UserRole();
	  ur.setRole_id((long) 2);
	  ur.setUser_id((long) 1);
//	  urmp.save(ur);
//	  urmp.delete(ur);
	}
	
//	 @Test
	    public void testRole(){
	        
		 	Role u = rmp.findById((long) 5);
		 	System.out.println("test hello 1" + u);
		 	
		 	List<Role> rl = rmp.findAll();
		 	for(Role r : rl)
		 	{
		 		System.out.println("role find all"+r);
		 	}
	    }

//	    @Test
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
