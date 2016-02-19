package com.example.zeromq.answer;

import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import com.example.zeromq.Counter;

public class EchoServer {

	public static void main(String[] argv) {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		String url = "tcp://127.0.0.1:5557";
		// String url = "tcp://*:9999";
		try {
			socket.bind(url);
		} catch (ZMQException e) {
			throw e;
		}
		System.out.println("EchoServer start");
		boolean wait = true;
		while (wait) {
			byte[] request;
			try {
				request = socket.recv(0);
				System.out.println(new String(request));
				socket.send("OK".getBytes(), 1);
			} catch (ZMQException e) {
				throw e;
			}
		}
	}
}
