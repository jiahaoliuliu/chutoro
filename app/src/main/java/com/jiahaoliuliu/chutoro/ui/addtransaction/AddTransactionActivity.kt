package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.*
import com.jiahaoliuliu.chutoro.R
import com.jiahaoliuliu.chutoro.ui.MainApplication
import com.jiahaoliuliu.chutoro.entity.Currency

import kotlinx.android.synthetic.main.activity_add_transaction.*
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject

class AddTransactionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
        AddTransactionContract.View {
    @Inject
    lateinit var presenter: AddTransactionContract.Presenter

    private val defaultCurrency = Currency.AED
    private var currency = defaultCurrency

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
            // TODO: Pass the date
            presenter.addTransactionIfCorrect(destinationED.text.toString(), sourceED.text.toString(),
                    quantity.text.toString(), currency, Date().time)
        }

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = getString(R.string.add_transaction_title)
    }

    // Spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position >= 0 && position < Currency.values().size) {
            currency = Currency.values()[position]
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        currency = defaultCurrency
    }

    override fun onInsertionCorrect() {
        Toast.makeText(this, "The transaction has been correctly inserted",
                Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showInsertionError(exception: IllegalArgumentException) {
        Toast.makeText(this,
                "Error inserting the transaction ${exception.localizedMessage}",
                Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter?.dispose()
        super.onDestroy()
    }
}
