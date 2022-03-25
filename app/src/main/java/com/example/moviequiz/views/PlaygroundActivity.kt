package com.example.moviequiz.views

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.example.moviequiz.R
import com.example.moviequiz.controllers.QuizController
import com.example.moviequiz.data
import com.example.moviequiz.models.QuizModel

class PlaygroundActivity : AppCompatActivity(), View.OnClickListener {
    private var endingActivityIntent: Intent? = null

    private var tvTitle: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvChoice1: TextView? = null
    private var tvChoice2: TextView? = null
    private var tvChoice3: TextView? = null
    private var tvChoice4: TextView? = null
    private var tvProgress: TextView? = null
    private var pbProgress: ProgressBar? = null
    private var btnSubmit: Button? = null

    private var quizController: QuizController? = null

    private var name: String? = null
    private var choices: ArrayList<TextView?>? = null
    private var isSubmit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_playground)

        this.endingActivityIntent = Intent(this, EndingActivity::class.java)

        this.tvTitle = findViewById<TextView>(R.id.tv_title)
        this.tvQuestion = findViewById<TextView>(R.id.tv_question)
        this.ivImage = findViewById<ImageView>(R.id.iv_image)
        this.tvChoice1 = findViewById<TextView>(R.id.tv_choice1)
        this.tvChoice2 = findViewById<TextView>(R.id.tv_choice2)
        this.tvChoice3 = findViewById<TextView>(R.id.tv_choice3)
        this.tvChoice4 = findViewById<TextView>(R.id.tv_choice4)
        this.tvProgress = findViewById<TextView>(R.id.tv_progress)
        this.pbProgress = findViewById<ProgressBar>(R.id.pb_progress)
        this.btnSubmit = findViewById<Button>(R.id.btn_submit)

        this.name = intent.getStringExtra("name")
        this.choices = arrayListOf<TextView?>(tvChoice1, tvChoice2, tvChoice3, tvChoice4)

        this.quizController = QuizController(data.list)

        this.tvChoice1?.setOnClickListener(this)
        this.tvChoice2?.setOnClickListener(this)
        this.tvChoice3?.setOnClickListener(this)
        this.tvChoice4?.setOnClickListener(this)

        this.btnSubmit?.setOnClickListener {
            when {
                quizController!!.isFinish() -> {
                    this.onEnding()
                }
                this.isSubmit -> {
                    this.onNext()
                }
                else -> {
                    this.onSubmit()
                }
            }
        }

        this.setContext()
        this.setChoicesDefault()
    }

    override fun onClick(v: View?) {
        val choice: TextView = (v as TextView)
        val ind: Int = this.choices!!.indexOf(choice)

        if (ind != -1) {
            this.quizController?.setSelected(ind)
            this.setChoiceSelected(choice)
        }
    }

    private fun onSubmit() {
        this.quizController?.let {
            val selected = it.getSelected()
            val corrected = it.getCorrectChoice()

            if (selected != -1) {
                this.setChoiceWrong(choices!![selected])
                this.setChoiceCorrect(choices!![corrected])
                this.setChoicesEnable(false)

                if (it.isAnswerCorrect())
                    it.setScoreUp()

                it.next()

                this.btnSubmit?.text = "ข้อต่อไป"
                this.isSubmit = true
            }
        }
    }


    private fun onNext() {
        this.setContext()
        this.setChoicesDefault()
        this.setChoicesEnable(true)

        this.btnSubmit?.text = "ส่งคำตอบ"
        this.isSubmit = false
    }

    private fun onEnding() {
        this.quizController?.let {
            this.endingActivityIntent?.putExtra("score", "${it.getScore()}/${it.getSize()}")
            this.endingActivityIntent?.putExtra("grade", it.getGrade())
            this.startActivity(this.endingActivityIntent)
            this.finish()
        }
    }

    private fun setContext() {
        if (this.window.decorView.systemUiVisibility != View.SYSTEM_UI_FLAG_FULLSCREEN)
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        this.quizController?.let {
            val q: QuizModel = it.get()

            for (i in 0 until q.choices.size)
                this.choices!![i]?.text = q.choices[i]

            this.tvTitle?.text = this.tvTitle?.text.toString().replace("?", this.name!!)
            this.tvQuestion?.text = "${it.getCurrent() + 1}. ${q.question}"
            this.ivImage?.setImageResource(q.image)

            this.tvProgress?.text = "${it.getCurrent() + 1}/${it.getSize()}"
            this.pbProgress?.progress = it.getCurrent() + 1
            this.pbProgress?.max = it.getSize()
        }
    }

    private fun setChoicesDefault() {
        for (c in this.choices!!) {
            c?.setTextColor(R.color.primary_font_soft.toInt())
            c?.typeface = Typeface.DEFAULT
            c?.background = ContextCompat.getDrawable(this, R.drawable.shape_option_default)
        }
    }

    private fun setChoiceSelected(v: TextView?) {
        setChoicesDefault()

        v?.setTextColor(R.color.primary_font.toInt())
        v?.setTypeface(v.typeface, Typeface.BOLD)
        v?.background = ContextCompat.getDrawable(this, R.drawable.shape_option_selected)
    }

    private fun setChoiceCorrect(v: TextView?) {
        v?.background = ContextCompat.getDrawable(this, R.drawable.shape_option_correct)
    }

    private fun setChoiceWrong(v: TextView?) {
        v?.background = ContextCompat.getDrawable(this, R.drawable.shape_option_wrong)
    }

    private fun setChoicesEnable(e: Boolean) {
        for (c in this.choices!!) {
            c?.isEnabled = e
        }
    }
}