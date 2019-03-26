package demo.juster.spboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import demo.juster.spboot.error.ErrorConfig;

@Controller
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan("demo.juster.spboot")//因默认扫描启动类目录，因此加入componentScan
@MapperScan("demo.juster.spboot.mapper") //扫描的mapper
@ServletComponentScan//自定义filter加入
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
	    //一个配置类中声明该Bean
	    @Bean
	    public ErrorPageRegistrar errorPageRegistrar(){
	        return new ErrorConfig();
	    }
	    

}

