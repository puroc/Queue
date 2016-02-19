package com.example.zeromq.pipeline;

import org.zeromq.ZMQ;

public class Pull1 {

	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket receiver = context.socket(ZMQ.PULL);
		receiver.connect("tcp://localhost:5557");
		boolean b = true;
		while (b) {
			String string = new String(receiver.recv(0)).trim();
			System.out.println(Pull1.class.getName() + " receive " + string + '.');
		}
		receiver.close();
		context.term();
	}
}
