package org.xumiao.feignclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableFeignClients
@RestController
@SpringBootApplication
public class FeignClientApplication {

	@Autowired
	private HelloClient helloClient;

	@RequestMapping("/")
	public String hello() {
		return helloClient.hello();
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}

	@FeignClient("feign-server")
	interface HelloClient {
		@RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = "text/plain;charset=UTF-8")
		String hello();
	}
}
