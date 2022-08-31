package com.practica.apirest.dao

import com.practica.apirest.model.Ciudad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CiudadRepository:JpaRepository<Ciudad,Long> {
}