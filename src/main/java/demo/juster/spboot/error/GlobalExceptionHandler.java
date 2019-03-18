package demo.juster.spboot.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/*
 * 异常不同于请求error
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
	        ModelAndView mav = new ModelAndView("error"); // 设置跳转路径
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
}
