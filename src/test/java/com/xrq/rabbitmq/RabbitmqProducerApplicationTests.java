package com.xrq.rabbitmq;

import com.xrq.rabbitmq.entity.Order;
import com.xrq.rabbitmq.producer.RabbitOrderSender;
import com.xrq.rabbitmq.service.OrderService;
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
    private RabbitOrderSender orderSender;

    /*
    启动producer发送一条消息,
     */
    @Test
    public void testOrdersender() throws Exception {

        Order order = new Order();
        order.setId("20180831");
        order.setName("测试订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderSender.sendOrder(order);
    }


    /*
    高可用测试,
     */
    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId("2018080400000011");
        order.setName("测试创建订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }
}
