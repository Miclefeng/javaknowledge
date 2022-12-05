package com.designpattern.pattern.creational.mooc.prototype;

/**
 * @author miclefengzss
 * 2022/1/18 下午9:08
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setContent("邮件的初始化内容");
        System.out.println("初始化mail: " + mail);
        for (int i = 0; i < 10; i++) {
            Mail mailTemp = (Mail) mail.clone();
            mailTemp.setName("name " + i);
            mailTemp.setAddress("address " + i);
            mailTemp.setContent("content " + i);
            MailUtil.sendMail(mailTemp);
            System.out.println("clone的mailTemp" + mailTemp);
        }

        MailUtil.saveOriginalMail(mail);
    }
}
