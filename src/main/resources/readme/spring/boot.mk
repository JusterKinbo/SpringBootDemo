### springBoot配置文件application.properties
[reference](https://blog.csdn.net/yuchao2015/article/details/52588407)  


### 上传文件
[reference](https://blog.csdn.net/weixin_42047790/article/details/84798170)  
[403问题](springboot +security post请求报403)

### @import @importResource
我们可以在SpringBoot的启动类上添加这个注解并在注解的locations属性中指定xml配置文件。（可以使用一个文件集合也可以只引入主配置文件然后在主配置文件中使用<import>标签引入其他子配置文件，个人更喜欢第二中方式）。这样容器在启动时配置在xml文件中的BeanDefination也可以被解析。
[SpringBoot 默认是通过Java代码进行依赖注入，但也为xml形式的依赖注入提供了入口，就是@ImportResource注解](https://blog.csdn.net/xiaoliuliu2050/article/details/54693154/)