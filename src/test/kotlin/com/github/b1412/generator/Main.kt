package com.github.b1412.generator

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.extenstions.println
import com.github.b1412.generator.client.HttpTaskGenerator
import com.github.b1412.generator.entity.CodeProject
import com.github.b1412.generator.entity.scanForCodeEntities
import com.github.b1412.generator.template.FreeMarkerHelper
import org.junit.jupiter.api.Test
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.div

class Main {
    @ExperimentalPathApi
    @Test
    fun test() {
        TaskConstants.generatedPath = System.getProperty("user.dir") + "/generated"
        println(TaskConstants.generatedPath)
        val results = CodeProject(
            name = "generator-test",
            entities = scanForCodeEntities(
                path = "classpath*:com/github/b1412/generator/entity/*.class",
                clazz = BaseEntity::class.java,
                projectId = 1
            ),
            packageName = "com.github.b1412.generator",
            tasks = mutableListOf(HttpTaskGenerator()),
            templateEngine = FreeMarkerHelper()
        ).generate()!!
        results

    }
}
