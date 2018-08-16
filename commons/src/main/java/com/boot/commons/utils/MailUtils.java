package com.boot.commons.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class MailUtils {

    public static void main(String args[]) throws  Exception{
        Email email = new SimpleEmail();
        email.setHostName("smtp.exmail.qq.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("username", "password"));
        email.setSSLOnConnect(true);
        email.setFrom("wangpeng@jbinfo.cn");
        email.setSubject("Peng123456");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("18300247760@163.com");
        email.send();
    }
}
