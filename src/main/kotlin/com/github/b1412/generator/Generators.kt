package com.github.b1412.generator

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.entity.scanForCodeEntities
import com.github.b1412.generator.tasks.kotlin.*
import com.github.b1412.generator.template.FreeMarkerHelper

import java.util.*


fun ktGenerator(config: String) {
    val appProps = Properties()
    appProps.load(Thread.currentThread().contextClassLoader.getResourceAsStream(config))
    val projectName = appProps.getProperty("projectName")
    val packageName = appProps.getProperty("packageName")
    val entityPackageName = appProps.getProperty("entityLocationPattern")
    val entities = scanForCodeEntities(entityPackageName, BaseEntity::class.java)
    TaskConstants.init(config)
    val tasks = listOf(
            BaseControllerTask(),
            ControllerTask(),
            DaoTask(),
            ServiceTask(),
            AllPermissionTask(),
            PermissionTask(),
            RolePermissionRuleTask(),
            ExcelExportTask()
    )
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
