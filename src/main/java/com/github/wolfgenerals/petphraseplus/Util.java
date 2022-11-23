package com.github.wolfgenerals.petphraseplus;

import me.shedaniel.autoconfig.AutoConfig;

import java.util.List;

public class Util {
    static final List<Character> punctuations = List.of('!', '?', '.', '(', ')', '！', '？', '。', '（', '）', '~', '”', '“', '‘', '’', '"', '\'');


    public static String replaceString(String original, String[] settings , String mark ){
        //替换指定字符串,保留Mark后面的
        return original.replace(settings[0],mark + settings[0]).replace(mark + mark + settings[0], settings[0]).replace(mark + settings[0], settings[1]);
    }
    
    public static String endInner (String original, String endInner,int subscript){
        String m;
        if (punctuations.contains(original.charAt(subscript-1))){
            m = endInner(original,endInner,subscript-1);
        }else{
            m = insert(original,endInner,subscript);
        }
        return m;

    }

    public static String insert (String original, String replacement, int index){
        StringBuilder str = new StringBuilder(original);
        str.insert(index,replacement);
        return str.toString();
    }
    public static String petphraseplus (String message) {
        if (message.charAt(0) != '/') {
            ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
            for (int index = 0; index < config.replace.length; index++) {
                message = Util.replaceString(message, config.replace[index], config.mark);
            }
            if (!message.endsWith(config.mark)) {
                message = Util.endInner(message, config.endInner,message.length());
                message = Util.insert(message, config.endOuter,message.length());
            }

        }
        return message;
    }

}