package com.bot.botdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class openJadwal {
    protected BufferedReader br;
    protected File file;
    protected String url="src/main/java/jadwal/jadwal.txt";
    protected String jadwal="";
    protected String data="";
    
    
    public void open(){
        
        file = new File(url);
        try {
            br= new BufferedReader(new FileReader(file));
            while((jadwal=br.readLine())!=null){
                data +=jadwal+"\n";
            }

        }catch (IOException e){
           
         }

        
    }
    public String getOpen(){
        return data;
    }
}
