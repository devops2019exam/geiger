package com.kristiania.geiger.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface MeasurementRepository : CrudRepository<Measurement, Long> {

    fun findAllByDeviceId(id: Long): List<Measurement>
}