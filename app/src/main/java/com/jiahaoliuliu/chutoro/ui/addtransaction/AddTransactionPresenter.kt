package com.jiahaoliuliu.chutoro.ui.addtransaction

import com.jiahaoliuliu.chutoro.entity.Currency
import com.jiahaoliuliu.chutoro.entity.Source
import com.jiahaoliuliu.chutoro.usecase.AddTransactionUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.IllegalArgumentException

class AddTransactionPresenter(private val addTransactionUseCase: AddTransactionUseCase):
        AddTransactionContract.Presenter {

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
                    Timber.v( "Transaction inserted correctly: $destination $source $quantity $currency $date")
                    view?.onInsertionCorrect()
                }
            }, {throwable ->
                if (throwable is IllegalArgumentException) {
                    view?.showInsertionError(throwable)
                } else {
                    Timber.e(throwable, "Error adding the transaction ")
            }
        }))
    }

    override fun dispose() {
        compositeDisposable.clear()
    }
}