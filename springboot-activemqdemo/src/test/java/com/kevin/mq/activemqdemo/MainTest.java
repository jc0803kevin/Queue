package com.kevin.mq.activemqdemo;

import com.kevin.mq.activemqdemo.utill.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author kevin
 * @Description 发送消息测试
 * @Date Created on 2019/8/2 9:41
 */
public class MainTest {

    public static void main(String[] args) {
        MainTest mainTest =  new MainTest();

        for (int i=0;i<1000;i++){
            mainTest.test();
        }

    }


    public void test(){
        try {
            String cocorespResult = HttpUtil.post( "http://localhost:9090/coco", "msg="+System.currentTimeMillis());
            System.err.println("coco  respResult---->" + cocorespResult);


            String kevinrespResult = HttpUtil.post( "http://localhost:9090/kevin", "msg="+System.currentTimeMillis());
            System.out.println("kevin  respResult---->" + kevinrespResult);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
