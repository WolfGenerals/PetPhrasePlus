package com.github.wolfgenerals.petphraseplus;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@SuppressWarnings("all")
@Config(name = "petphraseplus")
public class ModConfig implements ConfigData {
    @Comment("Enable or disable the petphrase-plus mod.")
    public  boolean enabled = true;
    @Comment("Pet Phrase: mark")
    public  String mark = "&";
    @Comment("Pet Phrase: replace")
    public  String[][] replace = {{"",""},{"",""}};
    @Comment("Pet Phrase: end_inner")
    public  String endInner = "nya~";
    @Comment("Pet Phrase: end_outer")
    public  String endOuter = "";
}   
