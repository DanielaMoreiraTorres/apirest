package com.practica.apirest.business

import com.practica.apirest.dao.CiudadRepository
import com.practica.apirest.exceptions.BusinessException
import com.practica.apirest.model.Ciudad
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.Optional

@Service
class CiudadBusiness: ICiudadBusiness {

    @Autowired
    val ciudadRepository: CiudadRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Ciudad> {
        try {
            return ciudadRepository!!.findAll()
        } catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, com.practica.apirest.exceptions.NotFoundException::class)
    override fun load(idCiudad: Long): Ciudad {
        val op: Optional<Ciudad>
        try {
            op = ciudadRepository!!.findById(idCiudad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent) {
            throw com.practica.apirest.exceptions.NotFoundException("No se encontro ciudad con id $idCiudad")
        }
        return op.get()
    }

    override fun save(ciudad: Ciudad): Ciudad {
        try{
            return ciudadRepository!!.save(ciudad)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun remove(idCiudad: Long) {
        val op: Optional<Ciudad>
        try {
            op = ciudadRepository!!.findById(idCiudad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent) {
            throw com.practica.apirest.exceptions.NotFoundException("No se encontro ciudad con id $idCiudad")
        }else {
            try{
                ciudadRepository!!.deleteById(idCiudad)
            } catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

    }

}