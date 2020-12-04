package com.github.b1412.generator.client

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.task.SingleTask
import com.github.b1412.generator.task.Task
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.div


fun createMap(map: Map<String, Any>): (Task, CodeProject) -> Map<String, Any> {
    return { _, _ ->
        map
    }
}


@ExperimentalPathApi
class HttpJsonGenerator(map: Map<String, Any>) : SingleTask(
    filePath = { _, _ -> (Paths.get(TaskConstants.generatedPath) / "http/http-client.env.json").toFile().absolutePath },
    templatePath = "http/envJson.ftl",
    projectExtProcessors = listOf(createMap(map))
)
