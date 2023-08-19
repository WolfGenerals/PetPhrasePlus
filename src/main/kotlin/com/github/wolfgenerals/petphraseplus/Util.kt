package com.github.wolfgenerals.petphraseplus

import com.github.wolfgenerals.petphraseplus.config.config
import net.minecraft.text.MutableText
import net.minecraft.text.Text

private fun getReplaced(replace: String): String {
    val index = replace.indexOf("/")
    return if (index == -1) {
        ""
    } else if (index == 0) {
        ""
    } else {
        replace.substring(0, index)
    }
}

private fun getReplacement(replace: String): String {
    val index = replace.indexOf("/")
    return if (index == -1) {
        ""
    } else if (index == replace.length - 1) {
        ""
    } else {
        replace.substring(index + 1)
    }
}

private fun replaceString(original: String, replace: String, mark: String): String {
    //替换指定字符串,保留Mark后面的
    return original.replace(getReplaced(replace), mark + getReplaced(replace))
        .replace(mark + mark + getReplaced(replace), getReplaced(replace))
        .replace(mark + getReplaced(replace), getReplacement(replace))
}

private fun endInner(original: String, endInner: String, subscript: Int): String {
    val m: String
    if (subscript == 0) {
        return original
    } //记事本代码大法
    m = if (punctuations.contains(original[subscript - 1])) {
        endInner(original, endInner, subscript - 1)
    } else {
        insert(original, endInner, subscript)
    }
    return m
}

private fun insert(original: String, replacement: String, index: Int): String {
    val str = StringBuilder(original)
    str.insert(index, replacement)
    return str.toString()
}


fun editMessage(message: String): String {
    var message = message
    if (message[0] != '/') {
        for (index in config.replace.indices) {
            message = replaceString(message, config.replace[index], config.mark)
        }
        if (!message.endsWith(config.mark)) {
            message = endInner(message, config.endInner, message.length)
            message = insert(message, config.endOuter, message.length)
        } else {
            return message.substring(0, message.length - 1)
        }
    }
    return message
}

private val punctuations = listOf(
    '!', '?', '.', ';', ':', '(', ')', '~', '"', '\'', '[', ']', '{', '}',
    '！', '？', '。', '；', '：', '（', '）', '～', '“', '”', '‘', '’', '【', '】', '｛', '｝',
    '¡', '¿', '「', '」', '『', '』'
)

val String.i18n: MutableText
    get() = Text.translatable(this)