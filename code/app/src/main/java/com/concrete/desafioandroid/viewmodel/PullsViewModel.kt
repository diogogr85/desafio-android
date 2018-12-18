package com.concrete.desafioandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.concrete.desafioandroid.data.models.LivePullRequest
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.features.base.BaseViewModel
import com.concrete.desafioandroid.features.pulls.PullsInteractor
import io.reactivex.disposables.Disposable


class PullsViewModel(private val interactor: PullsInteractor): BaseViewModel() {

    val pulls: MutableLiveData<List<PullRequest>> by lazy {
        MutableLiveData<List<PullRequest>>()
    }
    val openedIssues = MutableLiveData<Int>()
    val closedIssues = MutableLiveData<Int>()
//    var openedIssues: Int = 0
//    var closedIssues: Int = 0

    lateinit var disposable: Disposable

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getPullsRequests(pullsUrl: String, forceUpdate: Boolean) {
        if (forceUpdate) {
            unsubscribe()

//            view?.showProgress(true)
            disposable = interactor.getPullsList(pullsUrl,
                    { list, openedIssues, closedIssues ->
                        pulls.value = list
                        this.openedIssues.value = openedIssues
                        this.closedIssues.value = closedIssues
//                        view?.showProgress(false)
//                        view?.onGetPullsRequests(list)
//                        view?.updateUi(openedIssues, closedIssues)
                    },
                    {
//                        view?.showProgress(false)
//                        view?.showError(it)
                    })

            compositeDisposable.add(disposable)
        }
    }

}