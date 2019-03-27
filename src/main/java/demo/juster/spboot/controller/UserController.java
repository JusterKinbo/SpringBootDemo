package demo.juster.spboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.service.interfaces.IUserService;

@Controller
@RequestMapping("/usr")
public class UserController {

	private IUserService userService;
	
	@Autowired
	public UserController(IUserService userService)
	{
		this.userService = userService;
	}
	

	@RequestMapping("/save")
    @ResponseBody
    String saveUser()
    {
		User u = new User();
		u.setName("我是谁123");
		u.setPwd("123456");
		User u1 = this.userService.findUserByName(u.getUsername());
		if(null != u1)
		{
			 return "name complicated!";
		}
		
		this.userService.saveUser(u);
		return "saved";
    }
	
	@RequestMapping(value="/info/{userName}",method=RequestMethod.GET)
    @ResponseBody
    String home(@PathVariable("userName") String userName)
    {
		
			this.userService.findUserByName(userName);
		
        return "Hello World!xyz";
    }
	
//	@RequestMapping(value="/{userName}",method=RequestMethod.GET)
//	public String userName(@PathVariable("userName") String userName,Model model)
//	{
//		
//		return "/user/list";//考虑前后缀
//	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userName(Model model)
	{
		List<User> userList = this.userService.findAllUsers();
		model.addAttribute("users", userList);
		for(User u : userList)
		{
			System.out.println(u);
		}
		
		return "/user/list";//考虑前后缀
	}
	
	
}
