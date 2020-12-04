package com.github.b1412.generator.entity

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.generator.metadata.EntityFeature
import com.github.b1412.generator.metadata.ExcelFeature
import javax.persistence.Entity
import javax.persistence.ManyToOne

@EntityFeature
@Entity
data class Tag(
    @ExcelFeature(importable = true)
    val name: String,

    @ExcelFeature(importable = true)
    @ManyToOne
    val tagType: TagType
) : BaseEntity()