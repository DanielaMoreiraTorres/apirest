package com.practica.apirest

import com.practica.apirest.dao.CiudadRepository
import com.practica.apirest.model.Ciudad
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ApirestApplication:CommandLineRunner{

	fun main(args: Array<String>) {
		SpringApplication.run(ApirestApplication::class.java, *args)
	}
	@Autowired
	val ciudadRepository: CiudadRepository?= null

	override fun run(vararg args: String?) {

	}
}

fun main(args: Array<String>) {
	runApplication<ApirestApplication>(*args)
}
