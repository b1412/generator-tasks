package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.SingleTask

class AllPermissionTask(map: MutableMap<String, String>) : SingleTask(
        filePath = { _, _ -> TaskConstants.generatedPath + "/" + "db/" +"add-curd-permission.sql"},
        templatePath = "kotlin/allPermission.ftl",
        projectExtProcessors = listOf(projectPermissionProcessor(map))
)
