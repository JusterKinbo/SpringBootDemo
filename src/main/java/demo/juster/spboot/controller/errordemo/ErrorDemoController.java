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


/*
 * @ControllerAdvice 拦截异常并统一处理:  https://www.cnblogs.com/magicalSam/p/7198420.html
 * Spring Boot 自定义错误页面:		https://www.jianshu.com/p/7c94d1ac2092
 * springboot配置错误页面及全局异常		https://www.cnblogs.com/wangzhuxing/p/10158390.html#_label3_0
 * 
 * 
 */