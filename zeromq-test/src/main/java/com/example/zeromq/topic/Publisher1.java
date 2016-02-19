package com.example.zeromq.topic;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Publisher1 {

	public static void main(String[] args) throws InterruptedException {
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);
		publisher.bind("tcp://127.0.0.1:5557");
		for (int i = 0; true; i++) {
			Thread.currentThread().sleep(1000);
			// publisher.send("A".getBytes(), ZMQ.SNDMORE);
			// publisher.send("This is A".getBytes(), 0);
			// publisher.send("BC".getBytes(), ZMQ.SNDMORE);
			publisher.send((("B " + i) + " from " + Publisher1.class.getName()).getBytes(), 0);
		}
	}

}