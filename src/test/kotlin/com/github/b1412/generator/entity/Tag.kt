package com.github.b1412.generator.entity

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.generator.metadata.EntityFeature
import com.github.b1412.generator.metadata.ExcelFeature
import com.github.b1412.generator.metadata.PermissionFeature
import com.github.b1412.generator.metadata.PermissionFeatures
import javax.persistence.Entity
import javax.persistence.ManyToOne

@PermissionFeatures(
    PermissionFeature(role = "student", rule = "belongTo", httpMethods = ["GET"]),
    PermissionFeature(role = "teacher", rule = "branches"),
    PermissionFeature(role = "admin", rule = "all")
)

@EntityFeature
@Entity
data class Tag(
    @ExcelFeature(importable = true, index = 1)
    val name: String,

    @ExcelFeature(importable = true, index = 2)
    @ManyToOne
    val tagType: TagType
) : BaseEntity()