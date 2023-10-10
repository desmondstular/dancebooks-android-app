package com.example.invoice.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(
    @PrimaryKey(autoGenerate = true)
    val clientId: Int = 0,
    val name: String,
    val email: String,
    val address: String
)
