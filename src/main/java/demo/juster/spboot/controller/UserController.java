package demo.juster.spboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import demo.juster.spboot.pojo.dao.UserRepository;
import demo.juster.spboot.pojo.user.User;

@Controller
@RequestMapping("/usr")
public class UserController {

	private UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	

	@RequestMapping("/save")
    @ResponseBody
    String saveUser()
    {
		User u = new User();
		u.setUsername("我是谁123");
		u.setPwd("123456");
		User u1 = this.userRepository.findByUsername(u.getUsername());
		if(null != u1)
		{
			 return "name complicated!";
		}
		
		this.userRepository.saveAndFlush(u);
		return "saved";
    }
	
	@RequestMapping(value="/info/{userName}",method=RequestMethod.GET)
    @ResponseBody
    String home(@PathVariable("userName") String userName)
    {
		
			this.userRepository.findByUsername(userName);
		
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
		List<User> userList = this.userRepository.findAll();
		model.addAttribute("users", userList);
		for(User u : userList)
		{
			System.out.println(u);
		}
		
		return "/user/list";//考虑前后缀
	}
	
	
}
