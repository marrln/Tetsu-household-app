/*
 * Tetsu Household Assistant App - Phase 1.1 Enhancement
 * Copyright (C) 2026 marrln
 *
 * This file is part of Tetsu.
 *
 * Tetsu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Tetsu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Tetsu.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.tetsu.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access object for purchase history
 */
@Dao
interface PurchaseHistoryDao {
    
    @Query("SELECT * FROM purchase_history WHERE itemId = :itemId ORDER BY purchaseDate DESC")
    fun getPurchaseHistory(itemId: Int): Flow<List<PurchaseHistory>>
    
    @Query("SELECT * FROM purchase_history ORDER BY purchaseDate DESC LIMIT :limit")
    fun getRecentPurchases(limit: Int = 10): Flow<List<PurchaseHistory>>
    
    @Query("SELECT AVG(price) FROM purchase_history WHERE itemId = :itemId")
    suspend fun getAveragePrice(itemId: Int): Double?
    
    @Query("SELECT MIN(price) FROM purchase_history WHERE itemId = :itemId")
    suspend fun getMinPrice(itemId: Int): Double?
    
    @Query("SELECT MAX(price) FROM purchase_history WHERE itemId = :itemId")
    suspend fun getMaxPrice(itemId: Int): Double?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(purchaseHistory: PurchaseHistory)
    
    @Delete
    suspend fun delete(purchaseHistory: PurchaseHistory)
    
    @Query("DELETE FROM purchase_history WHERE itemId = :itemId")
    suspend fun deleteAllForItem(itemId: Int)
}
