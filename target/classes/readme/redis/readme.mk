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

