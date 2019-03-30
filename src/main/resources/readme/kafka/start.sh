#!/bin/sh
sh ~/tools/kafka_2.11-2.1.0/bin/kafka-server-start.sh ~/tools/kafka_2.11-2.1.0/config/server0.properties 
sh ~/tools/kafka_2.11-2.1.0/bin/kafka-server-start.sh ~/tools/kafka_2.11-2.1.0/config/server1.properties 
sh ~/tools/kafka_2.11-2.1.0/bin/kafka-server-start.sh ~/tools/kafka_2.11-2.1.0/config/server2.properties 

# test
# sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
# sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
