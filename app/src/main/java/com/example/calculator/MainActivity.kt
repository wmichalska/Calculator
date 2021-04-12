package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var number: String? = null
    var firstNumber: Double = 0.0
    var lastNumber: Double = 0.0
    var status: String? = null
    var operator: Boolean = false
    var myFormatter = DecimalFormat("######.######")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

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

                }
                R.id.btnDel -> {

                }
                R.id.btnPlus -> {
                    if (operator) {
                        if (status == "multiplication") {
                            multiply()
                        } else if (status == "division") {
                            divide()
                        } else if (status == "subtraction") {
                            minus()
                        } else {
                            plus()
                        }

                    }
                    status = "sum"
                    operator = false
                    number = null
                }
                R.id.btnMinus -> {
                    if (operator) {
                        if (status == "multiplication") {
                            multiply()
                        } else if (status == "division") {
                            divide()
                        } else if (status == "sum") {
                            plus()
                        } else {
                            minus()
                        }
                    }
                    status = "subtraction"
                    operator = false
                    number = null
                }
                R.id.btnMultiple -> {
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
                    if (operator) {
                        if (status == "multiplication") {
                            multiply()
                        } else if (status == "sum") {
                            plus()
                        } else if (status == "subtraction") {
                            minus()
                        } else {
                            divide()
                        }
                    }
                    status = "division"
                    operator = false
                    number = null
                }
                R.id.btnEquals -> {
                    if (operator) {
                        if (status == "sum") {
                            plus()
                        } else if (status == "subtraction") {
                            minus()
                        } else if (status == "multiplication") {
                            multiply()
                        } else if (status == "division") {
                            divide()
                        }else{
                            firstNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
                        }
                    }

                    operator=false

                }
                R.id.btnDot -> {

                }

            }
        }
    }

    private fun numberClick(view: String) {
        if (number == null) {
            number = view
        } else {
            number = number + view
        }
        textViewResult.text = number
        operator = true
    }

    @SuppressLint("SetTextI18n")
    private fun plus() {
        lastNumber = java.lang.Double.parseDouble(textViewResult.text.toString())
        firstNumber += lastNumber
        textViewResult.text = myFormatter.format(firstNumber)
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
    }

}