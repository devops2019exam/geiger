package com.kristiania.geiger.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import javax.annotation.PostConstruct


@Service
class DatabaseInitializer(
        @Autowired val deviceRepository: DeviceRepository,
        @Autowired val measurementRepository: MeasurementRepository
) {


    @PostConstruct
    fun initializeDeviceAndMeasurements(){
        measurementRepository.deleteAll()
        deviceRepository.deleteAll()

        val dev1 = Device("ModelNr1")
        val dev2 = Device("ModelNr2")
        val dev3 = Device("ModelNr3")
        val dev4 = Device("ModelNr4")
        deviceRepository.run {
            save(dev1)
            save(dev2)
            save(dev3)
            save(dev4)
        }

        measurementRepository.run {
            save(Measurement(dev1, 140.20f,60.120f,340))
            save(Measurement(dev2,120.20f,60.150f,30))
            save(Measurement(dev3,0.20f,-0.60f,10000))
            save(Measurement(dev4,300f,200.10f,1))
        }
    }

}