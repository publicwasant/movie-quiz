package com.example.moviequiz.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.moviequiz.MainActivity
import com.example.moviequiz.R

class EndingActivity : AppCompatActivity(), View.OnClickListener {
    private var mainActivityIntent: Intent? = null

    private var tvScore: TextView? = null
    private var tvGrade: TextView? = null
    private var btnReplay: Button? = null

    private var score: String? = null
    private var grade: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_ending)

        this.mainActivityIntent = Intent(this, MainActivity::class.java)

        this.score = intent.getStringExtra("score").toString()
        this.grade = intent.getStringExtra("grade").toString()

        this.tvScore = findViewById<TextView>(R.id.tv_score)
        this.tvGrade = findViewById<TextView>(R.id.tv_grade)
        this.btnReplay = findViewById<Button>(R.id.btn_replay)

        this.btnReplay?.setOnClickListener(this)

        this.setContext()
    }

    override fun onClick(v: View?) {
        this.startActivity(this.mainActivityIntent)
        this.finish()
    }

    private fun setContext() {
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        this.tvScore?.text = tvScore?.text.toString().replace("?", score!!)
        this.tvGrade?.text = tvGrade?.text.toString().replace("?", grade!!)
    }
}