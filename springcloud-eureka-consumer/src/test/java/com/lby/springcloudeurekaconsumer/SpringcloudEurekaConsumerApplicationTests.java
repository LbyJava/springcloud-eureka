package com.lby.springcloudeurekaconsumer;

import com.lby.springcloudeurekaconsumer.config.RestUtils;
import com.lby.springcloudeurekaconsumer.pojo.Demo;
import com.lby.springcloudeurekaconsumer.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudEurekaConsumerApplicationTests {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.USER_AGENT, "useragent");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ParameterizedTypeReference<Demo> type = new ParameterizedTypeReference<Demo>() {};
        ResponseEntity<Demo> exchange = restTemplate.exchange("https://www.sojson.com/api/gongan/baidu.com", HttpMethod.GET, request, type);
        Demo body = exchange.getBody();
        System.out.println("body = " + body);
    }

//    @Test
//    public void testPostUrlObject() {
//        User user = new User();
//        user.setName("Eriri");
//        user.setPass("5433");
//        user.setAge(17);
//        user = RestUtils.postUrlObject(loadBalancerClient.choose("eureka-provider"), "post/user", user, User.class);
//        System.out.println(user);
//    }
}
