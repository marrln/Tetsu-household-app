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
 * Offline implementation of DiscountRepository
 */
class OfflineDiscountRepository(
    private val discountDao: DiscountDao
) : DiscountRepository {
    
    override fun getDiscountsForItem(itemId: Int): Flow<List<Discount>> =
        discountDao.getDiscountsForItem(itemId)

    override fun getActiveDiscounts(currentDate: Long): Flow<List<Discount>> =
        discountDao.getActiveDiscounts(currentDate)

    override fun getExpiredDiscounts(currentDate: Long): Flow<List<Discount>> =
        discountDao.getExpiredDiscounts(currentDate)

    override fun getAllDiscounts(): Flow<List<Discount>> =
        discountDao.getAllDiscounts()

    override suspend fun getTotalSavingsForItem(itemId: Int): Double? =
        discountDao.getTotalSavingsForItem(itemId)

    override suspend fun insert(discount: Discount) =
        discountDao.insert(discount)

    override suspend fun update(discount: Discount) =
        discountDao.update(discount)

    override suspend fun delete(discount: Discount) =
        discountDao.delete(discount)

    override suspend fun deleteAllForItem(itemId: Int) =
        discountDao.deleteAllForItem(itemId)
}
