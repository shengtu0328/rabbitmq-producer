package com.xrq.rabbitmq.rabbitmqproducer;

import com.xrq.rabbitmq.rabbitmqproducer.entity.Order;
import com.xrq.rabbitmq.rabbitmqproducer.producer.OrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProducerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;

    @Test
    public void testOrdersender()throws Exception{

        Order order= new Order();
        order.setId("20180831");
        order.setName("测试订单");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderSender.send(order);
    }

}
