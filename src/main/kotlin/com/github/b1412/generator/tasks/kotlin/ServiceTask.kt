package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.MultipleTask


class ServiceTask : MultipleTask(
    replaceFile = false,
    filePath = { project, entity ->
        TaskConstants.apiPath + TaskConstants.srcPath + project.packageName.replace(
            ".",
            "/"
        ) + "/" + "service" + "/" + entity!!.name + "Service.kt"
    },
    templatePath = "kotlin/service.ftl"
)
