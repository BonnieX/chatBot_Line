package com.bot.botdemo;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@LineMessageHandler
public class BotDemoApplication extends SpringBootServletInitializer {

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BotDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BotDemoApplication.class, args);
    }

    @EventMapping
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent){
        String pesan = messageEvent.getMessage().getText().toLowerCase();
        String[] pesanSplit = pesan.split(" ");
        String replyToken = messageEvent.getReplyToken();
         openJadwal jadwal = new openJadwal();
         String jawaban="";
        if(pesanSplit[0].equals("apakah")){
           jawaban = getRandomJawaban();
            
           
        }

        else if(pesanSplit[0].equals("jadwal")){
            
            jadwal.open();
           jawaban=jadwal.getOpen();
            

        }
        else if(pesanSplit[0].equals("aslab")){
            if(pesanSplit.length==1){
                jawaban="hai";
            }
            else{
                aslab aslab = new aslab();
                aslab.setUrl(pesanSplit[1]);
                aslab.open();
                jawaban = aslab.getOpen();
            }
            
        }
         balasChatDenganRandomJawaban(replyToken, jawaban);
    }

    private String getRandomJawaban(){
        String jawaban = "";
        int random = new Random().nextInt();
        if(random%2==0){
            jawaban = "Ya";
        } else{
            jawaban = "Nggak";
        }
        return jawaban;
    }

    private void balasChatDenganRandomJawaban(String replyToken, String jawaban){
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(jawaban);
        try {
            lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, jawabanDalamBentukTextMessage))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ada error saat ingin membalas chat");
        }
    }

}