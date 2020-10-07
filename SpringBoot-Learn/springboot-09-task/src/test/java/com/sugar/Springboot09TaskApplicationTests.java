package com.sugar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	void contextLoads() {
		// 一个简单的邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject("Sugar，你好");
		mailMessage.setText("谢谢啊");
		mailMessage.setTo("406857586@qq.com");
		mailMessage.setFrom("406857586@qq.com");
		mailSender.send(mailMessage);
	}

	@Test
	void contextLoads2() throws MessagingException {
		// 一个复杂的邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 组装
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setSubject("Sugar，你好");
		helper.setText("<p style='color:red'>啊哈哈哈</p>", true);  // true，开启解析html
		// 附件
		helper.addAttachment("1.jpg", new File("/Users/Sugar/Desktop/1.jpg"));
		helper.setTo("406857586@qq.com");
		helper.setFrom("406857586@qq.com");
		mailSender.send(mimeMessage);
	}

	// 封装发邮件方法
	public void sendMail(boolean enableHtml, String subject, String text) throws MessagingException {
		// 一个复杂的邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 组装
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, enableHtml);
		helper.setSubject(subject);
		helper.setText(text, true);  // true，开启解析html
		// 附件
		helper.addAttachment("1.jpg", new File("/Users/Sugar/Desktop/1.jpg"));
		helper.setTo("406857586@qq.com");
		helper.setFrom("406857586@qq.com");
		mailSender.send(mimeMessage);
	}
}
