package demo.juster.spboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.juster.spboot.pojo.user.User;
import demo.juster.spboot.service.SecurityService;
import demo.juster.spboot.util.MD5Util;

@Configuration//指定为配置类
@EnableWebSecurity//指定为Spring Security配置类
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    UserDetailsService Service(){
        return new SecurityService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(Service()).passwordEncoder(new PasswordEncoder() {

                //用户加密配置
                @Override
                public String encode(CharSequence charSequence) {
                	System.out.println("encode=======");
                    return MD5Util.encode((String)charSequence);
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                	System.out.println("matches=======");
                    return s.equals(MD5Util.encode((String)charSequence));
                }
            });
    }

    /*
    通过 authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护
    通过 formLogin() 定义当需要用户登录时候，转到的登录页面。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                //设置静态资源均可以访问
        		antMatchers("/css/**","/error*", "/js/**","/errors/**","/images/**","/user/index").permitAll().
                antMatchers("/user/toAddUser").hasRole("ADMIN").//限制的是请求->非管理员操作时Forbidden403，通过user/index下添加用户进行测试
                antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").//任何以"/db/" 开头的URL需要同时具有 "ROLE_ADMIN" 和 "ROLE_DBA"权限的用户才可以访问。
                antMatchers("/login").permitAll().//放行登录
                anyRequest().authenticated().
                and().
                //指定登录认证的Controller
                formLogin().loginPage("/login").permitAll().
                successHandler(loginSuccessHandler()).//登录成功转发接口
                failureHandler(loginFaillureHandler()).//登录失败转发接口
                and().
                logout().permitAll().invalidateHttpSession(true).
                deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
                and().sessionManagement().maximumSessions(3).expiredUrl("/login");
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    User user = (User) authentication.getPrincipal();
                   System.out.println("成功退出: "+user.getName());
                } catch (Exception e) {
                    
                }
                httpServletResponse.sendRedirect("/login");
            }

        };
    }
    
    @Bean
    public AuthenticationFailureHandler loginFaillureHandler() { //登出处理
        return new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				System.out.println("授权失败");
				response.sendRedirect("/error401Page");
			}
		};
    }
    

    public AuthenticationSuccessHandler loginSuccessHandler() { //登出处理
        return new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				System.out.println("授权成功");
				response.sendRedirect("/user/index");
				
			}
		};
    }
    
    
}
