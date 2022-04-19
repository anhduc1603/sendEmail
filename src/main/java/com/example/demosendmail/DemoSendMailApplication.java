package com.example.demosendmail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@SpringBootApplication
public class DemoSendMailApplication implements CommandLineRunner {


	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(DemoSendMailApplication.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println("Đang gửi  Email...");

	try {
		//sendEmail();
		sendEmailWithAttachment();

	}catch (Exception e){
		e.printStackTrace();
	}


		System.out.println("Done");

	}

	void sendEmail() {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("saotrucanhduc@gmail.com");

		msg.setSubject("Thử nghiệm gửi gmail");
		msg.setText("Xin chào, đây là email thử nghiệm được gửi từ Spring Email");

		javaMailSender.send(msg);

	}

	void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("saotrucanhduc@gmail.com");

		helper.setSubject("Testing from Spring Boot");

		// default = text/plain
		//helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("<h1>Check attachment for image!</h1>", true);
		helper.addAttachment("meo.jpg", new ClassPathResource("meo.jpg"));
		javaMailSender.send(msg);

	}

}
