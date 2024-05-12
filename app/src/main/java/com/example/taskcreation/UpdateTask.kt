package com.example.taskcreation

import TaskDbSetUp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskcreation.databinding.ActivityUpdateTaskBinding

class UpdateTask : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateTaskBinding
    private lateinit var db:TaskDbSetUp
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= TaskDbSetUp(this)

        taskId = intent.getIntExtra("note_id",-1)
        if (taskId == -1){
            finish()
            return
        }

        val note = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle=binding.updateTitleEditText.text.toString()
            val newContent=binding.updateContentEditText.text.toString()
            val updatedNote = Task(taskId, newTitle, newContent)
            db.updateTask(updatedNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }

        binding.backButton2.setOnClickListener {
            val intent = Intent(this,AllTask::class.java)
            startActivity(intent)
            finish()
        }
    }
}