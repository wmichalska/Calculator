package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var number: String? = null
    private var firstNumber: Double = 0.0
    private var lastNumber: Double = 0.0
    private var status: String? = null
    private var operator: Boolean = false
    private var myFormatter = DecimalFormat("######.######")
    private var history: String? = null
    private var currentResult: String? = null
    private var dot = true
    private var btnAcControl = true
    private var btnEqualsControl = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn0 -> {
                    numberClick("0")
                }
                R.id.btn1 -> {
                    numberClick("1")
                }
                R.id.btn2 -> {
                    numberClick("2")
                }
                R.id.btn3 -> {
                    numberClick("3")
                }
                R.id.btn4 -> {
                    numberClick("4")
                }
                R.id.btn5 -> {
                    numberClick("5")
                }
                R.id.btn6 -> {
                    numberClick("6")
                }
                R.id.btn7 -> {
                    numberClick("7")
                }
                R.id.btn8 -> {
                    numberClick("8")
                }
                R.id.btn9 -> {
                    numberClick("9")
                }
                R.id.btnAC -> {
                    number = null
                    status = null
                    textViewResult.text = "0"
                    textViewHistory.text = ""
                    firstNumber = 0.0
                    lastNumber = 0.0
                    dot = true
                    btnAcControl = true

                }
                R.id.btnDel -> {
                    if (btnAcControl) {
                        textViewResult.text = "0"
                    } else {
                        number = number?.substring(0, number!!.length - 1)
                        if (number?.isEmpty() == true) {
                            btnDel.isClickable = false
                        } else dot = !number?.contains(".")!!
                        textViewResult.text = number
                    }
                }
                R.id.btnPlus -> {
                    history = textViewHistory.text.toString()
                    currentResult = textViewResult.text.toString()
                    textViewHistory.text = "$history$currentResult+"

                    if (operator) {
                        when (status) {
                            "multiplication" -> {
                                multiply()
                            }
                            "division" -> {
                                divide()
                            }
                            "subtraction" -> {
                                minus()
                            }
                            else -> {
                                plus()
                            }
                        }

                    }
                    status = "sum"
                    operator = false
                    number = null
                }
                R.id.btnMinus -> {
                    history = textViewHistory.text.toString()
                    currentResult = textViewResult.text.toString()
                    textViewHistory.text = "$history$currentResult-"
                    if (operator) {
                        when (status) {
                            "multiplication" -> {
                                multiply()
                            }
                            "division" -> {
                                divide()
                            }
                            "sum" -> {
                                plus()
                            }
                            else -> {
                                minus()
                            }
                        }
                    }
                    status = "subtraction"
                    operator = false
                    number = null
                }
                R.id.btnMultiple -> {
                    history = textViewHistory.text.toString()
                    currentResult = textViewResult.text.toString()
                    textViewHistory.text = "$history$currentResult*"
                    if (operator) {
                        when (status) {
                            "sum" -> {
                                plus()
                            }
                            "division" -> {
                                divide()
                            }
                            "subtraction" -> {
                                minus()
                            }
                            else -> {
                                multiply()
                            }
                        }
                    }
                    status = "multiplication"
                    operator = false
                    number = null
                }
                R.id.btnDivide -> {
                    history = textViewHistory.text.toString()
                    currentResult = textViewResult.text.toString()
                    textViewHistory.text = "$history$currentResult/"
                    if (operator) {
                        when (status) {
                            "multiplication" -> {
                                multiply()
                            }
                            "sum" -> {
                                plus()
                            }
                            "subtraction" -> {
                                minus()
                            }
                            else -> {
                                divide()
                            }
                        }
                    }
                    status = "division"
                    operator = false
                    number = null
                }
                R.id.btnEquals -> {
                    if (operator) {
                        when (status) {
                            "sum" -> {
                                plus()
                            }
                            "subtraction" -> {
                                minus()
                            }
                            "multiplication" -> {
                                multiply()
                            }
                            "division" -> {
                                divide()
                            }
                            else -> {
                                firstNumber =
                                    java.lang.Double.parseDouble(textViewResult.text.toString())
                            }
                        }
                    }

                    operator = false
                    btnEqualsControl = true
                }
                R.id.btnDot -> {
                    if (dot) {
                        if (number == null) {
                            number = "0."
                        } else {
                            number += "."
                        }
                    }

                    textViewResult.text = number
                    dot = false
                }
            }
        }
    }

    private fun numberClick(view: String) {
        when {
            number == null -> {
                number = view
            }
            btnEqualsControl -> {
                firstNumber = 0.0
                lastNumber = 0.0
                number = view
            }
            else -> {
                number += view
            }
        }
        textViewResult.text = number
        operator = true
        btnAcControl = false
        btnDel.isClickable = true
        btnEqualsControl = false
    }

    @SuppressLint("SetTextI18n")
    private fun plus() {
        lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
        firstNumber += lastNumber
        textViewResult.text = myFormatter.format(firstNumber)
        dot = true
    }

    @SuppressLint("SetTextI18n")
    private fun minus() {
        if (firstNumber == 0.0) {
            firstNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
        } else {
            lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
            firstNumber -= lastNumber
        }
        textViewResult.text = myFormatter.format(firstNumber)
        dot = true
    }

    @SuppressLint("SetTextI18n")
    private fun multiply() {
        if (firstNumber == 0.0) {
            firstNumber = 1.0
            lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
            firstNumber *= lastNumber
        } else {
            lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
            firstNumber *= lastNumber
        }
        textViewResult.text = myFormatter.format(firstNumber)
        dot = true
    }

    @SuppressLint("SetTextI18n")
    private fun divide() {
        if (firstNumber == 0.0) {

            lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
            firstNumber = lastNumber / 1
        } else {
            lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
            firstNumber /= lastNumber
        }
        textViewResult.text = myFormatter.format(firstNumber)
        dot = true
    }

}