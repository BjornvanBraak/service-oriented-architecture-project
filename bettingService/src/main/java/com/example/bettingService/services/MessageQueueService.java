package com.example.bettingService.services;

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
    //includes an action tag so the receiver knows what to do with it.

    //message should include action:verifyCustomer, messageId:id, customerId:id,
    public void sendVerifyCustomer(String message)
    {
        jmsTemplate.convertAndSend("IdentityServiceRequestChannel", message);
    }

    //message should include action:verifyGame, messageId:id, gameId:id
    public void sendVerifyGame(String message)
    {
        jmsTemplate.convertAndSend("GameServiceRequestChannel", message);
    }

    //Deducts from balance
    //message should include action:verifyPayment, messageId:id, customerId:id, betAmount:amount
    public void sendVerifyPayment(String message)
    {
        jmsTemplate.convertAndSend("BalanceServiceRequestChannel", message);
    }

    //Increases balance
    //message should include action:verifyPayout, messageId:id, customerId:id, earnedAmount:amount
    public void sendVerifyPayout(Long customer_id, Float payoutAmount)
    {
        jmsTemplate.convertAndSend("BalanceServiceRequestChannel", customer_id);
    }

    //Consuming messages from the message queue. Based on what action tag they have it should be handled.
    @JmsListener(destination = "BettingServiceReponseChannel")
    public void listener(String message){
        //Handle incoming message

        //Handle action:verifyCustomerResponse
        //messageId:id, customerId:id, crukApproved:boolean

        //Handle action:verifyGameResponse
        //messageId:id, gameId:id, gameApproved:boolean, teamA:team, teamB:team, startTime:dateTime

        //Handle action:verifyPaymentResponse
        //messageId:id, customerId:id, paymentApproved:boolean

        //Handle action:verifyPayoutResponse
        //messageId:id, customerId:id, payoutApproved:boolean

    }

}


