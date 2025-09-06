package com.filipdadgar.calc.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _display = MutableLiveData<String>("0")
    val display: LiveData<String> = _display

    private var currentInput = ""
    private var expression = ""
    private var operand: Double? = null
    private var pendingOperator: String? = null
    private var userIsInTheMiddleOfTyping = false

    fun onDigit(digit: String) {
        if (!userIsInTheMiddleOfTyping) {
            currentInput = digit
            userIsInTheMiddleOfTyping = true
        } else {
            currentInput += digit
        }
        updateDisplay()
    }

    fun onDot() {
        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) {
                currentInput = "0."
            } else {
                currentInput += "."
            }
            userIsInTheMiddleOfTyping = true
            updateDisplay()
        }
    }

    fun onOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            expression += if (expression.isEmpty()) currentInput else " $currentInput"
            expression += " $operator"
            operand = currentInput.toDoubleOrNull()
            currentInput = ""
        } else if (expression.isNotEmpty() && expression.last() in "+-×÷") {
            expression = expression.dropLast(1) + operator
        }
        pendingOperator = operator
        userIsInTheMiddleOfTyping = false
        updateDisplay()
    }

    fun onEquals() {
        if (pendingOperator != null && currentInput.isNotEmpty()) {
            val firstOperand = operand ?: 0.0
            val secondOperand = currentInput.toDoubleOrNull() ?: 0.0
            val result = when (pendingOperator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "×" -> firstOperand * secondOperand
                "÷" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
                else -> secondOperand
            }
            expression += " $currentInput ="
            currentInput = if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
            operand = null
            pendingOperator = null
            userIsInTheMiddleOfTyping = false
            updateDisplay(true)
            expression = ""
        }
    }

    fun onClear() {
        currentInput = ""
        expression = ""
        operand = null
        pendingOperator = null
        userIsInTheMiddleOfTyping = false
        _display.value = "0"
    }

    fun onDelete() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
        }
        updateDisplay()
    }

    private fun updateDisplay(showResult: Boolean = false) {
        _display.value = if (showResult) currentInput else (expression + (if (currentInput.isNotEmpty()) " $currentInput" else "")).trim()
        if (_display.value.isNullOrEmpty()) _display.value = "0"
    }
}
