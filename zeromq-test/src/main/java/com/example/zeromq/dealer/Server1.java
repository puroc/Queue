package com.example.zeromq.dealer;

import org.zeromq.ZMQ;

import com.example.zeromq.Counter;

public class Server1 {

	// private static final String HOST = "192.168.1.230";
	private static final String HOST = "localhost";

	public static void main(String[] args) {
		Counter counter = new Counter();
		counter.start();
		ZMQ.Context context = ZMQ.context(1);
		final ZMQ.Socket socket = context.socket(ZMQ.DEALER);
		socket.connect("tcp://" + HOST + ":5557");
		boolean b = true;
		while (b) {
			final String string = new String(socket.recv(0)).trim();
			System.out.println(Server1.class.getName() + " receive " + string + '.');
			counter.add();
			boolean result = socket.send(Server1.class.getName() + " reply for " + string, ZMQ.DONTWAIT);
			if (!result) {
				System.out.println("send reply for " + string + " failed.");
			} else {
				System.out.println("successfully");
			}
		}
		socket.close();
		context.term();
	}

}
