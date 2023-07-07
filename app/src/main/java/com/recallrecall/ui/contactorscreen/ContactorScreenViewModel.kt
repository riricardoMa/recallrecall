package com.recallrecall.ui.contactorscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ContactorScreenViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val _stateFlow: MutableStateFlow<ContactorScreenState> =
    MutableStateFlow(ContactorScreenState())

  val stateFlow: StateFlow<ContactorScreenState> = _stateFlow.asStateFlow()

}
