package com.example.zeromq.topic;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Sub1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.SUB);
		socket.connect("tcp://127.0.0.1:5557");
		socket.subscribe("B".getBytes());
		while (true) {
			System.out.println(new String(socket.recv(0)));
			System.out.println(new String(socket.recv(0)));
		}
	}

}