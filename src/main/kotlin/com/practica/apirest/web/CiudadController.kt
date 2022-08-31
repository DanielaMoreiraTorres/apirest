package com.practica.apirest.web

import com.practica.apirest.business.ICiudadBusiness
import com.practica.apirest.exceptions.BusinessException
import com.practica.apirest.exceptions.NotFoundException
import com.practica.apirest.model.Ciudad
import com.practica.apirest.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_CIUDADES)
class CiudadController {

    @Autowired
    val ciudadBusiness: ICiudadBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Ciudad>> {
        return try {
            ResponseEntity(ciudadBusiness!!.list(), HttpStatus.OK)
        }catch (e:Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idCiudad:Long): ResponseEntity<Ciudad>{
        return try{
            ResponseEntity(ciudadBusiness!!.load(idCiudad), HttpStatus.OK)
        }catch (e:BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody ciudad: Ciudad): ResponseEntity<Any>{
        return try{
            ciudadBusiness!!.save(ciudad)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_CIUDADES+"/"+ciudad.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        } catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody ciudad: Ciudad): ResponseEntity<Any>{
        return try {
            ciudadBusiness!!.save(ciudad)
            ResponseEntity(HttpStatus.OK)
        } catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idCiudad:Long):ResponseEntity<Any>{
        return try{
            ciudadBusiness!!.remove(idCiudad)
            ResponseEntity(HttpStatus.OK)
        } catch (e:BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}