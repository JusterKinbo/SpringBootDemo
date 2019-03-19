package demo.juster.spboot.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;;

@Controller
public class ErrorPageAction {
    @RequestMapping(value = "/error400Page")
    public String error400Page() {
        return "errors/404";
    }
    @RequestMapping(value = "/error401Page")
    public String error401Page() {
        return "errors/401";
    }
    @RequestMapping(value = "/error404Page")
    public String error404Page(Model model) {
        model.addAttribute("code","6666666");
        model.addAttribute("msg","服务器降级中......");
        return "errors/404";
    }
    @RequestMapping(value = "/error403Page")
    public String erro403Page(Model model) {
        return "errors/403";
    }
    
    @RequestMapping(value = "/error500Page")
    public String error500Page(Model model) {
        return "errors/500";
    }
}