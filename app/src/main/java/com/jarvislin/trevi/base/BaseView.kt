package com.jarvislin.trevi.base

import io.reactivex.disposables.Disposable

interface BaseView {
    fun showToast(text:String)
    fun addDisposable(disposable: Disposable)
}