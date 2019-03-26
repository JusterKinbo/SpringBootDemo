package demo.juster.spboot.controller.userdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.juster.spboot.mapper.RoleMapper;
import demo.juster.spboot.mapper.UserMapper;
import demo.juster.spboot.pojo.user.Role;
import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.service.interfaces.IUserService;
import demo.juster.spboot.util.MD5Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserDemoController {
	
	  @Autowired
	  private IUserService userServicy;

	    
	  @RequestMapping("/index")
	    public String index(Model model) {

	        return "/user/index";
	    }
	  
	  @RequestMapping("/userlist")
	    public String userLstr(Model model) {
		  List<User> userList = this.userServicy.findAllUsers();
		  model.addAttribute("users", userList);
		  return "/user/userlist";
	  }
	  
	  @RequestMapping("/toAddUser")
	    public String toAddUser() {
		  System.out.println("toAddUser:=======");
		  return "/user/admin/addUser";
	  }
	  
	  @RequestMapping("/addUser")
	    public ModelAndView addUser(String name,String password,String role) {
		  ModelAndView mav = new ModelAndView();
		  try {
			  String encodePassword = MD5Util.encode(password);
		        User user = new User(name,encodePassword);
		        List<Role> roles = new ArrayList<>();
		        Role role1 = new Role();
		        role1.setRolename(role);
		        roles.add(role1);
		        user.setRoles(roles);
		        System.out.println("user:======="+user);
		        userServicy.saveUser(user);
		        
		        
		        
		        
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }finally
		  {
			  mav.setViewName("/user/userlist");
			  List<User> users = userServicy.findAllUsers();
			  mav.addObject("users", users);
		  }
	        
	            return mav;
	    }

	  @GetMapping("/index/delete/{id}")	
	    public String deleteUser(@PathVariable Long id){
		  userServicy.deleteUserById(id);
	        return "redirect:/user/userlist";
	    }
	    @GetMapping("/index/{id}")
	    public String getUser(Model model,@PathVariable Long id) {
	        User user = userServicy.findUserById(id);
	        System.out.println("用户信息:"+user);
	        model.addAttribute("user", user);
	        return "/user/userInfo";
	    }

}
