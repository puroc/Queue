package com.example.zeromq.answer;

import org.zeromq.ZMQ;

public class EchoClient {

	static {
		// 如果使用jzmq，需要设置java.library.path，但代码中设置java.library.path是不生效的，因为jvm只有在启动的时候读取一次，只有-Djava.library.path=/usr/local/lib这种方式生效
		// System.setProperty("java.library.path", "/usr/local/lib");
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket socket = context.socket(ZMQ.REQ);

			System.out.println("Connecting to hello world server...");
			socket.connect("tcp://127.0.0.1:5557");
			String requestString = "Hello" + " ";
			byte[] request = requestString.getBytes();
			socket.send(request, 0);
			byte[] reply = socket.recv(0);
			System.out.println("Received reply [" + new String(reply) + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
