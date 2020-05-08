package com.movieprj.services;

import com.movieprj.beans.Message;
import com.movieprj.beans.User;
import com.movieprj.mapper.MessageMapper;
import com.movieprj.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class MessageServiceImp implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Message findMessageWithUserById(Integer message_id) {
        return messageMapper.findMessageWithUserById(message_id);
    }

    @Override
    public List<Message> findAllMessageWithUser() {
        return messageMapper.findAllMessageWithUser();
    }

    @Override
    public int addMessage(Message message) {
        messageMapper.addMessage(message);
        return 1;
    }

    @Override
    public int deleteMessage(Map<String, List> message_ids) {
        List mids = message_ids.get("message_ids");
        for(int i=0;i<mids.size();i++){
            messageMapper.deleteMessage(Integer.valueOf(message_ids.get(i).toString()));
        }
        return 1;
    }

    @Override
    public int updateMessage(Message message) {
        messageMapper.updateMessage(message);
        return 1;
    }

    @Override
    public int replyMessage(Integer message_id,Integer user_id, String reply_content) {
        Message ms = messageMapper.findMessageWithUserById(message_id);
        User user = userMapper.findUserById(user_id);
        String user_email = user.getUser_email();
        // 发件人的邮箱地址和密码
        String sendEmailAccount = "1729828571@qq.com";
        //如果有授权码，此处填写授权码
        String sendEmailPassword = "gintzysvedapdfja";
        // 发件人邮箱的 SMTP 服务器地址, 可以登录web邮箱查询
        String sendEmailSMTPHost = "smtp.qq.com";
        // 收件人邮箱地址
        String receiveMailAccount = user_email;
        // 参数配置

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");

        props.setProperty("mail.smtp.host", sendEmailSMTPHost);

        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.setProperty("mail.smtp.port", "587");

        props.setProperty("mail.smtp.socketFactory.port", "587");

        // 根据配置创建会话对象, 用于和邮件服务器交互

        Session session = Session.getDefaultInstance(props);

        session.setDebug(true);   // 设置为debug模式, 可以查看详细的发送 log

        // 创建一封邮件

        MimeMessage message = null;
        try {
            message = createMimeMessage(session, sendEmailAccount, receiveMailAccount,reply_content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据 Session 获取邮件传输对象

        Transport transport = null;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        // 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则会报错

        try {
            transport.connect(sendEmailAccount, sendEmailPassword);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 发送邮件

        try {
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 关闭连接

        try {
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        ms.setStatus(1);
        messageMapper.updateMessage(ms);
        return 1;
    }

    private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail ,String content) throws Exception {

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sendMail));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

        // 设置邮件标题

        message.setSubject("来自华莱坞影视的回复");

        // 设置邮件正文

        message.setText(content);

        message.setSentDate(new Date());

        //保存设置

        message.saveChanges();

        return message;

    }


}
