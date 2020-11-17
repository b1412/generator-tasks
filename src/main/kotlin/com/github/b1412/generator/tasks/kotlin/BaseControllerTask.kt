package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.task.MultipleTask
import com.github.b1412.generator.TaskConstants

class BaseControllerTask : MultipleTask(
        folder = { project, _ -> TaskConstants.generatedPath + TaskConstants.srcPath + project.packageName.replace(".", "/") + "/" + "controller/base" },
        filename = { _, entity -> "Base" + entity!!.name + "Controller.kt" },
        templatePath = "kotlin/baseController.ftl"
)

