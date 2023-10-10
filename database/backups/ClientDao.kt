package com.example.invoice.database.backups

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.invoice.database.entities.Client
import com.example.invoice.database.entities.Invoice
import com.example.invoice.database.relations.ClientWithInvoices
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Upsert
    suspend fun insertClient(client: Client)

    @Upsert
    suspend fun insertInvoice(invoice: Invoice)

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