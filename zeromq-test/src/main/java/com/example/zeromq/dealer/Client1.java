package com.example.zeromq.dealer;

import org.zeromq.ZMQ;

public class Client1 {

	public static void main(String[] args) {
		try {
			ZMQ.Context context = ZMQ.context(1);
			final ZMQ.Socket socket = context.socket(ZMQ.DEALER);
			socket.bind("tcp://192.168.1.124:5557");
			new Thread(new Runnable() {

				public void run() {
					while (true) {

						//比较奇怪，有的时候收不到消息（即时对端不断的发送消息，并且对端显示发送消息成功）
						byte[] recv = socket.recv(ZMQ.DONTWAIT);
						if (recv == null) {
							try {
								System.out.println("null");
								Thread.sleep(1000);
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
			// Thread.sleep(5000);
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
