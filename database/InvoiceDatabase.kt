package com.example.invoice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.invoice.database.entities.Client
import com.example.invoice.database.entities.DanceClass
import com.example.invoice.database.entities.Invoice
import com.example.invoice.database.entities.InvoiceDao
import com.example.invoice.database.relations.DanceClassInvoiceCrossRef

@Database(
    entities = [
        Client::class,
        DanceClass::class,
        Invoice::class,
        DanceClassInvoiceCrossRef::class],
    version = 1
)

abstract class InvoiceDatabase: RoomDatabase() {

    abstract val invoiceDao: InvoiceDao

    companion object {
        @Volatile
        private var INSTANCE: InvoiceDatabase? = null

        fun getInstance(context: Context): InvoiceDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    InvoiceDatabase::class.java,
                    "invoice_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}