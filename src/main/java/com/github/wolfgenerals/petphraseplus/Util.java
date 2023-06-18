package com.github.wolfgenerals.petphraseplus;

import com.github.wolfgenerals.petphraseplus.config.ConfigOption;

import java.util.List;

public class Util {
    static final List<Character> punctuations = List.of('!', '?', '.', '(', ')', '！', '？', '。', '（', '）', '~', '”', '“', '‘', '’', '"', '\'');

    private static String getReplaced(String replace) {
        int index = replace.indexOf("/");
        if (index == -1) {
            return "";
        } else if (index == 0) {
            return "";
        } else {
            return replace.substring(0, index);
        }


    }

    private static String getReplacement(String replace) {
        int index = replace.indexOf("/");
        if (index == -1) {
            return "";
        } else if (index == replace.length() - 1) {
            return "";
        } else {
            return replace.substring(index + 1);
        }
    }

    private static String replaceString(String original, String replace, String mark) {
        //替换指定字符串,保留Mark后面的
        return original.replace(getReplaced(replace), mark + getReplaced(replace)).replace(mark + mark + getReplaced(replace), getReplaced(replace)).replace(mark + getReplaced(replace), getReplacement(replace));
    }

    private static String endInner(String original, String endInner, int subscript) {
        String m;
        if(subscript==0) {return original;}//记事本代码大法
        if (punctuations.contains(original.charAt(subscript - 1))) {
            m = endInner(original, endInner, subscript - 1);
        } else {
            m = insert(original, endInner, subscript);
        }
        return m;

    }

    private static String insert(String original, String replacement, int index) {
        StringBuilder str = new StringBuilder(original);
        str.insert(index, replacement);
        return str.toString();
    }

    public static String petphraseplus(String message) {
        if (ConfigOption.enabled) {
            if (message.charAt(0) != '/') {
                for (int index = 0; index < ConfigOption.replace.size(); index++) {
                    message = Util.replaceString(message, ConfigOption.replace.get(index), ConfigOption.mark);
                }
                if (!message.endsWith(ConfigOption.mark)) {
                    message = Util.endInner(message, ConfigOption.endInner, message.length());
                    message = Util.insert(message, ConfigOption.endOuter, message.length());
                } else {
                    return message.substring(0, message.length() - 1);
                }

            }
        }
        return message;
    }

}
