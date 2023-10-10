package com.example.invoice.database.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.invoice.database.relations.ClientWithInvoices
import com.example.invoice.database.relations.DanceClassInvoiceCrossRef
import com.example.invoice.database.relations.DanceClassWithInvoices
import com.example.invoice.database.relations.InvoiceWithDanceClasses
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    //Client Dao
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

    //DanceClassDao
    @Upsert
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Query("SELECT * FROM client ORDER BY clientId ASC")
    fun getClientsOrderedByClientId(): Flow<List<Client>>

    @Query("SELECT * FROM client ORDER BY name ASC")
    fun getClientsOrderedByName(): Flow<List<Client>>

    @Query("SELECT * FROM client ORDER BY email ASC")
    fun getClientsOrderedByEmail(): Flow<List<Client>>

    @Query("SELECT * FROM client ORDER BY address ASC")
    fun getClientsOrderedByAddress(): Flow<List<Client>>

    @Transaction
    @Query("SELECT * FROM client WHERE clientId = :clientId")
    suspend fun getClientWithInvoices(clientId: Int): List<ClientWithInvoices>
}