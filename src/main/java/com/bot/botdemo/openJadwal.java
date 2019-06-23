package com.bot.botdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class openJadwal {
    private BufferedReader br;
    private File file;
    private String url="src/main/java/jadwal.txt";
    private String jadwal="";
    private String data="";
    
    
    public void Jadwal(){
        
        file = new File(url);
        try {
            br= new BufferedReader(new FileReader(file));
            while((jadwal=br.readLine())!=null){
                data +=jadwal+"\n";
            }

        }catch (IOException e){
           
         }

        
    }
    public String getJadwal(){
        return data;
    }
}
