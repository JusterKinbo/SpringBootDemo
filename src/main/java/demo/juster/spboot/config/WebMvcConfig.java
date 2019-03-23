package demo.juster.spboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import demo.juster.spboot.interceptors.FirstInterceptor;
import demo.juster.spboot.interceptors.TwoInterceptor;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置登录处理操作
        registry.addViewController("/login").setViewName("/user/login");
    }
    
    @Override //配置自己的 path 匹配规则-->可以设置path前后缀
    public void configurePathMatch(PathMatchConfigurer configurer) {
    	// TODO Auto-generated method stub
    	super.configurePathMatch(configurer);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	// TODO Auto-generated method stub
    	registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/user/*").excludePathPatterns("/user/login");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/user/*").excludePathPatterns("/user/login");
    	super.addInterceptors(registry);
    }
}
