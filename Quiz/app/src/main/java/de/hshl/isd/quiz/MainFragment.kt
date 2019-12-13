package de.hshl.isd.quiz

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
                ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        viewModel.question.observe(this, Observer {questionTextView.text = it})
        viewModel.answer.observe(this, Observer {
            it?.let { answerTextView.setText(it) } ?: answerTextView.setText("")
        })

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
            viewModel.skippedQuestions++
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main, menu)
    }

    private fun showAnswer() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        skipButton.isEnabled = false

        val animationIn = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                    // Nothing to do
                }

                override fun onAnimationEnd(p0: Animation?) {
                    viewModel.increaseIndex()
                    trueButton.isEnabled = true
                    falseButton.isEnabled = true
                    skipButton.isEnabled = true
                }

                override fun onAnimationStart(p0: Animation?) {
                    // Nothing to do
                }
            }

            )
        }
        answerTextView.startAnimation(animationIn)


    }


}
