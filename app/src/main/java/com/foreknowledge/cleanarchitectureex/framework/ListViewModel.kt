package com.foreknowledge.cleanarchitectureex.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.foreknowledge.cleanarchitectureex.framework.di.AppModule
import com.foreknowledge.cleanarchitectureex.framework.di.DaggerViewModelComponent
import com.foreknowledge.core.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .build()
            .inject(this)
    }

    val noteList = MutableLiveData<List<Note>>()

    fun getAllNotes() =
        coroutineScope.launch {
            noteList.postValue(useCases.getAllNotes())
        }
}