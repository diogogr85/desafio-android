package com.concrete.desafioandroid.features.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel: ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    abstract fun unsubscribe()

}