package com.example.demoEmail.commandLineRunner;

import com.example.demoEmail.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class TestEmailRunner implements CommandLineRunner {
    @Autowired
    EmailUtil emailUtil;

    @Override
    public void run(String... args) throws Exception {
        FileSystemResource file = new FileSystemResource("C:\\Users\\srinu\\OneDrive\\Desktop\\emailtetst.png");
        boolean isEmailSend = emailUtil.send(
                "srinu7487chatgpt@gmail.com",
                null,
                null,
                "sample",
                "hello ,how are u", file);
        //instead of text as string you can send html format also
        //"<html><body>hello ,how are u</body></html>"
        //but
        if (isEmailSend){
            System.out.println("email sent");
        }else{
            System.out.println("email not sent");
        }
    }
}
