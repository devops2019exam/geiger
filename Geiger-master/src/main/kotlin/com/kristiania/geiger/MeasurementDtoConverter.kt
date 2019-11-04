package com.kristiania.geiger

import com.kristiania.geiger.db.Measurement
import com.kristiania.geiger.dto.MeasurementDto


object MeasurementDtoConverter {


    fun transform(measurement: Measurement) : MeasurementDto {

        return MeasurementDto(
                device = measurement.device,
                lat = measurement.lat,
                lng = measurement.lng,
                sivert = measurement.sivert,
                id = measurement.id.toString()
        )
    }


    fun transform(measurements: Iterable<Measurement>) : List<MeasurementDto>{
        return measurements.map { transform(it) }
    }
}