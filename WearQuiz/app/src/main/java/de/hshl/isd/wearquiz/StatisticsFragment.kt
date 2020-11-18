package de.hshl.isd.wearquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment(), NavigationDrawerFragment {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        requireActivity().findViewById<TextView>(R.id.questionsTextView).text =
            viewModel.answeredQuestions.toString()
        requireActivity().findViewById<TextView>(R.id.correctQuestionsTextView).text =
            viewModel.correctAnswers.toString()
        requireActivity().findViewById<TextView>(R.id.wrongQuestionsTextView).text =
            viewModel.wrongAnswers.toString()
        requireActivity().findViewById<TextView>(R.id.skippedQuestionsTextView).text =
            viewModel.skippedQuestions.toString()
    }


    override val navDrawerText: CharSequence
        get() = "Statistics"
    override val navDrawerDrawable: Int
        get() = R.drawable.ic_baseline_bar_chart_24
}