package com.example.rushi_assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rushi_assignment1.databinding.ActivityExpensesBinding

class ExpensesActivity : AppCompatActivity() {

    // ViewBinding object to access layout views
    private lateinit var b: ActivityExpensesBinding

    // Adapter for RecyclerView to manage expense items
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        b = ActivityExpensesBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Initialize RecyclerView with adapter
        adapter = ExpenseAdapter(mutableListOf())
        b.rvExpenses.layoutManager = LinearLayoutManager(this)
        b.rvExpenses.adapter = adapter

        // Get the starting total if passed from MainActivity
        val startTotal = intent.getDoubleExtra("EXTRA_START_TOTAL", 0.0)
        if (startTotal > 0) {
            adapter.add(Expense("Existing Expenses", startTotal))
            updateTotal()
        }

        // Add button click → adds a new expense item to the list
        b.btnAdd.setOnClickListener {
            val name = b.etName.text?.toString()?.trim().orEmpty()
            val amt = b.etAmount.text?.toString()?.toDoubleOrNull() ?: -1.0

            // Validate: must not be blank or negative
            if (name.isBlank() || amt < 0) {
                toast("Enter a name and a non-negative amount.")
                return@setOnClickListener
            }

            // Add the expense to the adapter
            adapter.add(Expense(name, amt))

            // Clear input fields
            b.etName.setText("")
            b.etAmount.setText("")

            // Update total expenses display
            updateTotal()
        }

        // Done button click → send total expenses back to MainActivity
        b.btnDone.setOnClickListener {
            val data = Intent().putExtra("EXTRA_TOTAL_EXPENSES", adapter.total())
            setResult(RESULT_OK, data) // return result to caller
            finish() // close activity
        }
    }

    // Update the total TextView with the current sum of all expenses
    private fun updateTotal() {
        b.tvTotal.text = "Total: $%.2f".format(adapter.total())
    }

    // Convenience function to show a short Toast message
    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}