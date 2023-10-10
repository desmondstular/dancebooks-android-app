package com.example.invoice.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.invoice.database.entities.DanceClass

data class DanceClassWithInvoices(
    @Embedded val danceClass: DanceClass,
    @Relation(
        parentColumn = "classId",
        entityColumn = "classId",
        associateBy = Junction(DanceClassInvoiceCrossRef::class)
    )
    val danceClasses: List<DanceClass>
)
