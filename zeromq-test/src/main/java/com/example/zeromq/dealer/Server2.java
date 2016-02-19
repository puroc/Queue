package com.example.zeromq.dealer;

import org.zeromq.ZMQ;

public class Server2 {

	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);
		final ZMQ.Socket socket = context.socket(ZMQ.DEALER);
		socket.connect("tcp://localhost:5557");
		boolean b = true;
		while (b) {
			final String string = new String(socket.recv(0)).trim();
			System.out.println(Server2.class.getName() + " receive " + string + '.');
			socket.send(Server2.class.getName() + " reply for " + string);
		}
		socket.close();
		context.term();
	}

}
