package org.jdw.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonolithicMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{SenderMain.class,ReceiverMain.class}, args);
    }

}
