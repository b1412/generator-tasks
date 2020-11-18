package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.MultipleTask

class DaoTask : MultipleTask(
        replaceFile = false,
        folder = { project, _ -> TaskConstants.apiPath + TaskConstants.srcPath + project.packageName.replace(".", "/") + "/" + "dao" },
        filename = { _, entity -> entity!!.name + "Dao.kt" },
        templatePath = "kotlin/dao.ftl"
)
