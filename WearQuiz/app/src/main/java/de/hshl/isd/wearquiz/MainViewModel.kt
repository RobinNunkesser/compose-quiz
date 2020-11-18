package de.hshl.isd.wearquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val questions = listOf(
        Pair("Das Videospiel Donkey Kong sollte ursprünglich Popeye als Hauptfigur haben.", true),
        Pair("Die Farbe Orange wurde nach der Frucht benannt.", true),
        Pair("In der griechischen Mythologie ist Hera die Göttin der Ernte.", false),
        Pair("Liechtenstein hat keinen eigenen Flughafen.", true),
        Pair("Die meisten Subarus werden in China hergestellt.", false)
    )

    var correctAnswers = 0
    var wrongAnswers = 0
    var skippedQuestions = 0
    val answeredQuestions: Int
        get() = correctAnswers + wrongAnswers + skippedQuestions

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int>
        get() = _index

    init {
        _index.value = 0
    }

    val question: LiveData<String> =
        Transformations.map(index) { index -> questions[index].first }

    private val _answer = MutableLiveData<Int?>()
    val answer: LiveData<Int?>
        get() = _answer

    fun increaseIndex() {
        _index.value = (_index.value!! + 1) % questions.count()
        _answer.value = null
    }

    fun evaluateAnswer(answer: Boolean) {
        if (answer == questions[_index.value!!].second) {
            _answer.value = R.string.label_correct
            correctAnswers++
        } else {
            _answer.value = R.string.label_wrong
            wrongAnswers++
        }
    }

}