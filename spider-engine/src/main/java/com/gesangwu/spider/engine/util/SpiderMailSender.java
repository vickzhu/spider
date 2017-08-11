package com.gesangwu.spider.engine.util;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SpiderMailSender {
	
	@Resource
    private JavaMailSender mailSender;
	
    public void send(String text){
    	SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("zhuxiaobing@rojintee.com");
		mail.setTo("zhuxiaobing@rojintee.com");
		mail.setSubject("恭喜发财！！！");
		mail.setText(text);
    	mailSender.send(mail);
    }
    
}
