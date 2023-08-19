package com.github.wolfgenerals.petphraseplus

import net.minecraft.text.MutableText
import net.minecraft.text.Text

val String.i18n: MutableText
    get() = Text.translatable(this)

val String.replace
    get() = if (!isReplaceable) null
    else Replace(substringBefore('/') , substringAfter('/'))
val String.isReplaceable: Boolean
    get() = !startsWith('/') and !endsWith('/') and (count { it == '/' } == 1)


data class Replace(val old:String,val new:String){
    override fun toString(): String = "$old/$new"
}