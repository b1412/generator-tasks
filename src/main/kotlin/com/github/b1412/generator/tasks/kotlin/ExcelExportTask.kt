package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.MultipleTask

class ExcelExportTask : MultipleTask(
    filePath = { project, entity ->
        TaskConstants.generatedPath + TaskConstants.srcPath + project.packageName.replace(
            ".",
            "/"
        ) + "/" + "excel" + "/" + entity!!.name + "ExcelParsingRule.kt"
    },
    templatePath = "kotlin/excelParsingRule.ftl"
)