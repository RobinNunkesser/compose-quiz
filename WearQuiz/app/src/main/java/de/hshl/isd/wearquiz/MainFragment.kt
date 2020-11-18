package de.hshl.isd.wearquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainFragment : Fragment(), NavigationDrawerFragment {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val navDrawerText: CharSequence
        get() = "Quiz"
    override val navDrawerDrawable: Int
        get() = R.drawable.ic_baseline_quickreply_24

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val questionTextView = requireActivity().findViewById<TextView>(R.id.questionTextView)
        val falseButton = requireActivity().findViewById<ImageButton>(R.id.falseButton)
        val trueButton = requireActivity().findViewById<ImageButton>(R.id.trueButton)
        val skipButton = requireActivity().findViewById<ImageButton>(R.id.skipButton)

        trueButton.setOnClickListener {
            viewModel.evaluateAnswer(true)
            viewModel.increaseIndex()
        }

        falseButton.setOnClickListener {
            viewModel.evaluateAnswer(false)
            viewModel.increaseIndex()
        }

        skipButton.setOnClickListener {
            viewModel.increaseIndex()
            viewModel.skippedQuestions++
        }

        viewModel.question.observe(viewLifecycleOwner, Observer { questionTextView.text = it })
        viewModel.answer.observe(viewLifecycleOwner, Observer {
            it?.let { answer ->
                Toast.makeText(
                    requireContext(),
                    answer, Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}