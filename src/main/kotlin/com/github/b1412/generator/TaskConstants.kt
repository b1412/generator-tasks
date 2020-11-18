package com.github.b1412.generator

import java.util.*

object TaskConstants {
    lateinit var generatedPath: String
    lateinit var apiPath: String
    lateinit var srcPath: String
    fun init(config: String) {
        val inputStream = javaClass.classLoader.getResourceAsStream(config)
        val appProps = Properties()
        appProps.load(inputStream)
        val projectName = appProps.getProperty("projectName")
        generatedPath = "/$projectName-generated"
        apiPath = "/$projectName-api"
        srcPath = "/src/main/kotlin/"
    }
}
