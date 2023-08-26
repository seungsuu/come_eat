package kr.or.comeeat;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
//메일 보내는 로직
public class EmailSender {
	@Autowired
	private JavaMailSender sender;
	
	public String authMail(String email) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		//영어 대/소문자/숫자 8자리 랜덤코드 생성
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		
		for(int i=0; i<8; i++) {
			// 0 ~ 9 : r.nextInt(10); 
			// A ~ Z : (char)(r.nextInt(26)+65; 아스키코드
			// a ~ z : (char)(r.nextInt(26)+97;
			
			int flag = r.nextInt(3); // 0:숫자 1:대문자 2:소문자
			if(flag == 0) {
				int num = r.nextInt(10);
				sb.append(num);
			}else if(flag == 1) {
				char ch = (char)(r.nextInt(26)+65);
				sb.append(ch);
			}else if(flag == 2){
				char ch = (char)(r.nextInt(26)+97);
				sb.append(ch);
			}
		}
		
		try {
			//보내는 날짜 : 오늘날짜
			helper.setSentDate(new Date());
			helper.setFrom(new InternetAddress("tnsgus1104@gmail.com","come eat"));
			helper.setTo(email);
			//이메일 제목
			helper.setSubject("[come eat] 비밀번호 인증번호.");
			//내용 true -> html 활성화
			helper.setText("<h4>안녕하세요 come eat 입니다.<br>이메일 인증번호를 알려 드립니다.<br>-인증번호: "+"<span style='color : #ff3700ff;'>"+sb.toString()+"</span></h4>" , true); 
			sender.send(message);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return sb.toString(); // 화면에서 코드가 맞는지 검증하기 위해 return
	}
}
