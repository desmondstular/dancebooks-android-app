package com.example.invoice.database.backups

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.invoice.database.entities.DanceClass
import com.example.invoice.database.entities.Invoice
import com.example.invoice.database.relations.DanceClassInvoiceCrossRef
import com.example.invoice.database.relations.DanceClassWithInvoices
import com.example.invoice.database.relations.InvoiceWithDanceClasses
import kotlinx.coroutines.flow.Flow

@Dao
interface DanceClassDao {
    @Upsert
    suspend fun insertDanceClass(danceClass: DanceClass)

    @Upsert
    suspend fun insertInvoice(invoice: Invoice)

    @Upsert
    suspend fun insertDanceClassInvoiceCrossRef(crossRef: DanceClassInvoiceCrossRef)

    @Delete
    suspend fun deleteDanceClass(danceClass: DanceClass)

    @Query("SELECT * FROM DanceClass ORDER BY classId ASC")
    fun getDanceClassOrderedByClassId(): Flow<List<DanceClass>>

    @Query("SELECT * FROM DanceClass ORDER BY name ASC")
    fun getDanceClassOrderedByName(): Flow<List<DanceClass>>

    @Query("SELECT * FROM DanceClass ORDER BY lumpSumCost ASC")
    fun getDanceClassOrderedByLumpSumCost(): Flow<List<DanceClass>>

    @Query("SELECT * FROM DanceClass ORDER BY biAnnualCost ASC")
    fun getDanceClassOrderedByBiAnnualCost(): Flow<List<DanceClass>>

    @Query("SELECT * FROM DanceClass ORDER BY monthlyCost ASC")
    fun getDanceClassOrderedByMonthlyCost(): Flow<List<DanceClass>>

    @Transaction
    @Query("SELECT * FROM Invoice WHERE invoiceId = :invoiceId")
    suspend fun getDanceClassesOfInvoice(invoiceId: Int): List<DanceClassWithInvoices>

    @Transaction
    @Query("SELECT * FROM DanceClass WHERE classId = :classId")
    suspend fun getInvoicesOfDanceClass(classId: Int): List<InvoiceWithDanceClasses>
}