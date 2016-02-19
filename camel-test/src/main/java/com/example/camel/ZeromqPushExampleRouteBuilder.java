package com.example.camel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Stephen K Samuel samspade79@gmail.com Sep 2, 2012 2:42:45 AM
 * 
 */
public class ZeromqPushExampleRouteBuilder extends RouteBuilder {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void configure() throws Exception {
		from("timer://foo?fixedRate=true&period=1000").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				List<String> asList = Arrays.asList("coldplay", "keane", "jethro tull", "jack bruce", "elton john",
						"kate bush");
				Collections.shuffle(asList);
				logger.debug("Pushing message {}", asList.get(0));
				exchange.getIn().setBody("bands " + asList.get(0));
				System.out.println("push msgId:"+exchange.getIn().getMessageId());
				// ZeromqSocketType.DEALER
			}
		}).to("zeromq:tcp://127.0.0.1:5557?socketType=PUSH");
	}

}
