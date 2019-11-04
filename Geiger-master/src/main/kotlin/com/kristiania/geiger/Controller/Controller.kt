package com.kristiania.geiger.controller

import com.kristiania.geiger.DeviceDtoConverter
import com.kristiania.geiger.MeasurementDtoConverter
import com.kristiania.geiger.db.Device
import com.kristiania.geiger.db.DeviceRepository
import com.kristiania.geiger.db.Measurement
import com.kristiania.geiger.db.MeasurementRepository
import com.kristiania.geiger.dto.DeviceDto
import com.kristiania.geiger.dto.MeasurementDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Api(value = "/device", description = "Handling of creating and retrieving devices entries")
@RequestMapping(path = ["/device"])
@RestController
class Controller (
    val deviceRepository: DeviceRepository,
    val measurementRepository: MeasurementRepository
){
    var logger = LoggerFactory.getLogger(Controller::class.java)

    @ApiOperation("Get all the devices")
    @GetMapping
    fun getAllDevices(): ResponseEntity<List<DeviceDto>> {
        logger.info("TEST: returning all devices!")
        return ResponseEntity.status(200).body(
                DeviceDtoConverter.transform(deviceRepository.findAll())
        )
    }

    @GetMapping(path = ["/{deviceId}/measurements"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ApiOperation("Get all the measurements on device id")
    fun getAllMeasurement(
            @ApiParam("The id of the device")
            @PathVariable("deviceId")
            deviceId: Long
    ): ResponseEntity<List<MeasurementDto>> {
        logger.info("TEST: all measurements on a device!")
        return ResponseEntity.status(200).body(
                MeasurementDtoConverter.transform(measurementRepository.findAllByDeviceId(deviceId)))
    }

    @ApiOperation("Create a new device")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createDevice(
            @ApiParam("Data for new device")
            @RequestBody
            dto: DeviceDto
    ): ResponseEntity<Long>{

        if (dto.id != null) {
            return ResponseEntity.status(400).build()
        }

        val entity = Device(dto.modelName!!)
        deviceRepository.save(entity)
        logger.info("TEST: creating a device!")
        return ResponseEntity.ok(entity.id!!)
    }


    @ApiOperation("Create a new measurement on a device")
    @PostMapping(path = ["/{deviceId}/measurements"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createMeasurementById(
            @ApiParam("The id of the device")
            @PathVariable("deviceId")
            deviceId: Long?,
            @ApiParam("New data to give to device")
            @RequestBody
            dto: MeasurementDto
    ): ResponseEntity<Long>{

        if(dto.id != null){
            return ResponseEntity.status(400).build()
        }
        val device = deviceRepository.findByIdOrNull(deviceId!!)!!
        val entity = Measurement(device,dto.lat!!,dto.lng!!,dto.sivert!!)

        measurementRepository.save(entity)
        logger.info("TEST: create a measurement on a device!")
        return ResponseEntity.status(204).build()
    }
}