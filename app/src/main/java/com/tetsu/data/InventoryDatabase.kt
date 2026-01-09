/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tetsu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Database class with a singleton Instance object.
 * Version 2: Added purchase frequency, categories, purchase history, and discount tracking
 */
@Database(
    entities = [Item::class, PurchaseHistory::class, Discount::class], 
    version = 2, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
    abstract fun discountDao(): DiscountDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .addMigrations(MIGRATION_1_2)
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

        /**
         * Migration from version 1 to version 2
         * Adds new fields to items table and creates purchase_history and discounts tables
         */
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add new columns to items table
                database.execSQL("ALTER TABLE items ADD COLUMN purchaseFrequency TEXT NOT NULL DEFAULT 'MONTHLY'")
                database.execSQL("ALTER TABLE items ADD COLUMN lastPurchaseDate INTEGER")
                database.execSQL("ALTER TABLE items ADD COLUMN averagePrice REAL NOT NULL DEFAULT 0.0")
                database.execSQL("ALTER TABLE items ADD COLUMN category TEXT NOT NULL DEFAULT 'UNCATEGORIZED'")
                database.execSQL("ALTER TABLE items ADD COLUMN notes TEXT NOT NULL DEFAULT ''")

                // Create purchase_history table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS purchase_history (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        itemId INTEGER NOT NULL,
                        purchaseDate INTEGER NOT NULL,
                        price REAL NOT NULL,
                        quantity INTEGER NOT NULL,
                        storeName TEXT NOT NULL DEFAULT '',
                        wasOnDiscount INTEGER NOT NULL DEFAULT 0,
                        FOREIGN KEY(itemId) REFERENCES items(id) ON DELETE CASCADE
                    )
                """.trimIndent())
                database.execSQL("CREATE INDEX IF NOT EXISTS index_purchase_history_itemId ON purchase_history(itemId)")

                // Create discounts table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS discounts (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        itemId INTEGER NOT NULL,
                        originalPrice REAL NOT NULL,
                        discountedPrice REAL NOT NULL,
                        discountPercentage REAL NOT NULL,
                        startDate INTEGER NOT NULL,
                        endDate INTEGER,
                        storeName TEXT NOT NULL DEFAULT '',
                        notes TEXT NOT NULL DEFAULT '',
                        FOREIGN KEY(itemId) REFERENCES items(id) ON DELETE CASCADE
                    )
                """.trimIndent())
                database.execSQL("CREATE INDEX IF NOT EXISTS index_discounts_itemId ON discounts(itemId)")
            }
        }
    }
}
