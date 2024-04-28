package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        //boolean for if number or operation clicked
        var isNumberLastSelected = false
        var isOperationLastSelected = false


        //create a list referencing each button
        val numberButtons = mutableListOf<ExtendedFloatingActionButton>(
            binding.btnNumber0,
            binding.btnNumber1,
            binding.btnNumber2,
            binding.btnNumber3,
            binding.btnNumber4,
            binding.btnNumber5,
            binding.btnNumber5,
            binding.btnNumber6,
            binding.btnNumber7,
            binding.btnNumber8,
            binding.btnNumber9,
        )
        //list referencing all operation buttons
        val operationButtons = mutableListOf<ExtendedFloatingActionButton>(
            binding.btnAdd,
            binding.btnSubtract,
            binding.btnDivide,
            binding.btnMultiply,
        )

        //Create list to hold the entire problem to a list
        val problemList = mutableListOf<Any>()

        numberButtons.forEach {numberButton->
            numberButton.setOnClickListener {
                problemList.add(numberButton.text) //add it to problem list
            }
        }

        operationButtons.forEach { operationButton ->
            operationButton.setOnClickListener {
                problemList.add(operationButton.text)
            }
        }


        //Clear is always zero
        binding.btnClear.setOnClickListener { binding.calculatorScreen.setText("0") }

        //When number is clicked update text view and add it to the list
        binding.btnNumber0.setOnClickListener {
            binding.calculatorScreen.setText("0")
            problemList.add(0)

        }
        binding.btnNumber1.setOnClickListener {
            binding.calculatorScreen.setText("2")
            problemList.add(1)
        }
        binding.btnNumber2.setOnClickListener {
            binding.calculatorScreen.setText("2")
            problemList.add(2)
        }

        binding.btnNumber3.setOnClickListener {
            binding.calculatorScreen.setText("3")
            problemList.add(3)
        }

        binding.btnNumber4.setOnClickListener {
            binding.calculatorScreen.setText("4")
            problemList.add(4)
        }
        binding.btnNumber5.setOnClickListener {
            binding.calculatorScreen.setText("5")
            problemList.add(5)
        }
        binding.btnNumber6.setOnClickListener {
            binding.calculatorScreen.setText("6")
            problemList.add(6)
        }
        binding.btnNumber7.setOnClickListener {
            binding.calculatorScreen.setText("7")
            problemList.add(7)
        }
        binding.btnNumber8.setOnClickListener {
            binding.calculatorScreen.setText("8")
            problemList.add(8)
        }
        binding.btnNumber9.setOnClickListener {
            binding.calculatorScreen.setText("9")
            problemList.add(9)
        }

        //when operation is clicked only add it to the list

        binding.btnDivide.setOnClickListener {
            problemList.add("/")
        }
        binding.btnMultiply.setOnClickListener {
            problemList.add("*")
        }

        binding.btnSubtract.setOnClickListener {
            problemList.add("-")
        }

        binding.btnAdd.setOnClickListener {
            problemList.add("+")
        }


        binding.btnEquals.setOnClickListener {
            Log.d("MainActivity","$problemList")

        }

    }
    fun addition(num1:Int, num2:Int):Int{
        return num1 + num2
    }

    fun subtraction(num1:Int, num2:Int):Int{
        return num1 - num2
    }

    fun division(num1:Int, num2:Int):Int{
        return num1 / num2
    }

    fun multiplication(num1: Int, num2:Int):Int{
        return num1 * num2
    }



}