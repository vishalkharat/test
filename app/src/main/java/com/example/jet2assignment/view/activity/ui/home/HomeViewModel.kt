package com.example.jet2assignment.view.activity.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.jet2assignment.data.repository.ItemRepository

class HomeViewModel @ViewModelInject constructor(
	repository: ItemRepository
	) : ViewModel() {
	val flowRemoteAndDb = repository.getRemoteAndLocalFlow()
}