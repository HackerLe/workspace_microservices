package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication

@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@IntegrationComponentScan
public class Application implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private StatusBean statusBean;

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public StatusBean statusBean() {
		return new StatusBean();
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		statusBean.init();
	}

}