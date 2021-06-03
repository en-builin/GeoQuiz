package com.bnr.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var answers = IntArray(6)

    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionIsAnswered: Boolean
        get() = (answers[currentIndex] == 0)

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex--
        if (currentIndex < 0) currentIndex = questionBank.size - 1
    }

    fun setAnswer(isCorrect: Boolean) {
        answers[currentIndex] = if (isCorrect) 1 else -1
    }

    fun testFinished(): Boolean {
        var finished = true
        var rightAnswers = 0
        answers.forEach {
            if (it == 0) finished = false
            if (it == 1) rightAnswers++
        }
        return finished
    }

    fun rightAnswersCount(): Int {
        var rightAnswers = 0
        answers.forEach {
            if (it == 1) rightAnswers++
        }
        return rightAnswers
    }
}