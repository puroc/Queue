package com.example.zeromq.pipeline;

import org.zeromq.ZMQ;

public class Push1 {
	public static void main(String[] args) {
		try {
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket socket = context.socket(ZMQ.PUSH);
			socket.bind("tcp://*:5557");
			boolean b = true;
			for (int i = 0; b; i++) {
				Thread.sleep(1000);
				socket.send("hello " + i, 0);
			}
			socket.close();
			context.term();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
