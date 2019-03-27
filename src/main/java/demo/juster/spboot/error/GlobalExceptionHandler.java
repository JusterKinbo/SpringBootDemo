package demo.juster.spboot.error;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*
 * 异常不同于请求error
 * @ControllerAdvice 主要处理的就是 controller 层的错误信息，而没有进入 controller 层的错误 @ControllerAdvice 是无法处理的，
 * 那么我需要另外的一个全局错误处理。==>参考GlobelController
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
     * 全局异常捕捉处理
     * @param request ex
     * @return
     */
	 @ExceptionHandler(Exception.class) // 所有的异常都是Exception子类
	    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) { // 出现异常之后会跳转到此方法
		 	System.out.println("发生内部错误"+e);
	        ModelAndView mav = new ModelAndView("errors/error"); // 设置跳转路径
//	        mav.setViewName("error");
	        mav.addObject("statcktrace", e.getStackTrace());
	        mav.addObject("exception", e); // 将异常对象传递过去
	        mav.addObject("url", request.getRequestURL()); // 获得请求的路径
	        return mav;
	    }
	 
	 /**
	     * 拦截捕捉自定义异常 MyException.class
	     * @param ex
	     * @return
	     */
	    @ResponseBody
	    @ExceptionHandler(value = CustomException.class)
	    public Map<String, String> myErrorHandler(CustomException ex) {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("code", ex.getCode());
	        map.put("msg", ex.getMsg());
	        return map;
	    }
	    
	    @ExceptionHandler(MultipartException.class)
	    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
	        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
	        return "redirect:/upload/status";
	    }
	   
}
