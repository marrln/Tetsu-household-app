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

/**
 * Enum representing how frequently an item should be purchased
 */
enum class PurchaseFrequency {
    DAILY,
    WEEKLY,
    BI_WEEKLY,
    MONTHLY,
    QUARTERLY,
    YEARLY
}
