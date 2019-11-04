package com.kristiania.geiger.db

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Measurement(

        @get:ManyToOne
        var device: Device,

        @get:NotNull
        var lat: Float,

        @get:NotNull
        var lng: Float,

        @get:NotNull
        var sivert: Long,

        @get:GeneratedValue
        @get:Id
        var id: Long? = null
)