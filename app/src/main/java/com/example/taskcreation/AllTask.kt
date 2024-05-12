package com.example.taskcreation

import TaskDbSetUp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskcreation.databinding.ActivityAllTaskBinding

class AllTask : AppCompatActivity() {

    private lateinit var binding: ActivityAllTaskBinding
    private  lateinit var db: TaskDbSetUp
    private lateinit var taskAdp: TaskAdp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDbSetUp(this)
        taskAdp = TaskAdp(db.getAllTasks(), this)

        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter = taskAdp

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

        binding.backButton3.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        taskAdp.refreshData(db.getAllTasks())
    }
}