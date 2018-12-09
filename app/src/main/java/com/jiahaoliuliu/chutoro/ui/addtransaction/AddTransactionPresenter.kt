package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.util.Log
import com.jiahaoliuliu.chutoro.entity.Currency
import com.jiahaoliuliu.chutoro.entity.Source
import com.jiahaoliuliu.chutoro.usecase.AddTransactionUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException

class AddTransactionPresenter(private val addTransactionUseCase: AddTransactionUseCase):
        AddTransactionContract.Presenter {

    companion object {
        private const val TAG = "AddTransactionPresenter"
    }

    private var view: AddTransactionContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun setView(view: AddTransactionContract.View) {
        this.view = view
    }

    override fun addTransactionIfCorrect(
            destination: String, source: Source, quantity: String, currency: Currency, date: Long){
        compositeDisposable.add(addTransactionUseCase.execute(destination, source, quantity, currency, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({resultBoolean ->
                if (resultBoolean) {
                    Log.v(TAG, "Transaction inserted correctly: $destination $source $quantity $currency $date")
                    view?.onInsertionCorrect()
                }
            }, {throwable ->
                if (throwable is IllegalArgumentException) {
                    view?.showInsertionError(throwable)
                } else {
                    Log.e(TAG, "Error adding the transaction ", throwable)
            }
        }))
    }

    override fun dispose() {
        compositeDisposable.clear()
    }
}