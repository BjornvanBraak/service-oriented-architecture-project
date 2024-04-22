package com.example.identityAndAccessManagementService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public MessageQueueService(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    //functions to send to the message queue. Format should be a json object that
    //includes an action tag and messageId so the receiver knows what to do with it
    //and the response can be identified by the receiver.

    //message should include action:verifyCustomerReponse, responseToMessageId:id,
    //messageId:id, customerId:id, crukApproved:boolean
    public void respondVerifyCustomer(String message)
    {
        jmsTemplate.convertAndSend("BettingServiceRespondChannel", message);
    }


    //Consuming messages from the message queue. Based on what action tag they have it should be handled.
    @JmsListener(destination = "IdentityServiceRequestChannel")
    public void listener(String message){
        //Handle incoming message

        //Handle action:verifyCustomer,
        //messageId:id, customerId:id,

    }

}


