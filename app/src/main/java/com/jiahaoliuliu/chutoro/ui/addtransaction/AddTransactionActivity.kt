package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.jiahaoliuliu.chutoro.R
import com.jiahaoliuliu.chutoro.ui.MainApplication
import com.jiahaoliuliu.chutoro.entity.Currency

import kotlinx.android.synthetic.main.activity_add_transaction.*
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject
import android.widget.AdapterView
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener
import com.jiahaoliuliu.chutoro.entity.Source
import java.text.SimpleDateFormat

class AddTransactionActivity : AppCompatActivity(), AddTransactionContract.View {

    companion object {
        private const val DATE_FORMAT_DATE = "dd MMMM yyyy"
    }

    @Inject
    lateinit var presenter: AddTransactionContract.Presenter

    // Currency
    private val defaultCurrency = Currency.AED
    private var currency = defaultCurrency

    // Source
    private val defaultSource = Source.ADCB
    private var source = defaultSource

    private val simpleDateFormatter = SimpleDateFormat(DATE_FORMAT_DATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        setSupportActionBar(toolbar)

        MainApplication.getMainComponent().inject(this)

        presenter.setView(this)

        // Link the views
        val destinationED = findViewById<EditText>(R.id.destination)
        val sourceSpinner = findViewById<Spinner>(R.id.source)
        val quantity = findViewById<EditText>(R.id.quantity)
        val currencySpinner = findViewById<Spinner>(R.id.currency)
        val dateTV = findViewById<TextView>(R.id.date_tv)
        val timeTV = findViewById<TextView>(R.id.time_tv)

        // Set the values for the views
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = getString(R.string.add_transaction_title)

        // Set the spinner for the currency
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

        currencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                if (position >= 0 && position < Currency.values().size) {
                    currency = Currency.values()[position]
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                currency = defaultCurrency
            }
        }

        // Set the spinner for the source
        ArrayAdapter.createFromResource(
                this,
                R.array.source_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            sourceSpinner.adapter = adapter
        }

        sourceSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                if (position >= 0 && position < Source.values().size) {
                    source = Source.values()[position]
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                source = defaultSource
            }
        }

        val addTransactionButton = findViewById<Button>(R.id.add)
        addTransactionButton.setOnClickListener { _ ->
            // TODO: Pass the date
            presenter.addTransactionIfCorrect(destinationED.text.toString(), source,
                    quantity.text.toString(), currency, Date().time)
        }

        val now = Date()
        dateTV.text = simpleDateFormatter.format(now)

        dateTV.setOnClickListener({
            // TODO
        })
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        presenter?.dispose()
        super.onDestroy()
    }
}
