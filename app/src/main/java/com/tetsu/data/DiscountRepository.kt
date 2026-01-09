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

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Discount] from a given data source.
 */
interface DiscountRepository {
    /**
     * Retrieve all discounts for a specific item
     */
    fun getDiscountsForItem(itemId: Int): Flow<List<Discount>>

    /**
     * Retrieve all currently active discounts
     */
    fun getActiveDiscounts(currentDate: Long = System.currentTimeMillis()): Flow<List<Discount>>

    /**
     * Retrieve all expired discounts
     */
    fun getExpiredDiscounts(currentDate: Long = System.currentTimeMillis()): Flow<List<Discount>>

    /**
     * Retrieve all discounts
     */
    fun getAllDiscounts(): Flow<List<Discount>>

    /**
     * Get total savings for a specific item
     */
    suspend fun getTotalSavingsForItem(itemId: Int): Double?

    /**
     * Insert discount
     */
    suspend fun insert(discount: Discount)

    /**
     * Update discount
     */
    suspend fun update(discount: Discount)

    /**
     * Delete discount
     */
    suspend fun delete(discount: Discount)

    /**
     * Delete all discounts for a specific item
     */
    suspend fun deleteAllForItem(itemId: Int)
}
