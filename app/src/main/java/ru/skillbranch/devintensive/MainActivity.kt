package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phlogiston.devintensive.R
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {

    lateinit var bender: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bender = Bender()

        if (savedInstanceState != null) {
            val status = savedInstanceState.getString("STATUS")
            val question = savedInstanceState.getString("QUESTION")
            bender.status = Bender.Status.valueOf(status!!)
            bender.question = Bender.Question.valueOf(question!!)
        }

        val (r, g, b) = bender.status.color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        tv_text.text = bender.askQuestion()

        iv_send.setOnClickListener {
            val (phrase, color) = bender.listenAnswer(et_message.text.toString())
            et_message.setText("")
            val (sr, sg, sb) = color
            iv_bender.setColorFilter(Color.rgb(sr, sg, sb), PorterDuff.Mode.MULTIPLY)
            tv_text.text = phrase
        }

        et_message.setOnEditorActionListener { view, actionId, keyEvent ->

            val (phrase, color) = bender.listenAnswer(et_message.text.toString())
            et_message.setText("")
            val (er, eg, eb) = color
            iv_bender.setColorFilter(Color.rgb(er, eg, eb), PorterDuff.Mode.MULTIPLY)
            tv_text.text = phrase

            hideKeyboard()

            return@setOnEditorActionListener true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("STATUS", bender.status.name)
        outState.putString("QUESTION", bender.question.name)
    }
}
