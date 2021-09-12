package com.example.androidroomview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.androidroomview.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var binding: ActivityNewWordBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // editWordView = findViewById(R.id.edit_word)
        editWordView = binding.editWord

        // val button = findViewById<Button>(R.id.button_save)
        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
