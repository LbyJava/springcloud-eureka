package com.lby.springcloudeurekaconsumer.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: TSF
 * @Description:
 * @Date: Create in 2018/12/27 16:58
 */
public class RestUtils {
    /**
     * 给url发送get请求
     *
     * @param serviceInstance 可以获取的服务实现
     * @param rest            RESTful请求地址，不含端口与ip
     * @param result          返回list类型
     * @param <T>
     * @return 返回一个得到的list
     */
    public static <T> List<T> getUrlList(ServiceInstance serviceInstance, String rest, Class<T> result) {
        Tmp tmp = new Tmp<Object>(serviceInstance, rest).invoke();
        //springMvc RestTemplate
        RestTemplate restTemplate = tmp.getRestTemplate();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(tmp.getHttpHeaders());

        //ResponseEntity:封装了返回值信息
        ResponseEntity<List<T>> listResponseEntity = restTemplate.exchange(tmp.getStringBuffer().toString(), HttpMethod.GET, request, tmp.getParameterizedTypeReference());

        List<T> list = listResponseEntity.getBody();
        return list;
    }

    /**
     * 给url发送post请求获取List
     *
     * @param serviceInstance 可以获取的服务实现
     * @param rest            RESTful请求地址，不含端口与ip
     * @param pojo            需要传递的实体类
     * @param result          返回list类型
     * @param <T>
     * @return 返回一个得到的list
     */
    public static <T> List<T> postUrlList(ServiceInstance serviceInstance, String rest, Object pojo, Class<T> result) {
        //获取一个封装了模板的内部类
        Tmp tmp = new Tmp<Object>(serviceInstance, rest).invoke();
        RestTemplate restTemplate = tmp.getRestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(tmp.getStringBuffer().toString(), pojo, String.class);

        List<T> re = JsonUtils.jsonToList(response.getBody(), result);
        return re;
    }

    /**
     * 给url发送post请求获取一个pojo
     * @param serviceInstance 可以获取的服务实现
     * @param rest RESTful请求地址，不含端口与ip
     * @param pojo 需要传递的实体类
     * @param result 返回list类型
     * @param <T>
     * @return 返回一个得到的list
     */
//    public static <T> T postUrlObject(ServiceInstance serviceInstance,String rest,Object pojo,Class<T> result) {
//        //获取一个封装了模板的内部类
//        Tmp tmp = new Tmp<Object>(serviceInstance, rest).invoke();
//        RestTemplate restTemplate = tmp.getRestTemplate();
//
//        ResponseEntity<String> response = restTemplate.postForEntity(tmp.getStringBuffer().toString(), pojo, String.class);
//
//        T re = JsonUtils.jsonToPojo(response.getBody(), result);
//        return re;
//    }


    private static class Tmp<T> {
        private ServiceInstance serviceInstance;
        private String rest;
        private StringBuffer stringBuffer;
        private HttpHeaders httpHeaders;
        private RestTemplate restTemplate;
        private ParameterizedTypeReference<List<T>> parameterizedTypeReference;

        public Tmp(ServiceInstance serviceInstance, String rest) {
            this.serviceInstance = serviceInstance;
            this.rest = rest;
        }

        public HttpHeaders getHttpHeaders() {
            return httpHeaders;
        }

        public StringBuffer getStringBuffer() {
            return stringBuffer;
        }

        public RestTemplate getRestTemplate() {
            return restTemplate;
        }

        public ParameterizedTypeReference<List<T>> getParameterizedTypeReference() {
            return parameterizedTypeReference;
        }

        public Tmp invoke() {
            ServiceInstance instance = serviceInstance;
            stringBuffer = new StringBuffer();
            //拼接URL
            stringBuffer.append("http://").append(instance.getHost()).append(":").append(instance.getPort()).append("/" + rest);
            //springMvc RestTemplate
            restTemplate = new RestTemplate();
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            parameterizedTypeReference = new ParameterizedTypeReference<List<T>>() {
            };
            return this;
        }
    }
}
