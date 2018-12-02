package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Button
import com.jiahaoliuliu.chutoro.R

import kotlinx.android.synthetic.main.activity_add_transaction.*

class AddTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        setSupportActionBar(toolbar)
        val pressMeButton = findViewById<Button>(R.id.pressMe)
        pressMeButton.setOnClickListener { _ ->
            setResult(Activity.RESULT_OK, null)
            finish()
        }
    }
}
