package com.edge.weather.unithon;

/**
 * Created by c2619 on 2018-01-28.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sj on 2018-01-27.
 */

public class Voiceclassify {
    List<String> items = new ArrayList<>();
    public Voiceclassify(String voice){
        //voice = voice.replaceAll(" ","");
        String [] voices=voice.split("/");

        for(int i =0;i<voices.length;i++){
            voices[i] = voices[i].replaceAll(" ","");
            items.add(voices[i]);
        }

    }
    public String classify(){
        String result="";
        if(items.contains("해빗업")|items.contains("해비덥")|items.contains("해빛업")|items.contains("해비덮")|items.contains("해비덕")|items.contains("헤비덕"))
        {
            result ="happy";
        }

        else {
            result ="unhappy";
        }
        return result;
    }
}