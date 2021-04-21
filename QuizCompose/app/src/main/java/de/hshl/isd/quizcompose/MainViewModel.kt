package de.hshl.isd.quizcompose

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    var index = 0

    var showAnswer by mutableStateOf(false)

    var question by mutableStateOf(questions[index].first)

    var answer by mutableStateOf("")

    private fun nextQuestion() {
        showAnswer = false
        index = (index + 1) % questions.count()
        question = questions[index].first
        answer = ""
    }

    fun evaluateAnswer(givenAnswer: Boolean) {
        showAnswer = true
        if (givenAnswer == questions[index].second) {
            answer = "Richtig!"
            correctAnswers++
        } else {
            answer = "Falsch!"
            wrongAnswers++
        }
        GlobalScope.launch {
            delay(1000)
            nextQuestion()
        }
    }

    fun skip() {
        skippedQuestions++
        nextQuestion()
    }

}
