package com.practica.apirest.business

import com.practica.apirest.model.Ciudad

interface ICiudadBusiness {

    fun list():List<Ciudad>
    fun load(idCiudad:Long):Ciudad
    fun save(ciudad:Ciudad):Ciudad
    fun remove(idCiudad:Long)
}