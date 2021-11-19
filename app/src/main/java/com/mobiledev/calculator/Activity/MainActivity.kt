package com.mobiledev.calculator.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mobiledev.calculator.R
import com.mobiledev.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializationComponentsView()
        setListeners()
    }

    private fun initializationComponentsView() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setListeners() {
        mainBinding.ac.setOnClickListener { performAcAction() }
        mainBinding.zero.setOnClickListener { mainBinding.inputText.append("0") }
        mainBinding.one.setOnClickListener { mainBinding.inputText.append("1") }
        mainBinding.two.setOnClickListener { mainBinding.inputText.append("2") }
        mainBinding.three.setOnClickListener { mainBinding.inputText.append("3") }
        mainBinding.four.setOnClickListener { mainBinding.inputText.append("4") }
        mainBinding.five.setOnClickListener { mainBinding.inputText.append("5") }
        mainBinding.six.setOnClickListener { mainBinding.inputText.append("6") }
        mainBinding.seven.setOnClickListener { mainBinding.inputText.append("7") }
        mainBinding.eight.setOnClickListener { mainBinding.inputText.append("8") }
        mainBinding.nine.setOnClickListener { mainBinding.inputText.append("9") }
        mainBinding.point.setOnClickListener { mainBinding.inputText.append(".") }
        mainBinding.plus.setOnClickListener { mainBinding.inputText.append(" + ") }
        mainBinding.minus.setOnClickListener { mainBinding.inputText.append(" - ") }
        mainBinding.multiply.setOnClickListener { mainBinding.inputText.append(" * ") }
        mainBinding.share.setOnClickListener { mainBinding.inputText.append(" / ") }
        mainBinding.leftBracket.setOnClickListener { mainBinding.inputText.append(" ( ") }
        mainBinding.rightBracket.setOnClickListener { mainBinding.inputText.append(" ) ") }
        mainBinding.equally.setOnClickListener { calculateAction() }
    }

    private fun performAcAction() {
        mainBinding.inputText.text = ""
        mainBinding.outputText.text = ""
    }

    private fun calculateAction() {
        val expression = ExpressionBuilder(mainBinding.inputText.text.toString()).build()
        val (result, longResult) = getCalculateResult(expression)

        when (result == longResult.toDouble()) {
            true -> mainBinding.outputText.text = longResult.toString()
            false -> mainBinding.outputText.text = result.toString()
        }

    }

    private fun getCalculateResult(expression: Expression): Pair<Double, Long> {
        return try {
            val result = expression.evaluate()
            val longResult = result.toLong()
            Pair(result, longResult)
        } catch (ex: Exception) {
            Pair(0.0, 0)
        }
    }


}