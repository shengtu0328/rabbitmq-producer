package com.xrq.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.xrq.rabbitmq.constant.Constants;
import com.xrq.rabbitmq.entity.BrokerMessageLog;
import com.xrq.rabbitmq.entity.Order;
import com.xrq.rabbitmq.mapper.BrokerMessageLogMapper;
import com.xrq.rabbitmq.mapper.OrderMapper;
import com.xrq.rabbitmq.producer.RabbitOrderSender;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    /*
    业务消息入库
    日志消息入库
    */
    public void createOrder(Order order) throws Exception {
        // 插入业务数据
        Date orderTime = new Date();
        orderMapper.insert(order);


        // 插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        // 消息唯一ID
        brokerMessageLog.setMessageId(order.getMessageId());
        // 保存消息整体 转为JSON 格式存储入库
        brokerMessageLog.setMessage(JSON.toJSONString(order));
        // 设置消息状态为0 表示发送中
        brokerMessageLog.setStatus("0");
        // 设置消息未确认超时时间窗口为 一分钟,订单入库后规定1分钟内应该处理完成
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogMapper.insert(brokerMessageLog);
        // 发送消息
        rabbitOrderSender.sendOrder(order);
    }

}
