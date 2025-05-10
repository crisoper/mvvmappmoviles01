package com.example.mvvmapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val imagen: String,
    val precio: Double
)