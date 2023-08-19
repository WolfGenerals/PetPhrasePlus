package com.github.wolfgenerals.petphraseplus.config

import net.fabricmc.loader.api.FabricLoader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.util.*

object ConfigSL {
    val PATH = FabricLoader.getInstance().configDir.resolve("petphraseplus.properties")
    @JvmStatic
    @Throws(IOException::class)
    fun newFile() {
        if (!Files.exists(PATH)) {
            File(PATH.toString()).createNewFile()
        }
    }

    @JvmStatic
    fun loadConfig() {
        val properties = Properties()
        try {
            properties.load(FileInputStream(PATH.toString()))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        val enabled = properties.getProperty("enable", "true").equals("true", ignoreCase = true)
        val mark = properties.getProperty("mark", "&")
        val endInner = properties.getProperty("endInner", "")
        val endOuter = properties.getProperty("endOuter", "")
        val replace = mutableListOf<String>()

        var i = 0
        while (properties.getProperty("replace$i") != null) {
            replace.add(properties.getProperty("replace$i"))
            i++
        }

        config = Config(enabled, mark, endInner, endOuter, replace)

    }
}