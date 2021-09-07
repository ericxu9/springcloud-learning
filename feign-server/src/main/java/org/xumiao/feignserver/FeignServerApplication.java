package org.xumiao.feignserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@EnableDiscoveryClient
@SpringBootApplication
@Controller
public class FeignServerApplication {

    /** config server value */
    @Value("${app.username}")
    private String userName;

    @Autowired
    private DiscoveryClient client;

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        List<ServiceInstance> instances = client.getInstances("feign-server");
        ServiceInstance selectedInstance = instances
                .get(new Random().nextInt(instances.size()));
        return "userName:" + userName + " Hello World: " + selectedInstance.getServiceId() + ":" + selectedInstance
                .getHost() + ":" + selectedInstance.getPort();
    }

}
