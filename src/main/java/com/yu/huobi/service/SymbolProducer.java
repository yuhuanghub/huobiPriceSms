package com.yu.huobi.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author Yu
 * @create 2019/6/19
 * @since 1.0.0
 */
@Service
public class SymbolProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    void sendMsg(String destinationName, Map<String, String> map) {
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, map);
    }
}