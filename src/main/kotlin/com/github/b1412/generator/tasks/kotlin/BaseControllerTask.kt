package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.MultipleTask

class BaseControllerTask : MultipleTask(
    filePath = { project, entity ->
        TaskConstants.generatedPath + TaskConstants.srcPath + project.packageName.replace(
            ".",
            "/"
        ) + "/" + "controller/base" + "Base" + entity!!.name + "Controller.kt"
    },
    templatePath = "kotlin/baseController.ftl"
)

