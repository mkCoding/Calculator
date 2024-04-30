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
    private var currentValue = ""
    var isResultCleared = false
    var isEqualsPressed = false
    var problemList = mutableListOf<Any>()


    private var lastInputIsOperator = false //used so user doesn't enter in the operator twice

    val TAG = "MainActivity"

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


        //create a list referencing each number button
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
//         problemList = mutableListOf<Any>()

        numberButtons.forEach {numberButton->
            numberButton.setOnClickListener {

                //every time a number button is clicked add it to current value
                currentValue = currentValue + numberButton.text.toString()

                //display that value to the screen
                binding.calculatorScreen.text = currentValue

                lastInputIsOperator = false //last user input is a number
            }
        }

        operationButtons.forEach { operationButton ->
            operationButton.setOnClickListener {

//                if(isResultCleared == false){
//                    lastInputIsOperator = true
//                }

                if(lastInputIsOperator == false) { //if last value entered by user is not an operator then allow them to add operator
                    //add the fully entered number to the list
                    problemList.add(currentValue)
                    Log.d(TAG,"$problemList")


                    //after add it to the list clear the current number value
                    currentValue = ""

                    //add operation to the list
                    problemList.add(operationButton.text)
                    Log.d(TAG,"$problemList")

                    lastInputIsOperator = true //last input added was an operator at this point
                }

            }
        }



        //Equals button which will retrieve the result
        binding.btnEquals.setOnClickListener {
            isEqualsPressed = true
            problemList.add(currentValue) //the entire problem has been added to problemList

            var expressionString = problemList.joinToString (" ") //convert array to string separated by spaces
            binding.calculatorScreen.text = evaluateTheExpression(expressionString.replace("x","*")).toString() //pass space separated string to the evaluateTheExpression function



                //currentValue = "" //clear current value
//            problemList.clear()
            Log.d(TAG,"$expressionString")
            Log.d(TAG,"$problemList")

        }

        //Clear everything and set all values always zero
        binding.btnClear.setOnClickListener {
            isResultCleared = true
            binding.calculatorScreen.setText("0") //set the screen to 0
            problemList.clear() //clear the problem list (reset)
            currentValue = ""
        }


    }


    private fun evaluateTheExpression(expression: String):Int{
        var partOfExpression = expression.split(" ")

        var result = partOfExpression [0].toInt() //this will be the starting number of the result convert from string to Int

        //for loop to iterate through every other element
        for(i in 1 until partOfExpression.size step 2){

            val operator = partOfExpression[i] //holds the operator
            val operand = partOfExpression[i + 1].toInt() //holds the actual number

            when(operator){
                "+" -> result += operand
                "-" -> result -= operand
                "*" -> result *= operand
                "/" -> result /= operand
            }
        }

        problemList.clear()
        currentValue = ""

//        problemList.add(result)
        currentValue ="$result"

    //partOfExpression = emptyList()

        return result
    }


}