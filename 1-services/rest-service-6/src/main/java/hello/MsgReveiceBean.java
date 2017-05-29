/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import rx.Observable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author Dave Syer
 *
 */
//@EnableBinding(Sink.class)
@EnableBinding(Processor.class)
public class MsgReveiceBean {

	private static Logger logger = LoggerFactory.getLogger(MsgReveiceBean.class);

//	@StreamListener(Sink.INPUT)
//	public void loggerSink(Object payload) {
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("message received: " + payload);
//	}

//	@StreamListener(Sink.INPUT)
	@StreamListener(Processor.INPUT)
	public void processor(Observable<String> inputStream) {
		Observable<String> os = inputStream.map(data -> {
			logger.info("Got message stream data = " + data);
			return data;
		});
				
		//.buffer(5).map(data -> String.valueOf(avg(data)));
		logger.info("message received complete");
	}

}
