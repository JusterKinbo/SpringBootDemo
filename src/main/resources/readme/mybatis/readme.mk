### mybatis配置时出现org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)
mapper与interface未能绑定，[解决方案](https://www.cnblogs.com/lfm601508022/p/InvalidBoundStatement.html)
#### 万能方案
1. mapper放入interface同一目录  
2. 分开放：mapper-locations: classpath*:/mappings/**/*.xml

###  parametertype 多个参数
[referece](https://blog.csdn.net/lixld/article/details/77980443)  

### resultMap
[reference](https://www.cnblogs.com/libra0920/p/6208587.html)  
[Mybatis高级-resultMap之collection聚集](https://blog.csdn.net/aodeng110/article/details/82850221)  
[resultMap](http://www.cnblogs.com/kenhome/p/7764398.html)  

### namespace属性说明
[reference](https://blog.csdn.net/qq_33530388/article/details/71194518)  

### mybatis常用jdbcType数据类型
[reference](https://www.cnblogs.com/lixuwu/p/5916585.html)  

### mysql delete别名
delete ur from user_roles ur where ur.user_id=1 and ur.roles_id=2;
[reference](https://www.cnblogs.com/wuyun-blog/p/6178303.html)

### 多表连接删除功能
[reference](https://www.jb51.net/article/107813.htm)

