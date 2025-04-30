package com.motion.hydropome.ui.personalization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motion.hydropome.data.repository.UserRepository
import com.motion.hydropome.ui.personalization.model.PersonalizationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalizationUiState())
    val uiState: StateFlow<PersonalizationUiState> = _uiState

    fun incrementPage() {
        _uiState.update {
            it.copy(page = it.page + 1)
        }
    }

    fun decrementPage() {
        _uiState.update {
            it.copy(page = it.page - 1)
        }
    }

    fun changeSelectedAnswers(questionId: String, selectedAnswers: List<Boolean>) {
        _uiState.update {
            val newSelectedAnswers = it.selectedAnswers.mapValues { (_, innerMap) ->
                innerMap.toMutableMap()
            }.toMutableMap()

            newSelectedAnswers[it.page]?.set(questionId, selectedAnswers)

            it.copy(selectedAnswers = newSelectedAnswers)
        }
    }

    fun savePreferences(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val preferences: MutableMap<String, MutableList<Int>> = mutableMapOf()

            for ((_, innerMap) in uiState.value.selectedAnswers) {
                for ((questionId, selectedAnswers) in innerMap) {
                    selectedAnswers.forEachIndexed { index, isSelected ->
                        val list = preferences.getOrDefault(questionId, mutableListOf())
                        if (isSelected) {
                            list.add(index)
                            preferences[questionId] = list
                        }
                    }
                }
            }

            userRepository.savePreferences(preferences).onSuccess {
                onSuccess()
            }

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}