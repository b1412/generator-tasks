package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.task.SingleTask
import com.github.b1412.generator.TaskConstants

class AllPermissionTask : SingleTask(
        folder = { _, _ -> TaskConstants.generatedPath + "/" + "db/" },
        filename = { _, _ -> "permission-all.sql" },
        templatePath = "kotlin/allPermission.ftl",
        projectExtProcessors = listOf(projectPermissionProcessor)
)
