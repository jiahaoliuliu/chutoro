package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.*
import com.jiahaoliuliu.chutoro.R
import com.jiahaoliuliu.chutoro.ui.MainApplication

import kotlinx.android.synthetic.main.activity_add_transaction.*
import javax.inject.Inject

class AddTransactionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
        AddTransactionContract.View {

    companion object {
        private const val DEFAULT_CURRENCY = "Dirhams"
    }

    @Inject
    lateinit var presenter: AddTransactionContract.Presenter

    var currency: String = DEFAULT_CURRENCY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        setSupportActionBar(toolbar)

        MainApplication.getMainComponent().inject(this)

        presenter.setView(this)

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
            presenter.addTransactionIfCorrect(destinationED.text.toString(), sourceED.text.toString(),
                    quantity.text.toString(), currency)
        }

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = getString(R.string.add_transaction_title)
    }

    // Spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currency = parent?.getItemAtPosition(position) as String
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        currency = DEFAULT_CURRENCY
    }
}
