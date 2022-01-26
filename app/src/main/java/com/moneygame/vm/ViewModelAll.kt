package com.moneygame.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneygame.ui.*

class ViewModelAll : ViewModel() {
    //Variables
    private val _question = MutableLiveData<String>()
    val question: LiveData<String> = _question

    private val answers: MutableList<String> = mutableListOf()

    private val _answer1 = MutableLiveData<String>()
    val answer1: LiveData<String> = _answer1
    private val _answer2 = MutableLiveData<String>()
    val answer2: LiveData<String> = _answer2
    private val _answer3 = MutableLiveData<String>()
    val answer3: LiveData<String> = _answer3

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> = _score

    private val numLimitQuestion = 5
    private val scoreIncrease = 10
    private val _currentQCount = MutableLiveData(0)
    val currentQCount: LiveData<Int>
        get() = _currentQCount

    private var usedQuestionList: MutableList<String> = mutableListOf()

    private lateinit var currentQuestion: String

    private val _setTrue = MutableLiveData<Int>()
    val setTrue: LiveData<Int> = _setTrue

    private val _goToScore = MutableLiveData<Boolean>()
    val goToScore: MutableLiveData<Boolean> = _goToScore

    //Funciones
    init {
        answers.clear()
    }

    fun newQuestion() {
        _setTrue.value = 1
        nextQuestion()
        getAnswers()
    }

    fun newQuestion2() {
        _setTrue.value = 2
        increaseScore()
        nextQuestion()
        getAnswers()
    }

    fun newQuestion3() {
        _setTrue.value = 3
        nextQuestion()
        getAnswers()
    }


    private fun nextQuestion(): Boolean {
        return if (_currentQCount.value!! < numLimitQuestion) {
            getNextQuestion()
            true
        } else {
            goToScore()
        }
    }

    private fun goToScore(): Boolean {
        _goToScore.value = true
        return true
    }

    private fun getNextQuestion() {
        currentQuestion = allQuestionsList.random()
        if (usedQuestionList.contains(currentQuestion)) {
            getNextQuestion()
        } else {
            _question.value = currentQuestion
            _currentQCount.value = (_currentQCount.value)?.inc()
            usedQuestionList.add(currentQuestion)
        }
    }

    private fun getAnswers() {
        when (currentQuestion) {
            "Nombre del autor de Padre Rico, Padre Pobre:" -> setAnswerList(respList1)
            "¿Cuál fue el primer negocio de Robert?" -> setAnswerList(respList2)
            "Según Robert los activos y pasivos son:" -> setAnswerList(respList3)
            "El juego que inventó Robert, se llama:" -> setAnswerList(respList4)
            "Conocer la ley porque es más caro no conocerla. Eso lo decía:" -> setAnswerList(respList5)
            "La inteligencia resuelve problemas y produce dinero. El dinero sin inteligencia financiera es:" -> setAnswerList(respList6)
            "¿Que es un activo para los ricos?" -> setAnswerList(respList7)
            "¿Que es un pasivo para los ricos?" -> setAnswerList(respList8)
            "¿Cuál es la causa de la inestabilidad económica de las familias o parejas jóvenes?" -> setAnswerList(respList9)
        }
    }

    private fun setAnswerList(i: List<String>) {
        answers.clear()
        var cont = 0
        repeat(3) {
            answers.add(i[cont])
            cont++
        }
        _answer1.value = answers[0]
        _answer2.value = answers[1]
        _answer3.value = answers[2]
    }

    private fun increaseScore() {
        _score.value = (_score.value)?.plus(scoreIncrease)
    }

    fun reiniciar() {
        _setTrue.value = 0
        _score.value = 0
        _currentQCount.value = 0
        usedQuestionList.clear()
        getNextQuestion()
        getAnswers()
    }
}