package demo.juster.spboot.controller.errordemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.juster.spboot.error.CustomException;

@Controller
@RequestMapping("/error")
public class ErrorDemoController {

	@RequestMapping(value="/sys",method=RequestMethod.GET)
	public String errSys(Model model) throws Exception
	{
		throw new Exception("sysErr");
	}
	
	@RequestMapping(value="/custom",method=RequestMethod.GET)
	public String cusSys(Model model) throws Exception
	{
		throw new CustomException("100","cusErr");
	}
}
