package com.flowerseeker.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.flowerseeker.controllers.forms.SendEmailForm;
 
@Service
public class MailService {
   
	@Autowired
	private MailSender mailSender;

 
    public void sendEmail(SendEmailForm sef){
 
        SimpleMailMessage message = new SimpleMailMessage();
 
        message.setTo(sef.getTo());
        message.setSubject(sef.getSubject());
        message.setText(sef.getMessage());
        message.setSentDate(new Date());
        mailSender.send(message);
 
    }
 
 

}
