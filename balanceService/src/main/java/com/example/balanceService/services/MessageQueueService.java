package com.example.balanceService.services;

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

    //Here there are two send functions for payment(withdrawl from balance) and payout(increase balance)
    //Could choose to have just one and set negative for witdrawls for example.

    //message should include action:verifyCustomerReponse, responseToMessageId:id, customerId:id, paymentApproved:boolean
    public void respondVerifyPayment(String message)
    {
        jmsTemplate.convertAndSend("BettingServiceRespondChannel", message);
    }

    //message should include action:verifyCustomerReponse, responseToMessageId:id, customerId:id, payoutApproved:boolean
    public void respondVerifyPayout(String message)
    {
        jmsTemplate.convertAndSend("BettingServiceRespondChannel", message);
    }

    //Consuming messages from the message queue. Based on what action tag they have it should be handled.
    @JmsListener(destination = "BalanceServiceRequestChannel")
    public void listener(String message){
        //Handle incoming message

        //Handle action:verifyPayment
        //messageId:id, customerId:id, betAmount:amount

        //Handle action:verifyPayout
        //messageId:id, customerId:id, earnedAmount:amount

    }

}


