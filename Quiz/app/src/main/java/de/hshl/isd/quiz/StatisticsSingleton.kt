package de.hshl.isd.quiz

object StatisticsSingleton  {
    var correctAnswers = 0
    var wrongAnswers = 0
    var skippedQuestions = 0
    val answeredQuestions : Int
        get() = correctAnswers + wrongAnswers + skippedQuestions
}