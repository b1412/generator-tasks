package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.SingleTask

class DelAllPermissionTask(map: MutableMap<String, String>) : SingleTask(
        folder = { _, _ -> TaskConstants.generatedPath + "/" + "db/" },
        filename = { _, _ -> "del-permission-all.sql" },
        templatePath = "kotlin/delAllPermission.ftl",
        projectExtProcessors = listOf(projectPermissionProcessor(map))
)
