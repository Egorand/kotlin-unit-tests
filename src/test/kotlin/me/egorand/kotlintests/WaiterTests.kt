/*
 * Copyright 2017 Egor Andreevici
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.egorand.kotlintests

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.amshove.kluent.`it returns`
import org.junit.Before
import org.junit.Test

class WaiterTests {

  private val mockCalculator = mock<Calculator>()
  private val mockPrinter = mock<Printer<Int>>()

  private lateinit var waiter: Waiter

  @Before fun `init waiter`() {
    waiter = Waiter(mockCalculator, mockPrinter)
  }

  @Test fun `should count total and print receipt`() {
    val prices = intArrayOf(1, 2, 3, 4, 5)
    whenever(mockCalculator.countTotal(*prices)) `it returns` 15

    waiter.countAndPrintReceipt(*prices)

    verify(mockPrinter).print(15)
  }
}