package com.recallrecall.feature_recall.presentation.conversation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.recallrecall.feature_recall.domain.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MessageRepository,
) : ViewModel() {
  val messagenerName: String = savedStateHandle.get<String>("name") ?: "default"

  val messages
    get() = repository.loadByName(messagenerName).cachedIn(viewModelScope)

}
