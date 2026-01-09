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

package com.tetsu.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Database access object for discounts
 */
@Dao
interface DiscountDao {
    
    @Query("SELECT * FROM discounts WHERE itemId = :itemId ORDER BY startDate DESC")
    fun getDiscountsForItem(itemId: Int): Flow<List<Discount>>
    
    @Query("SELECT * FROM discounts WHERE endDate > :currentDate OR endDate IS NULL ORDER BY startDate DESC")
    fun getActiveDiscounts(currentDate: Long = System.currentTimeMillis()): Flow<List<Discount>>
    
    @Query("SELECT * FROM discounts WHERE endDate IS NOT NULL AND endDate <= :currentDate ORDER BY endDate DESC")
    fun getExpiredDiscounts(currentDate: Long = System.currentTimeMillis()): Flow<List<Discount>>
    
    @Query("SELECT * FROM discounts ORDER BY startDate DESC")
    fun getAllDiscounts(): Flow<List<Discount>>
    
    @Query("SELECT SUM(originalPrice - discountedPrice) FROM discounts WHERE itemId = :itemId")
    suspend fun getTotalSavingsForItem(itemId: Int): Double?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(discount: Discount)
    
    @Update
    suspend fun update(discount: Discount)
    
    @Delete
    suspend fun delete(discount: Discount)
    
    @Query("DELETE FROM discounts WHERE itemId = :itemId")
    suspend fun deleteAllForItem(itemId: Int)
}
