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

class Waiter(
    private val calculator: Calculator,
    private val printer: Printer<Int>) {
  fun countAndPrintReceipt(vararg prices: Int) {
    val total = calculator.countTotal(*prices)
    printer.print(total)
  }
}

interface Calculator {
  fun countTotal(vararg prices: Int): Int
}

class SimpleCalculator : Calculator {
  override fun countTotal(vararg prices: Int) = prices.sum()
}

interface Printer<in T> {
  fun print(t: T)
}

class ConsoleTotalPrinter : Printer<Int> {
  override fun print(t: Int) = println("TOTAL: $t")
}

fun main(args: Array<String>) {
  val waiter = Waiter(SimpleCalculator(), ConsoleTotalPrinter())
  waiter.countAndPrintReceipt(5, 13, 26, 7, 19)
}
