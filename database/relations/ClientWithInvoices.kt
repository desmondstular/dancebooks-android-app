package com.example.invoice.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.invoice.database.entities.Client
import com.example.invoice.database.entities.Invoice

data class ClientWithInvoices(
    @Embedded val  client: Client,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "clientId"
    )
    val invoices: List<Invoice>
)
