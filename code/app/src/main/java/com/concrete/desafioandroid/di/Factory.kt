package com.concrete.desafioandroid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.concrete.desafioandroid.features.pulls.PullsInteractor
import com.concrete.desafioandroid.viewmodel.PullsViewModel
import org.kodein.di.DKodein
import org.kodein.di.generic.instanceOrNull

class PullsViewModelFactory(private val interactor: PullsInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PullsViewModel::class.java)) {
            PullsViewModel(this.interactor) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }


//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return injector.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T?
//                ?: modelClass.newInstance()
//    }
}

//class PullsViewModelFactory(private val injector: DKodein): ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return injector.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T?
//                ?: modelClass.newInstance()
//    }
//}