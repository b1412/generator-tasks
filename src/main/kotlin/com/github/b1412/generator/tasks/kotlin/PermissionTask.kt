package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.ext.Utils
import com.github.b1412.generator.task.MultipleTask

class PermissionTask(map: MutableMap<String, String>) : MultipleTask(
    filePath = { _, entity ->
        """${TaskConstants.generatedPath}/db/${Utils.lowerHyphen(entity!!.name)}/""" + Utils.lowerHyphen(
            entity.name
        ) + "-permission.sql"
    },
    templatePath = "kotlin/permission.ftl",
    entityExtProcessors = listOf(entityPermissionProcessor(map))
)
