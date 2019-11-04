package com.kristiania.geiger.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface DeviceRepository : CrudRepository<Device, Long>