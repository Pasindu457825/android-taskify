package com.example.taskcreation

import TaskDbSetUp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskcreation.databinding.ActivityAddTaskBinding

class AddTask : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TaskDbSetUp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDbSetUp(this)


        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            if (title.isEmpty()) {
                binding.titleEditText.error = "Title is empty"
                return@setOnClickListener
            }

            if (content.isEmpty()) {
                binding.contentEditText.error = "content is empty"
                return@setOnClickListener
            }

                val task = Task(0, title, content) // Do not pass id here
                db.insertTask(task)
                finish()
                Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()

        }


        binding.backButton1.setOnClickListener {
            val intent = Intent(this,AllTask::class.java)
            startActivity(intent)
            finish()
        }


    }
}