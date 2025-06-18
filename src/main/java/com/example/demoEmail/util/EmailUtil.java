package com.example.demoEmail.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender sender;

    public boolean send(String to, String[] cc, String[] bcc, String subject, String text, Resource file)  {
        boolean flag=false;
        try{
            //1 create Empty message
            MimeMessage message=sender.createMimeMessage();

            //2 use Helper class object
            //MimeMessageHelper helper=new MimeMessageHelper(message,false);
            //if attachment is there then true else false
            //set details
            MimeMessageHelper helper=new MimeMessageHelper(message, null != file);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);//if it is plain test
            //helper.setText(text,true);if content is in html format
            if(null!=cc && cc.length>0){
                helper.setCc(cc);
            }
            if(null!=bcc && bcc.length>0){
                helper.setBcc(bcc);
            }
            if(null!=file){
                helper.addAttachment(file.getFilename(),file);
            }
            //3 send message
            sender.send(message);
            flag=true;
        } catch (Exception e) {
            flag=false;
            e.printStackTrace();
        }
        return flag;
    }

}
