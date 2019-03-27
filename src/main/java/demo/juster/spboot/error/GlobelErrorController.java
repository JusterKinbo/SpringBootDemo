package demo.juster.spboot.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/*
 * pringboot中可以使用ControllerAdvice和ExceptionHandler这两个注解来做全局异常，这种方式比较便捷，但是也有一个问题： 
 * ContollerAdvice只能拦截控制器中的异常，换言之，只能拦截500之类的异常，但是对于404这样不会进入控制器处理的异常不起作用。
 * 仿造springboot默认的全局处理类BasicController实现全局的异常处理，这样就能很好的按照自己的需求处理异常了。
 * 参考：https://blog.csdn.net/qq_29684305/article/details/82286469
 * */

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
@Slf4j
public class GlobelErrorController extends AbstractErrorController{
    private final ErrorProperties errorProperties;
    @Autowired
    public GlobelErrorController(ErrorAttributes errorAttributes,ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties=serverProperties.getError();
       log.info("info");
    }

    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView=new ModelAndView("/errors/error");
        Map<String, Object> errorMap=getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        if(errorMap!=null) {
            /*timestamp status error message path*/
            modelAndView.addObject("message",errorMap.get("error"));
            modelAndView.addObject("statusCode",errorMap.get("status"));
            modelAndView.addObject("url",errorMap.get("path"));
            System.out.println("error text/html");
            logHandler(errorMap);
            if (!Strings.isNullOrEmpty((String)errorMap.get("exception")) && errorMap.get("exception").equals(CustomException.class.getName())){
            	errorMap.put("status", HttpStatus.FORBIDDEN.value());
//                status = HttpStatus.FORBIDDEN;
//                return new ResponseEntity<Map<String, Object>>(body, status);
//            	参考https://segmentfault.com/a/1190000008443705
//            	项目中对exception采用了全局捕获，因此无法走入该环节
            }
        }
        return modelAndView;
    }

    @RequestMapping
	@ResponseBody
	public Map<String, Object> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
//		HttpStatus status = getStatus(request);
		logHandler(body);
		 Map<String, Object> map = new HashMap<String, Object>();
	        map.put("code",""+ body.get("status"));
	        map.put("msg", ""+body.get("message"));
		return map;
	}
    
    private void logHandler(Map<String, Object> errorMap) {
    	System.out.println("" + errorMap.get("path") + errorMap.get("status") + errorMap.get("timestamp") + errorMap.get("message"));
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request,
            MediaType produces) {
        IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }
    private ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}
