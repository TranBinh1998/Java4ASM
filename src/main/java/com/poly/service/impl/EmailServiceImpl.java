package com.poly.service.impl;

import com.poly.entity.User;
import com.poly.service.EmailService;
import com.poly.util.SendEmailUtil;
import jakarta.servlet.ServletContext;

public class EmailServiceImpl implements EmailService {
    private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online PhimMoi";
    private static final String EMAIL_FORGOT_PASSWORD = "Welcome to Online PhimMoi";

    @Override
    public void sendEmail(ServletContext context, User recipient, String type) {
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        try {
            String content = null;
            String subject = null;
            switch (type) {
                case "wellcome": {
                    subject = EMAIL_WELCOME_SUBJECT;
                    content = "Dear" + recipient.getUserName() + ", hope you have a good time";
                }break;
                case "forgot" : {
                    subject = EMAIL_FORGOT_PASSWORD;
                    content = "Dear "+ recipient.getUserName() + ", your new password here: "+recipient.getPassword();
                }break;
                default:
                    subject = "Online PhimMoi";
                    content = "Tin nhan rac";
            }
            SendEmailUtil.sendEmail(host, port, user, pass,recipient.getEmail(), subject, content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
