package com.example.rushi_assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.rushi_assignment1.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private lateinit var expensesLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        expensesLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val total = result.data?.getDoubleExtra("EXTRA_TOTAL_EXPENSES", 0.0) ?: 0.0
                b.etExpenses.setText("%.2f".format(total))
                toast("Expenses updated: $%.2f".format(total))
            }
        }

        b.btnCalcEmi.setOnClickListener { calcEmi() }
        b.btnComputeBudget.setOnClickListener { computeBudget() }

        // Explicit intent: detailed expenses
        b.btnManageExpenses.setOnClickListener {
            val startTotal = b.etExpenses.text?.toString()?.toDoubleOrNull() ?: 0.0
            val i = Intent(this, ExpensesActivity::class.java)
            i.putExtra("EXTRA_START_TOTAL", startTotal)
            expensesLauncher.launch(i)
        }


    }

    // calculating the emi
    private fun calcEmi() {
        val p = num(b.etLoan.text?.toString())
        val annualRate = num(b.etRate.text?.toString())
        val years = num(b.etYears.text?.toString())

        if (p <= 0 || annualRate < 0 || years <= 0) {
            toast("Please enter valid loan, rate, and years.")
            return
        }

        val n = (years * 12).toInt()
        val r = annualRate / 12.0 / 100.0

        val emi = if (r == 0.0) p / n
        else p * r * (1 + r).pow(n) / ((1 + r).pow(n) - 1)

        b.tvEmi.text = "EMI: $%.2f".format(emi)
    }

    // calculating the budget
    private fun computeBudget() {
        val income = num(b.etIncome.text?.toString())
        val expenses = num(b.etExpenses.text?.toString())
        val emi = b.tvEmi.text.toString().removePrefix("EMI: $").toDoubleOrNull() ?: 0.0

        if (income < 0 || expenses < 0) {
            toast("Income/Expenses must be non-negative.")
            return
        }

        val savings = income - (emi + expenses)
        val label = if (savings >= 0) "Savings" else "Deficit"
        b.tvBudget.text = "Result: $%.2f (%s)".format(abs(savings), label)
    }



    private fun num(s: String?): Double = s?.trim()?.toDoubleOrNull() ?: 0.0
    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}