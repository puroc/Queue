/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.jmstofile;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apacheextras.camel.component.zeromq.ZeromqComponent;

/**
 * An example class for demonstrating some of the basics behind Camel. This
 * example sends some text messages on to a JMS Queue, consumes them and
 * persists them to disk
 */
public final class CamelZeromqToFileExample {

	private CamelZeromqToFileExample() {
	}

	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		ZeromqComponent zc = new ZeromqComponent();
		context.addComponent("zeromq", zc);
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				// from("zeromq:tcp://127.0.0.1:5555?socketType=SUBSCRIBE&topics=haha")
				// .to("zeromq:tcp://127.0.0.1:9999?socketType=PUBLISH");
				from("zeromq:tcp://127.0.0.1:5555?socketType=SUBSCRIBE&topics=haha").to("file:/Users/puroc/haha");
			}
		});
		ProducerTemplate template = context.createProducerTemplate();
		context.start();
		// Now send some test text to a component - for this case a JMS Queue
		// The text get converted to JMS messages - and sent to the Queue
		// test.queue
		// The file component is listening for messages from the Queue
		// test.queue, consumes
		// them and stores them to disk. The content of each file will be the
		// test we sent here.
		// The listener on the file component gets notified when new files are
		// found ... that's it!
		// START SNIPPET: e5
		for (int i = 0; i < 10; i++) {
			template.sendBody("zeromq:tcp://127.0.0.1:5555?socketType=PUBLISH&topics=haha", "hahaha: " + i);
		}
		// END SNIPPET: e5

		// wait a bit and then stop
		Thread.sleep(1000000000);
		context.stop();
	}
}
