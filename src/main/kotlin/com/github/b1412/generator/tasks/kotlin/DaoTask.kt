package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.MultipleTask

class DaoTask : MultipleTask(
    replaceFile = false,
    filePath = { project, entity ->
        TaskConstants.apiPath + TaskConstants.srcPath +
                project.packageName.replace(".", "/") +
                "/dao/" + entity!!.name + "Dao.kt"
    },
    templatePath = "kotlin/dao.ftl"
)
