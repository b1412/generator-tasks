package com.github.b1412.generator

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.entity.scanForCodeEntities
import com.github.b1412.generator.tasks.kotlin.PermissionTask
import com.github.b1412.generator.tasks.kotlin.RolePermissionRuleTask
import com.github.b1412.generator.template.FreeMarkerHelper
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.io.path.ExperimentalPathApi

class Main {
    @ExperimentalPathApi
    @Test
    fun test() {
        val config = "generator.properties"
        val appProps = Properties()
        appProps.load(Thread.currentThread().contextClassLoader.getResourceAsStream(config))
        val properties: MutableMap<String, String> = mutableMapOf()
        for (name in appProps.stringPropertyNames()) {
            properties[name] = appProps.getProperty(name)
        }
        TaskConstants.generatedPath = System.getProperty("user.dir") + "/generated"
        println(TaskConstants.generatedPath)
        CodeProject(
            name = "generator-test",
            entities = scanForCodeEntities(
                path = "classpath*:com/github/b1412/generator/entity/*.class",
                clazz = BaseEntity::class.java,
                projectId = 1
            ),
            packageName = "com.github.b1412.generator",
            tasks = mutableListOf(
                PermissionTask(properties),
                RolePermissionRuleTask(properties)
            ),
            templateEngine = FreeMarkerHelper()
        ).generate()
    }
}
