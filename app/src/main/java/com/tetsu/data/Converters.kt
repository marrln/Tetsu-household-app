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

import androidx.room.TypeConverter

/**
 * Type converters for Room database to handle custom types
 */
class Converters {
    @TypeConverter
    fun fromPurchaseFrequency(value: PurchaseFrequency): String {
        return value.name
    }

    @TypeConverter
    fun toPurchaseFrequency(value: String): PurchaseFrequency {
        return PurchaseFrequency.valueOf(value)
    }

    @TypeConverter
    fun fromItemCategory(value: ItemCategory): String {
        return value.name
    }

    @TypeConverter
    fun toItemCategory(value: String): ItemCategory {
        return ItemCategory.valueOf(value)
    }
}
