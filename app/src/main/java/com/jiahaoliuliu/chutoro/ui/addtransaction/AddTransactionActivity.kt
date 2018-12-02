package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.jiahaoliuliu.chutoro.R

import kotlinx.android.synthetic.main.activity_add_transaction.*

// TODO: Use MVP for this
class AddTransactionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object {
        private const val DEFAULT_CURRENCY = "Dirhams"
    }
    var currency: String = DEFAULT_CURRENCY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        setSupportActionBar(toolbar)

        // Link the views
        val destinationED = findViewById<EditText>(R.id.destination)
        val sourceED = findViewById<EditText>(R.id.source)
        val quantity = findViewById<EditText>(R.id.quantity)
        val currencySpinner = findViewById<Spinner>(R.id.currency)
        ArrayAdapter.createFromResource(
                this,
                R.array.currencies_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            currencySpinner.adapter = adapter
        }
        currencySpinner.onItemSelectedListener = this

        val addTransactionButton = findViewById<Button>(R.id.add)
        addTransactionButton.setOnClickListener { _ ->
            addTransactionIfCorrect(destinationED.text.toString(), sourceED.text.toString(),
                    quantity.text.toString())
        }

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = getString(R.string.add_transaction_title)
    }

    private fun addTransactionIfCorrect(destination: String, source: String, quantity: String) {
        if (isAllDataValid(destination, source, quantity)) {

        }
    }

    private fun isAllDataValid(destination: String, source: String, quantity: String): Boolean {
        return isDestinationValid(destination) && isSourceValid(source) &&
                isQuantityValid(quantity)
    }

    private fun isDestinationValid(destination: String): Boolean {
        return !TextUtils.isEmpty(destination)
    }

    private fun isSourceValid(source: String): Boolean {
        return !TextUtils.isEmpty(source)
    }

    private fun isQuantityValid(quantity: String): Boolean {
        return quantity.toFloatOrNull() != null
    }

    // Spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currency = parent?.getItemAtPosition(position) as String
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        currency = DEFAULT_CURRENCY
    }
}
