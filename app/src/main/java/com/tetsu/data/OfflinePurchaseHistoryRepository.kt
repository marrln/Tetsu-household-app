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
 * Offline implementation of PurchaseHistoryRepository
 */
class OfflinePurchaseHistoryRepository(
    private val purchaseHistoryDao: PurchaseHistoryDao
) : PurchaseHistoryRepository {
    
    override fun getPurchaseHistory(itemId: Int): Flow<List<PurchaseHistory>> =
        purchaseHistoryDao.getPurchaseHistory(itemId)

    override fun getRecentPurchases(limit: Int): Flow<List<PurchaseHistory>> =
        purchaseHistoryDao.getRecentPurchases(limit)

    override suspend fun getAveragePrice(itemId: Int): Double? =
        purchaseHistoryDao.getAveragePrice(itemId)

    override suspend fun getMinPrice(itemId: Int): Double? =
        purchaseHistoryDao.getMinPrice(itemId)

    override suspend fun getMaxPrice(itemId: Int): Double? =
        purchaseHistoryDao.getMaxPrice(itemId)

    override suspend fun insert(purchaseHistory: PurchaseHistory) =
        purchaseHistoryDao.insert(purchaseHistory)

    override suspend fun delete(purchaseHistory: PurchaseHistory) =
        purchaseHistoryDao.delete(purchaseHistory)

    override suspend fun deleteAllForItem(itemId: Int) =
        purchaseHistoryDao.deleteAllForItem(itemId)
}
