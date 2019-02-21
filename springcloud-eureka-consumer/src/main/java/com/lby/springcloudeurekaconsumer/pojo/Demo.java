package com.lby.springcloudeurekaconsumer.pojo;

/**
 * @Author: TSF
 * @Description:
 * @Date: Create in 2018/12/27 23:37
 */
public class Demo {

    /**
     * data : {"id":"11000002000001","sitename":"百度","sitedomain":"baidu.com","sitetype":"交互式","cdate":"2016-07-13","comtype":"企业单位","comname":"北京百度网讯科技有限公司","comaddress":"北京市网安总队","updateTime":"2018-12-23"}
     * status : 200
     */

    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "data=" + data +
                ", status=" + status +
                '}';
    }


}
