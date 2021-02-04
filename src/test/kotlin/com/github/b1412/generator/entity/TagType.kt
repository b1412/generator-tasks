package com.github.b1412.generator.entity

import com.github.b1412.api.entity.BaseEntity
import com.github.b1412.generator.metadata.EntityFeature
import com.github.b1412.generator.metadata.ExcelFeature
import javax.persistence.Entity
import javax.validation.constraints.NotNull

@EntityFeature
@Entity
data class TagType(
    @ExcelFeature(importable = true, index = 1)
    @NotNull
    val name: String?
) : BaseEntity()