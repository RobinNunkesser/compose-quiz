package de.hshl.isd.quiz.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.Interpolator
import de.hshl.isd.quiz.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.question.observe(this, Observer {questionTextView.text = it})
        viewModel.answer.observe(this, Observer {answerTextView.text = it})

        trueButton.setOnClickListener {
            viewModel.evaluateAnswer(true)
            showAnswer()
        }

        falseButton.setOnClickListener {
            viewModel.evaluateAnswer(false)
            showAnswer()
        }

        skipButton.setOnClickListener {
            viewModel.increaseIndex()
        }
    }

    fun showAnswer() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        skipButton.isEnabled = false
        val animationIn = AlphaAnimation(0.0f,1.0f)
        animationIn.duration = 2000
        animationIn.interpolator = AccelerateDecelerateInterpolator()
        answerTextView.startAnimation(animationIn)

        answerTextView.postDelayed(Runnable {
            viewModel.increaseIndex()
            trueButton.isEnabled = true
            falseButton.isEnabled = true
            skipButton.isEnabled = true
        },2500)

    }

}
