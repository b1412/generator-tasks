package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.SingleTask

class DelAllPermissionTask(map: MutableMap<String, String>) : SingleTask(
    filePath = { _, _ -> TaskConstants.generatedPath + "/" + "db/del-permission-all.sql" },
    templatePath = "kotlin/delAllPermission.ftl",
    projectExtProcessors = listOf(projectPermissionProcessor(map))
)
