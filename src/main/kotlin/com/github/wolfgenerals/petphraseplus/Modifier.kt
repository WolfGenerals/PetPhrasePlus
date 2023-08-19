package com.github.wolfgenerals.petphraseplus

import com.github.wolfgenerals.petphraseplus.config.config

private fun String.endInner(endInner: String): String {
    val index = indexOfLast { it !in punctuations }
    return "${substring(0,index+1)}$endInner${substring(index+1)}"
}

fun String.replace(list:List<Replace>): String {
    var s = this
    list.forEach {
        s = this.replace(it.old,it.new)
    }
    return s
}


fun needModify(s: String): Boolean = config.enabled and !s.startsWith('/') and s.isNotBlank()

fun modifyMessage(message: String): String =
    "${config.start}${message.replace(config.replace).endInner(config.endInner)}${config.endOuter}"

private val punctuations = listOf(
    '!', '?', '.', ';', ':', '(', ')', '~', '"', '\'', '[', ']', '{', '}',
    '！', '？', '。', '；', '：', '（', '）', '～', '“', '”', '‘', '’', '【', '】', '｛', '｝',
    '¡', '¿', '「', '」', '『', '』'
)