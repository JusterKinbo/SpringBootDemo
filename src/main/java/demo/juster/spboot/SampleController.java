package demo.juster.spboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan("demo.juster.spboot.controller")//因默认扫描启动类目录，因此加入componentScan
public class SampleController {
	
	  	@RequestMapping("/")
	    @ResponseBody
	    String home()
	    {
	        return "Hello World!";
	    }
	  	

	    public static void main(String[] args) throws Exception
	    {
	        SpringApplication.run(SampleController.class, args);
	    }

}

