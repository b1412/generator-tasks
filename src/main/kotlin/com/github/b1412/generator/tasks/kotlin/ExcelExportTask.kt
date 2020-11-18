package com.github.b1412.generator.tasks.kotlin

import com.github.b1412.generator.TaskConstants
import com.github.b1412.generator.task.MultipleTask

class ExcelExportTask : MultipleTask(
        folder = { project, _ -> TaskConstants.generatedPath + TaskConstants.srcPath + project.packageName.replace(".", "/") + "/" + "excel" },
        filename = { _, entity -> entity!!.name + "ExcelParsingRule.kt" },
        templatePath = "kotlin/excelParsingRule.ftl"
)