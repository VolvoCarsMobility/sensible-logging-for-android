/*
 * Copyright 2022 Volvo Cars Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sensiblelogging.filter

import com.sensiblelogging.Category
import com.sensiblelogging.Line

abstract class CategoryFilter : FieldFilter<Category> {
    abstract val categories: List<Category>
    override fun locateField(line: Line): Category = line.category
    override fun isAllowed(field: Category): Boolean = categories.any { field == it }
}
