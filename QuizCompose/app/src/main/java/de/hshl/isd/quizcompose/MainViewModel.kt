package de.hshl.isd.quizcompose

import androidx.compose.Model

@Model
class MainViewModel {

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

    var showAnswer = false

    val question: String
        get() = questions[index].first

    var answer: String = ""

    fun increaseIndex() {
        index = (index + 1) % questions.count()
        answer = ""
    }

    fun evaluateAnswer(givenAnswer: Boolean) {
        if (givenAnswer == questions[index].second) {
            answer = "Richtig!"
            correctAnswers++
        } else {
            answer = "Falsch!"
            wrongAnswers++
        }
    }

}
