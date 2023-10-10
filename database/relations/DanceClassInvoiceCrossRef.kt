package com.example.invoice.database.relations

import androidx.room.Entity

@Entity(primaryKeys = ["classId", "invoiceId"])
data class DanceClassInvoiceCrossRef(
    val classId: Int,
    val invoiceId: Int
)
