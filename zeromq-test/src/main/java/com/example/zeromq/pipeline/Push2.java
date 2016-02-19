package com.example.zeromq.pipeline;

import org.zeromq.ZMQ;

public class Push2 {
	public static void main(String[] args) {
		try {
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket sender = context.socket(ZMQ.PUSH);
			sender.bind("tcp://*:5557");
			sender.send("hellow", 0);
			Thread.sleep(100000000);
			sender.close();
			context.term();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
