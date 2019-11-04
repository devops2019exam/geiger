package com.kristiania.geiger


import com.kristiania.geiger.db.DatabaseInitializer
import com.kristiania.geiger.db.DeviceRepository
import com.kristiania.geiger.dto.DeviceDto
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(GeigerApplication::class)],
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeigerApplicationTests {

	@LocalServerPort
	protected var port = 0

	@Autowired protected lateinit var deviceRepository: DeviceRepository
	@Autowired protected lateinit var measurementRepository: DeviceRepository
	@Autowired protected lateinit var databaseInitializer: DatabaseInitializer


	@BeforeEach
	@AfterEach
	fun clean() {

		RestAssured.baseURI = "http://localhost"
		RestAssured.port = port
		RestAssured.basePath = "/device"
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
		databaseInitializer.initializeDeviceAndMeasurements()
	}

	@Test
	fun testGetAllDevice() {
		RestAssured.given().accept(ContentType.JSON)
				.get()
				.then()
				.statusCode(200)
				.body("size()", CoreMatchers.equalTo(4))
	}

	@Test
	fun getAllMeasurement(){
		RestAssured.given().accept(ContentType.JSON)
				.get()
				.then()
				.statusCode(200)
				.body("size()", CoreMatchers.equalTo(4))
	}

	@Test
	fun testCreateDevice() {

		val n = RestAssured.given().accept(ContentType.JSON)
				.get()
				.then()
				.statusCode(200)
				.extract().body().path<Int>("size()")

		val dev1 = DeviceDto("modelnameTest")

		RestAssured.given().accept(ContentType.JSON)
				.header("Accept", ContentType.JSON)
				.request().header("Content-Type", "application/json")
				.body(dev1)
				.post("")
				.then().statusCode(200)

		RestAssured.given().accept(ContentType.JSON)
				.get()
				.then()
				.statusCode(200)
				.body("size()", CoreMatchers.equalTo(n + 1))
	}
}
