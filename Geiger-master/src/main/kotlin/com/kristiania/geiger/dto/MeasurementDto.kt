package com.kristiania.geiger.dto

import com.kristiania.geiger.db.Device
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull


class MeasurementDto(

        @ApiModelProperty("The device of them measurement")
        @NotNull
        var device: Device? = null,

        @ApiModelProperty("The latitude of the measurement")
        var lat: Float? = null,

        @ApiModelProperty("The longitude of the measurement")
        var lng: Float? = null,

        @ApiModelProperty("The sivert of the measurement")
        @NotNull
        var sivert: Long? = null,

        @ApiModelProperty("The unique id of the device")
        var id: String? = null
)