package com.example.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class HighConsumer {

	// public static void main(String[] args) {
	// Properties props = new Properties();
	// props.put("auto.offset.reset", "smallest"); // 必须要加，如果要读旧数据
	// props.put("zookeeper.connect", "192.168.1.230:2181");
	// // props.put("bootstrap.servers", GlobalConfig.KAFKA_HOSTS_AND_PORT);
	// 在zookeeper的consumers下创建该group.id的节点
	// props.put("group.id", "bbb");
	// props.put("zookeeper.session.timeout.ms", "400");
	// props.put("zookeeper.sync.time.ms", "200");
	// props.put("auto.commit.interval.ms", "1000");
	//
	// ConsumerConfig conf = new ConsumerConfig(props);
	// ConsumerConnector consumer =
	// kafka.consumer.Consumer.createJavaConsumerConnector(conf);
	// String topic = "haha";
	// Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
	// topicCountMap.put(topic, new Integer(1));
	// Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
	// consumer.createMessageStreams(topicCountMap);
	// List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
	//
	// KafkaStream<byte[], byte[]> stream = streams.get(0);
	// ConsumerIterator<byte[], byte[]> it = stream.iterator();
	// while (it.hasNext()) {
	// System.out.println("message: " + new String(it.next().message()));
	// }
	//
	// if (consumer != null)
	// consumer.shutdown();
	// }
	public static void main(String[] args) {
		Properties props = new Properties();
		// 接收之前从未接收的所有数据
		props.put("auto.offset.reset", "earliest");
		props.put("bootstrap.servers", GlobalConfig.KAFKA_HOSTS_AND_PORT);
		// 不会在zookeeper的consumers下创建该group.id的节点
		props.put("group.id", "ccc");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList(GlobalConfig.TOPIC_NAME));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.println(
						"offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
		}
	}

}
