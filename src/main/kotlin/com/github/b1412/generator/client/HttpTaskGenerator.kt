package com.github.b1412.generator.client

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.ext.Utils
import com.github.b1412.generator.task.MultipleTask
import com.github.b1412.generator.tasks.kotlin.entityNames

class HttpTaskGenerator : MultipleTask(
    filePath = { _, entity ->
        """${TaskConstants.generatedPath}/http/${Utils.lowerHyphen(entity!!.name)}.http"""
    },
    templatePath = "http/http.ftl",
    entityExtProcessors = listOf(entityNames)
)
