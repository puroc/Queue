package com.example.zeromq.dealer;

import org.zeromq.ZMQ;

public class Server2 {

	private static final String HOST = "192.168.1.230";
	// private static final String HOST = "localhost";

	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);
		final ZMQ.Socket socket = context.socket(ZMQ.DEALER);
		socket.connect("tcp://" + HOST + ":5557");
		boolean b = true;
		while (b) {
			final String string = new String(socket.recv(0)).trim();
			System.out.println(Server2.class.getName() + " receive " + string + '.');
			boolean result = socket.send(Server2.class.getName() + " reply for " + string, ZMQ.DONTWAIT);
			if (!result) {
				System.out.println("send reply for " + string + " failed.");
			} else {
				System.out.println("successfully");
			}
			// new Thread(new Runnable() {
			//
			// public void run() {
			// }
			// }).start();
		}
		socket.close();
		context.term();
	}

}
