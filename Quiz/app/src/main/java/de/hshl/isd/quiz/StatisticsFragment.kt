package de.hshl.isd.quiz

import android.arch.lifecycle.ViewModelProviders
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

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        questionsTextView.text = viewModel.answeredQuestions.toString()
        correctQuestionsTextView.text = viewModel.correctAnswers.toString()
        wrongQuestionsTextView.text = viewModel.wrongAnswers.toString()
        skippedQuestionsTextView.text = viewModel.skippedQuestions.toString()
    }

}
