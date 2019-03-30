#!/bin/sh
sh ~/tools/zookeeper-3.4.13/bin/zkServer.sh start ~/tools/zookeeper-3.4.13/conf/deploy/z1.cfg
sh ~/tools/zookeeper-3.4.13/bin/zkServer.sh start ~/tools/zookeeper-3.4.13/conf/deploy/z2.cfg
sh ~/tools/zookeeper-3.4.13/bin/zkServer.sh start ~/tools/zookeeper-3.4.13/conf/deploy/z3.cfg
