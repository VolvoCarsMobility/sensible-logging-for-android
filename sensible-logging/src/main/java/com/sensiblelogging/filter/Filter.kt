/*
 * Copyright 2022 Volvo Car Mobility AB
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
import com.sensiblelogging.Level
import com.sensiblelogging.Line

interface Filter {
    fun matches(line: Line): Boolean

    companion object {
        fun level(level: Level): Filter = SimpleLogLevelFilter(level)
        fun categories(categories: List<Category>): Filter = SimpleCategoryFilter(categories)
        fun allowAll(): Filter = AllowAllFilter
    }
}
