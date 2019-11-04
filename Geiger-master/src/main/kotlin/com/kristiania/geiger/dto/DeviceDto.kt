package com.kristiania.geiger.dto

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull


class DeviceDto(

        @ApiModelProperty("The modelName of the device")
        @get:NotNull
        var modelName: String? =null,

        @ApiModelProperty("The unique id of the device")
        var id: String? = null
)