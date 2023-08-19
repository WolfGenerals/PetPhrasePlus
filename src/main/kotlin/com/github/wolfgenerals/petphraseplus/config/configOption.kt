package com.github.wolfgenerals.petphraseplus.config

import com.google.gson.Gson
import net.fabricmc.loader.api.FabricLoader
import java.io.File

var config: Config = Config()
val configFile = FabricLoader.getInstance().configDir.resolve("petphraseplus.json").toFile()

data class Config(
    val enabled: Boolean = true,
    val mark: String = "",
    val endInner: String = "",
    val endOuter: String = "",
    val replace: List<String> = mutableListOf(),
)

fun Config.write(file: File) {
    file.writeText(this.encode())
}

fun File.readConfig() = this.takeIf { it.isFile }?.readText()?.decodeConfig()

fun Config.encode(): String = Gson().toJson(this)
fun String.decodeConfig():Config = Gson().fromJson(this,Config::class.java)

