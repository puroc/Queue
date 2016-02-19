package com.example.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Stephen K Samuel samspade79@gmail.com 28 Aug 2012 00:06:16
 * 
 */
public class ZeromqPullExampleRouteBuilder extends RouteBuilder {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Counter counter = new Counter();

	public ZeromqPullExampleRouteBuilder() {
		counter.start();
	}

	@Override
	public void configure() throws Exception {
		from("zeromq:tcp://127.0.0.1:5557?socketType=PULL").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				counter.add();
				logger.info("Message received [{}]", exchange.getIn().getBody());
			}
		});
	}
}
