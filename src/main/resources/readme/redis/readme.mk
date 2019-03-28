### 单机搭建
[搭建](https://www.cnblogs.com/bestmystery/p/6371229.html)  
启动
sudo redis-server /usr/local/etc/redis.conf 
查看启动
ps -ef | grep redis 
使用客户端
redis-cli
关闭客户端
redis-cli shutdown
redis-cli -p 端口号 shutdown

# 目录
单个redis etc/redis.conf 6379
单机群多实例 /Users/Juster/JKBdata/redis/6380 /Users/Juster/JKBdata/redis/6381 /Users/Juster/JKBdata/redis/6382

### Spring 集成Redis
[Spring 集成Redis 单机 、多集群](https://blog.csdn.net/weixin_38497513/article/details/81360848)  


### redis 单机、多集群
[Jedis整合spring的使用](https://blog.csdn.net/weixin_38497513/article/details/81360848)
[redis单机及其集群的搭建](https://www.cnblogs.com/mouseIT/p/5288204.html)
[单机多实例](https://www.cnblogs.com/lgeng/p/6623336.html)

### SpringBoot整合redis哨兵主从服务
[主从配置](https://www.cnblogs.com/zwcry/p/9156243.html)  
[哨兵配置](https://www.cnblogs.com/zwcry/p/9134721.html)    
[SpringBoot整合redis哨兵主从服务](https://www.cnblogs.com/zwcry/p/9156243.html)  c
