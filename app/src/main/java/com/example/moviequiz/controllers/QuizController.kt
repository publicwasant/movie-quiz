package com.example.moviequiz.controllers

import com.example.moviequiz.models.QuizModel
import kotlin.math.roundToInt
import kotlin.random.Random

class QuizController {
    private var questionPack: ArrayList<QuizModel>? = null
    private var current: Int = -1
    private var selected: Int = -1

    private var score: Int = 0

    constructor(questionPack: ArrayList<QuizModel>) {
        this.questionPack = toShuffle(questionPack)
        this.current = 0
    }

    private fun toShuffle(pack: ArrayList<QuizModel>): ArrayList<QuizModel> {
        return _toFetchPack(pack, ArrayList<QuizModel>(), ArrayList<Int>())
    }

    private fun _toFetchPack(pack: ArrayList<QuizModel>, r: ArrayList<QuizModel>, ind: ArrayList<Int>): ArrayList<QuizModel> {
        if (r.size == pack.size)
            return r

        val rand = Random.nextInt(pack.size)

        if (rand in ind)
            return _toFetchPack(pack, r, ind)

        ind.add(rand)
        r.add(pack[rand])

        return _toFetchPack(pack, r, ind)
    }

    fun get(): QuizModel {
        return this.questionPack!![this.current]
    }

    fun getCurrent(): Int {
        return this.current
    }

    fun getSize(): Int {
        return this.questionPack!!.size
    }

    fun getCorrectChoice(): Int {
        return this.questionPack!![this.current].choiceCorrect
    }

    fun getSelected(): Int {
        return this.selected
    }

    fun getScore(): Int {
        return this.score
    }

    fun getGrade(): String {
        val grade = arrayOf("ธรรมดา", "พอใช้", "รู้เยอะดี", "เก่งมาก!")

        val c: Double = this.score.toDouble()
        val s: Double = this.questionPack!!.size.toDouble()
        val g: Double = grade.size.toDouble()
        val ind: Int = (((c/s)*10.0)/g).roundToInt()

        return grade[ind]
    }

    fun setSelected(choice: Int) {
        this.selected = choice
    }

    fun setScoreUp() {
        this.score += 1
    }

    fun isFinish(): Boolean {
        return this.current >= this.questionPack!!.size
    }

    fun isAnswerCorrect(): Boolean {
        return this.selected == this.questionPack!![this.current].choiceCorrect
    }

    fun next() {
        this.current += 1
        this.selected = -1
    }
}