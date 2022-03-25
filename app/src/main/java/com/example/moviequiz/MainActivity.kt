package com.example.moviequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import com.example.moviequiz.views.PlaygroundActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var playgroundActivityIntent: Intent? = null

    private var etName: AppCompatEditText? = null
    private var btnStart: Button? = null

    private val SOURCE_NAME: ArrayList<String> = arrayListOf("ผู้เจริญ", "จอห์น", "เฌอ", "ฮิคารุ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        this.playgroundActivityIntent = Intent(this, PlaygroundActivity::class.java)

        this.etName = findViewById<AppCompatEditText>(R.id.et_name)
        this.btnStart = findViewById<Button>(R.id.btn_start)

        this.btnStart?.setOnClickListener(this)

        this.setContext()
    }

    override fun onClick(v: View?) {
        val name = etName!!.text.toString()

        if (name.isEmpty()) {
            this.playgroundActivityIntent?.putExtra("name", this.SOURCE_NAME[Random.nextInt(this.SOURCE_NAME.size)])
        } else {
            this.playgroundActivityIntent?.putExtra("name", name)
        }

        this.startActivity(this.playgroundActivityIntent)
    }

    private fun setContext() {
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }
}