package cn.itcast.travel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailUtils {
    private static final String USER = "pengyuucd@gmail.com"; // 发件人称号，同邮箱地址
    private static final String from ="pengyuucd@gmail.com";
    private static final String psd= "iuablqekyimeznxd";
    /**
     *
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     * @throws MessagingException 
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String text, String title) throws MessagingException{
//    	String msg = "This is a test message";
    	Properties props = new Properties(); 
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", "587");  
        props.put("mail.debug", "true");  

        Session session = Session.getDefaultInstance(props,  
        new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {  
           return new PasswordAuthentication(from,psd);  
       }  
       });  

       //session.setDebug(true);  
       Transport transport = session.getTransport();  
       InternetAddress addressFrom = new InternetAddress(from);  

       MimeMessage message = new MimeMessage(session);  
       message.setSender(addressFrom);  
       message.setSubject(title);  
	message.setContent(text,"text/html;charset=utf-8");  
       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

       transport.connect("smtp.gmail.com",from,psd);  
       Transport.send(message);  
       transport.close();
       return true;
    }
//        try {
//            final Properties props = new Properties();
//            props.setProperty("mail.transport.protocol", "smtp");     
//            props.setProperty("mail.host", "smtp.gmail.com");  
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//
//            // 发件人的账号
//            props.put("mail.user", USER);
//            //发件人的密码
//            props.put("mail.password", PASSWORD);
//
//            // 构建授权信息，用于进行SMTP进行身份验证
//            Authenticator authenticator = new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    // 用户名、密码
//                    String userName = props.getProperty("mail.user");
//                    String password = props.getProperty("mail.password");
//                    return new PasswordAuthentication(userName, password);
//                }
//            };
//            // 使用环境属性和授权信息，创建邮件会话
//            Session mailSession = Session.getInstance(props, authenticator);
//            // 创建邮件消息
//            MimeMessage message = new MimeMessage(mailSession);
//            // 设置发件人
//            String username = props.getProperty("mail.user");
//            InternetAddress form = new InternetAddress(username);
//            message.setFrom(form);
//
//            // 设置收件人
//            InternetAddress toAddress = new InternetAddress(to);
//            message.setRecipient(Message.RecipientType.TO, toAddress);
//
//            // 设置邮件标题
//            message.setSubject(title);
//
//            // 设置邮件的内容体
//            message.setContent(text, "text/html;charset=UTF-8");
//            // 发送邮件
//            Transport.send(message);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }

}
