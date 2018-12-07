package com.concrete.desafioandroid.features.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.component_progress_view.*
import org.kodein.di.KodeinAware


abstract class BaseActivity<V: BaseView>: AppCompatActivity(), KodeinAware, BaseView {

    protected abstract val layoutId: Int
    protected abstract val presenter: BasePresenter<V>

    protected var hasSavedInstances = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        hasSavedInstances = savedInstanceState != null

        setPresenter()
        onCreate()
    }

    protected abstract fun setPresenter()

    protected abstract fun onCreate()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    protected fun showMessage(message: String?) {
        if (message != null) {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showProgress(show: Boolean) {
        progressView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

}