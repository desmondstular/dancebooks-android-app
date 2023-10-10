package com.example.invoice.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    val invoiceId: Int = 0,
    val clientId: Int
)