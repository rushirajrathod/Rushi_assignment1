package com.example.rushi_assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rushi_assignment1.databinding.ActivityExpensesBinding

class ExpensesActivity : AppCompatActivity() {

    private lateinit var b: ActivityExpensesBinding
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityExpensesBinding.inflate(layoutInflater)
        setContentView(b.root)

        adapter = ExpenseAdapter(mutableListOf())
        b.rvExpenses.layoutManager = LinearLayoutManager(this)
        b.rvExpenses.adapter = adapter

        val startTotal = intent.getDoubleExtra("EXTRA_START_TOTAL", 0.0)
        if (startTotal > 0) {
            adapter.add(Expense("Existing Expenses", startTotal))
            updateTotal()
        }

        b.btnAdd.setOnClickListener {
            val name = b.etName.text?.toString()?.trim().orEmpty()
            val amt = b.etAmount.text?.toString()?.toDoubleOrNull() ?: -1.0
            if (name.isBlank() || amt < 0) {
                toast("Enter a name and a non-negative amount.")
                return@setOnClickListener
            }
            adapter.add(Expense(name, amt))
            b.etName.setText("")
            b.etAmount.setText("")
            updateTotal()
        }

        b.btnDone.setOnClickListener {
            val data = Intent().putExtra("EXTRA_TOTAL_EXPENSES", adapter.total())
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private fun updateTotal() {
        b.tvTotal.text = "Total: $%.2f".format(adapter.total())
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}