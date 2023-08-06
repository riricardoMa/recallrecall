package com.recallrecall.feature_recall.presentation.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.recallrecall.feature_recall.domain.model.Message
import com.recallrecall.feature_recall.domain.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class MessageViewModel @Inject constructor(private val repository: MessageRepository) :
    ViewModel() {

  val names = repository.loadAllName().cachedIn(viewModelScope)

  fun getMessageByName(name: String): Flow<Message> {
    return repository.loadLatestByName(name)
  }
}
