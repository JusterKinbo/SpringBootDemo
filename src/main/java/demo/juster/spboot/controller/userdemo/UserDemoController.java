package demo.juster.spboot.controller.userdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.juster.spboot.pojo.dao.RoleRepository;
import demo.juster.spboot.pojo.dao.UserRepository;
import demo.juster.spboot.pojo.user.Role;
import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.util.MD5Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserDemoController {
	
	  @Autowired
	  private UserRepository userRepository;

	  @Autowired
	  private RoleRepository roleRepository;
	    
	  @RequestMapping("/index")
	    public String index(Model model) {

	        return "/user/index";
	    }
	  
	  @RequestMapping("/toAddUser")
	    public String toAddUser() {
		  System.out.println("toAddUser:=======");
		  return "/user/addUser";
	  }
	  
	  @RequestMapping("/addUser")
	    public String addUser(String name,String password,String role) {
		  try {
			  String encodePassword = MD5Util.encode(password);
		        User user = new User(name,encodePassword);
		        List<Role> roles = new ArrayList<>();
		        Role role1 = new Role();
		        role1.setRolename(role);
		        roles.add(role1);
		        user.setRoles(roles);
		        System.out.println("user:======="+user);
		        userRepository.save(user);
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	        
	            return "/user/index";
	    }

}
