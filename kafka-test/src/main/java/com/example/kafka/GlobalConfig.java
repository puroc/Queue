package com.example.kafka;

public class GlobalConfig {
	// ./kafka-topics.sh --topic testTopic --create --zookeeper 127.0.0.1:2181
	// --replication-factor 1 --partition 1
	public static final String TOPIC_NAME = "haha";
//	public static final String TOPIC_NAME = "test-topic";
//	public static final String TOPIC_NAME = "testTopic";
	public static final int DATA_NUM = 1000000;
	public static final String KAFKA_HOSTS_AND_PORT = "192.168.1.230:9092";
	public static final String KAFKA_HOST = "192.168.1.230";
	// public static final String KAFKA_HOST = "10.10.105.60";
	public static final String KAFKA_PORT = "9092";

}
