package com.github.b1412.generator

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.generator.client.HttpTaskGenerator
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.entity.scanForCodeEntities
import com.github.b1412.generator.task.Task
import com.github.b1412.generator.tasks.kotlin.*
import com.github.b1412.generator.template.FreeMarkerHelper

import java.util.*


fun ktGenerator(config: String, additionalTasks: List<Task> = listOf()) {
    val appProps = Properties()
    appProps.load(Thread.currentThread().contextClassLoader.getResourceAsStream(config))
    val projectName = appProps.getProperty("projectName")
    val packageName = appProps.getProperty("packageName")
    val entityPackageName = appProps.getProperty("entityLocationPattern")
    val projectId = appProps.getProperty("projectId").toInt()

    val properties: MutableMap<String, String> = mutableMapOf()
    for (name in appProps.stringPropertyNames()) {
        properties[name] = appProps.getProperty(name)
    }
    val entities = scanForCodeEntities(entityPackageName, BaseEntity::class.java, projectId)
    TaskConstants.init(config)
    val tasks = listOf(
            BaseControllerTask(),
            ControllerTask(),
            DaoTask(),
            ServiceTask(),
            AllPermissionTask(properties),
            DelAllPermissionTask(properties),
           // PermissionTask(properties),
          //  RolePermissionRuleTask(properties),
            ExcelExportTask(),
            HttpTaskGenerator()
    ) + additionalTasks
    tasks.forEach {
        it.targetPath = System.getProperty("user.dir")
    }
    CodeProject(
            name = projectName,
            entities = entities,
            packageName = packageName,
            tasks = tasks,
            templateEngine = FreeMarkerHelper()
    ).generate()
}

fun main() {
    ktGenerator("")
}
