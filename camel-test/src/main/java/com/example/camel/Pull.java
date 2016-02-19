package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Pull {

	public static void main(String[] args) {
		try {
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(new ZeromqPullExampleRouteBuilder());
			context.start();
			Thread.sleep(1000000000);
			context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}