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

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [PurchaseHistory] from a given data source.
 */
interface PurchaseHistoryRepository {
    /**
     * Retrieve purchase history for a specific item
     */
    fun getPurchaseHistory(itemId: Int): Flow<List<PurchaseHistory>>

    /**
     * Retrieve recent purchases across all items
     */
    fun getRecentPurchases(limit: Int = 10): Flow<List<PurchaseHistory>>

    /**
     * Get average price for a specific item
     */
    suspend fun getAveragePrice(itemId: Int): Double?

    /**
     * Get minimum price for a specific item
     */
    suspend fun getMinPrice(itemId: Int): Double?

    /**
     * Get maximum price for a specific item
     */
    suspend fun getMaxPrice(itemId: Int): Double?

    /**
     * Insert purchase history entry
     */
    suspend fun insert(purchaseHistory: PurchaseHistory)

    /**
     * Delete purchase history entry
     */
    suspend fun delete(purchaseHistory: PurchaseHistory)

    /**
     * Delete all purchase history for a specific item
     */
    suspend fun deleteAllForItem(itemId: Int)
}
