#!/bin/sh
redis-server /Users/Juster/JKBdata/redis/6380/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis/6381/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis/6382/conf/redis.conf

# "启动redis"

ps -ef|grep redis

redis-sentinel /Users/Juster/JKBdata/redis/sentinel/26001/sentinel.conf 

# "启动redis哨兵"

ps -ef|grep redis