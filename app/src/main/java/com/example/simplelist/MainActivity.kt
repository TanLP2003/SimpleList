package com.example.simplelist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var inputNumber: EditText? = null
    var btnEven: RadioButton? = null
    var btnOdd: RadioButton? = null
    var btnSquare: RadioButton? = null
    var btnShow: Button? = null
    var listResult: ListView? = null
    var errView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputNumber = findViewById(R.id.inputNumber)
        btnEven = findViewById(R.id.btnEven)
        btnOdd = findViewById(R.id.btnOdd)
        btnShow = findViewById(R.id.btnShow)
        btnSquare = findViewById(R.id.btnSquare)
        listResult = findViewById(R.id.listResult)
        errView = findViewById(R.id.errView)

        btnShow?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        errView?.visibility = TextView.GONE

        val inputText = inputNumber?.text.toString()
        if(inputText.isBlank()) {
            errView?.text = "Vui lòng nhập số nguyên dương"
            errView?.visibility = TextView.VISIBLE
            return
        }
        val n = inputText?.toIntOrNull() ?: 0
        var numsList= when {
            btnEven?.isChecked == true -> getEvenList(n)
            btnOdd?.isChecked == true -> getOddList(n)
            btnSquare?.isChecked == true -> getSquareList(n)
            else -> {
                errView?.visibility = TextView.VISIBLE
                errView?.text = "Vui lòng bấm vào 1 trong 3 lựa chọn"
                return
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numsList)
        listResult?.adapter = adapter
    }

    private fun getEvenList(n: Int) : List<Int> {
        val res = mutableListOf<Int>()

        for(i in 0..n) {
            if (i % 2 == 0) res.add(i)
        }
        return res
    }

    private fun getOddList(n: Int) : List<Int> {
        val res = mutableListOf<Int>()

        for(i in 1..n) {
            if (i % 2 != 0) res.add(i)
        }
        return res
    }

    private fun getSquareList(n: Int) : List<Int> {
        val res = mutableListOf<Int>()
        var i = 0
        while(i * i <= n) {
            res.add(i * i)
            i++
        }
        return res
    }
}