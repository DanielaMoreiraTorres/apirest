package com.practica.apirest.model

import javax.persistence.*

@Entity
@Table(name="ciudad")
data class Ciudad (val ciudad:String="", val temperatura:String="", val descripcion:String="", val foto:String=""){

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    var id:Long = 0
}