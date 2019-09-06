package com.xrq.rabbitmq.task;

import com.alibaba.fastjson.JSON;
import com.xrq.rabbitmq.constant.Constants;
import com.xrq.rabbitmq.entity.BrokerMessageLog;
import com.xrq.rabbitmq.entity.Order;
import com.xrq.rabbitmq.mapper.BrokerMessageLogMapper;
import com.xrq.rabbitmq.producer.RabbitOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class RetryMessageTasker {
    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    //项目启动5秒钟之后，每隔10秒钟执行此任务
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend() {
        System.err.println("定时任务开启：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //pull status = 0 and timeout message
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        if (list.size() > 0) {
            System.err.println("发现到了未按时投递的订单记录");
        }
        list.forEach(messageLog -> {
            if (messageLog.getTryCount() >= 3) {
                //update fail message
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
            } else {
                // resend
                brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(), new Date());
                Order reSendOrder = JSON.parseObject(messageLog.getMessage(), Order.class);
                try {
                    rabbitOrderSender.sendOrder(reSendOrder);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("-----------异常处理-----------");
                }
            }
        });
    }

}
