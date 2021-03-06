package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class MsgSendingBean {

	private Source source;

	@Autowired
	public MsgSendingBean(Source source) {
		this.source = source;
	}

	public void sayHello(double val) {
		source.output().send(MessageBuilder.withPayload(val).build());
	}
}



//@Component
//@EnableBinding(CustomSource.class)
//public class MsgSendingBean {
//
//	@Autowired
//	private CustomSource customSource;
//
//	public void sayHello(String name) {
//		customSource.output().send(MessageBuilder.withPayload(name).build());
//	}
//
//}
//
//interface CustomSource {
//	String OUTPUT = "customoutput";
//	@Output(CustomSource.OUTPUT)
//	MessageChannel output();
//}