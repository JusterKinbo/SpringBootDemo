### Model、ModelMap和ModelAndView
最大的区别，那就是Model是每一次请求可以自动创建，但是ModelAndView 是需要我们自己去new的。  
ModelAndView方法,添加模型数据用addObject, setViewName  
[referce](https://blog.csdn.net/itbiggod/article/details/79685610)  

### @SpringBootApplication
@Configuration（@SpringBootConfiguration点开查看发现里面还是应用了@Configuration）  
@EnableAutoConfiguration  
@ComponentScan  
[referce](https://blog.csdn.net/zxc123e/article/details/80222967)

#### @EnableAutoConfiguration
最关键的要属@Import(AutoConfigurationImportSelector.class)，借助AutoConfigurationImportSelector，@EnableAutoConfiguration可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器    
借助于Spring框架原有的一个工具类：SpringFactoriesLoader的支持，@EnableAutoConfiguration可以智能的自动配置功效才得以大功告成！  

#### @ConfigurationProperties
有时候有这样子的情景，我们想把配置文件的信息，读取并自动封装成实体类，这样子，我们在代码里面使用就轻松方便多了，这时候，我们就可以使用@ConfigurationProperties  
[referce](https://www.cnblogs.com/liaojie970/p/8043150.html)

#### @Value("#{}")与@Value("${}")的区别
@Value(“#{}”) 表示SpEl表达式通常用来获取bean的属性，或者调用bean的某个方法。当然还有可以表示常量  
	当bean通过@Value(#{“”}) 获取其他bean的属性，或者调用其他bean的方法时，只要该bean (Beab_A)能够访问到被调用的bean(Beab_B)，即要么Beab_A 和Beab_B在同一个容器中，或者Beab_B所在容器是Beab_A所在容器的父容器  
@Value(“${xxxx}”)注解从配置文件读取值  
[referce](https://blog.csdn.net/chuang504321176/article/details/80672740)

#### @Configuration.properties与@Value的区别
	@ConfigurationProperties	@Value  
功能	批量注入配置文件中属性	一个个指定  
松散绑定	支持	不支持   
SPEL	不支持	支持   
JSR303数据校验	支持	不支持  
复杂类型封装	支持	不支持  
[referce](https://blog.csdn.net/weixin_41113108/article/details/82590103)  


