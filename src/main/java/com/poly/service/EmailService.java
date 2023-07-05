package com.poly.service;

import com.poly.entity.User;
import jakarta.servlet.ServletContext;

public interface EmailService {
    void sendEmail(ServletContext context, User recipient, String type);
}
