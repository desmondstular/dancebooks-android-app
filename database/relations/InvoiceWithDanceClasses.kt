package com.example.invoice.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.invoice.database.entities.Invoice

data class InvoiceWithDanceClasses(
    @Embedded val invoice: Invoice,
    @Relation(
        parentColumn = "invoiceId",
        entityColumn = "invoiceId",
        associateBy = Junction(DanceClassInvoiceCrossRef::class)
    )
    val danceClasses: List<Invoice>
)
