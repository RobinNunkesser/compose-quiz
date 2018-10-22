package de.hshl.isd.quiz.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val questions = listOf(Pair("Das Videospiel Donkey Kong sollte ursprünglich Popeye als Hauptfigur haben.", true),
    Pair("Die Farbe Orange wurde nach der Frucht benannt.", true),
    Pair("In der griechischen Mythologie ist Hera die Göttin der Ernte.", false),
    Pair("Liechtenstein hat keinen eigenen Flughafen.", true),
    Pair("Die meisten Subarus werden in China hergestellt.", false))

    private val _index = MutableLiveData<Int>()
    val index : LiveData<Int>
    get() = _index
    init {
        _index.value = 0
    }

    val question : LiveData<String> = Transformations.map(index) { index -> questions[index].first}

    private val _answer = MutableLiveData<String>()
    val answer : LiveData<String>
    get() = _answer
    init {
        _answer.value = ""
    }

    fun increaseIndex() {
        _index.value = (_index.value!! + 1) % questions.count()
        _answer.value = ""
    }

    fun evaluateAnswer(answer: Boolean) {
        if (answer == questions[_index.value!!].second) {
            _answer.value = "Richtig!"
        } else {
            _answer.value = "Falsch!"
        }
    }

}
