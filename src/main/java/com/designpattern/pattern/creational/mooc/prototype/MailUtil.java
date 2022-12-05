package com.designpattern.pattern.creational.mooc.prototype;

import java.text.MessageFormat;

/**
 * @author miclefengzss
 * 2022/1/12 下午4:38
 */
public class MailUtil {

    public static void sendMail(Mail mail) {
        String outputContent = "向{0}同学，邮件地址：{1}，邮件内容：{2}，发送邮件。";
        System.out.println(MessageFormat.format(outputContent, mail.getName(), mail.getAddress(), mail.getContent()));
    }

    public static void saveOriginalMail(Mail mail) {
        System.out.println("存储originalMail记录，originalMail: " + mail.getContent());
    }
}
