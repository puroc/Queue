package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class HighProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", GlobalConfig.KAFKA_HOSTS_AND_PORT);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		// props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, );
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer producer = new KafkaProducer(props);
		for (int i = 1300; i < 1400; i++)
			producer.send(new ProducerRecord<String, String>("haha", Integer.toString(i), Integer.toString(i)));

		producer.close();
	}

}
