package com.github.b1412.generator.client

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.task.SingleTask
import com.github.b1412.generator.task.Task


fun createEnv(map: Map<String, Any>): (Task, CodeProject) -> Map<String, Any> {
    return { _, _ ->
        map
    }
}

class HttpJsonGenerator(map: Map<String, Any>) : SingleTask(
        folder = { _, _ -> """${TaskConstants.generatedPath}/http/""" },
        filename = { _, _ -> "http-client.env.json" },
        templatePath = "generator/http/envJson.ftl",
        projectExtProcessors = listOf(createEnv(map))
)
