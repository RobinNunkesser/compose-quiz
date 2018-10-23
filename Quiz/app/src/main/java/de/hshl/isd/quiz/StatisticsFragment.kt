package de.hshl.isd.quiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.statistics_fragment.*


class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionsTextView.text = StatisticsSingleton.answeredQuestions.toString()
        correctQuestionsTextView.text = StatisticsSingleton.correctAnswers.toString()
        wrongQuestionsTextView.text = StatisticsSingleton.wrongAnswers.toString()
        skippedQuestionsTextView.text = StatisticsSingleton.skippedQuestions.toString()
    }

}
