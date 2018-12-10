package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
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
        private const val TAG = "AddTransactionActivity"
        private const val DATE_FORMAT_DATE = "dd MMMM yyyy"
        private const val DATE_FORMAT_HOUR = "HH:mm"
    }

    @Inject
    lateinit var presenter: AddTransactionContract.Presenter

    // Views
    private lateinit var destinationED: EditText
    private lateinit var sourceSpinner: Spinner
    private lateinit var quantity: EditText
    private lateinit var currencySpinner: Spinner
    private lateinit var dateTV: TextView
    private lateinit var hourTV: TextView
    private lateinit var addTransactionButton: Button

    // Currency
    private val defaultCurrency = Currency.AED
    private var currency = defaultCurrency

    // Source
    private val defaultSource = Source.ADCB
    private var source = defaultSource

    // Date & hour
    private val simpleDateFormatterDate = SimpleDateFormat(DATE_FORMAT_DATE)
    private val simpleDateFormatterHour = SimpleDateFormat(DATE_FORMAT_HOUR)
    private var time = Date()
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        setSupportActionBar(toolbar)

        MainApplication.getMainComponent().inject(this)

        presenter.setView(this)

        // Link the views
        linkViews()

        setupViews()
    }

    private fun setupViews() {
        setupActionBar()
        setupCurrency()
        setupSource()
        setupDate()
        setupHour()
        setupAddButton()
    }

    private fun linkViews() {
        destinationED = findViewById(R.id.destination)
        sourceSpinner = findViewById(R.id.source)
        quantity = findViewById(R.id.quantity)
        currencySpinner = findViewById(R.id.currency)
        dateTV = findViewById(R.id.date_tv)
        hourTV = findViewById(R.id.hour_tv)
        addTransactionButton = findViewById(R.id.add)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = getString(R.string.add_transaction_title)
    }

    private fun setupCurrency() {
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
    }

    private fun setupSource() {
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
    }

    private fun setupDate() {
        dateTV.text = simpleDateFormatterDate.format(time)
        dateTV.setOnClickListener { _ ->
            if (datePickerDialog == null) {
                datePickerDialog = createDatePickerDialog()
            }

            datePickerDialog?.show()
        }
    }

    private fun createDatePickerDialog(): DatePickerDialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
        }, year, month, day)
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

    private fun setupHour() {
        hourTV.text = simpleDateFormatterHour.format(time)
    }

    private fun setupAddButton() {
        addTransactionButton.setOnClickListener { _ ->
            // TODO: Pass the date
            presenter.addTransactionIfCorrect(destinationED.text.toString(), source,
                    quantity.text.toString(), currency, Date().time)
        }
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
