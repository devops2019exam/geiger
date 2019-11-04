package com.kristiania.geiger.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Device(

        @get:NotBlank
        @get:Size(max = 256)
        var modelName: String,

        @get:GeneratedValue
        @get:Id
        var id: Long? = null
)