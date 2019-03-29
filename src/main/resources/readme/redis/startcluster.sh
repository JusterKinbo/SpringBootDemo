#!/bin/sh
redis-server /Users/Juster/JKBdata/redis-cluster/7001/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis-cluster/7002/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis-cluster/7003/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis-cluster/7004/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis-cluster/7005/conf/redis.conf
redis-server /Users/Juster/JKBdata/redis-cluster/7006/conf/redis.conf

# gem install  redis --version 3.0.0

./redis-trib.rb create --replicas 1 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005  127.0.0.1:7006