package com.kristiania.geiger

import com.kristiania.geiger.db.Device
import com.kristiania.geiger.dto.DeviceDto


object DeviceDtoConverter {


    fun transform(device: Device) : DeviceDto {

        return DeviceDto(
                modelName = device.modelName,
                id = device.id.toString()
        )
    }


    fun transform(devices: Iterable<Device>) : List<DeviceDto>{
        return devices.map { transform(it) }
    }
}