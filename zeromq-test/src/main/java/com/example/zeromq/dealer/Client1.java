package com.example.zeromq.dealer;

import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import com.example.zeromq.Counter;

public class Client1 {

	// private static final String HOST = "192.168.1.230";
	private static final String HOST = "localhost";

	public static void main(String[] args) {
		try {
			ZMQ.Context context = ZMQ.context(1);
			final ZMQ.Socket socket = context.socket(ZMQ.DEALER);
			socket.bind("tcp://" + HOST + ":5557");
			new Thread(new Runnable() {

				public void run() {
					while (true) {
						byte[] recv = socket.recv(ZMQ.DONTWAIT);
						if (recv == null) {
							try {
								Thread.sleep(1);
								continue;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						String string = new String(recv).trim();
						System.out.println("receive " + string);
					}
				}
			}).start();
			boolean b = true;
			for (int i = 0; b; i++) {
				Thread.sleep(2000);
				socket.send("hello " + i, 0);
			}
			socket.close();
			context.term();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
