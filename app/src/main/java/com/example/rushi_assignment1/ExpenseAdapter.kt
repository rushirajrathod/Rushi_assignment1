package com.example.rushi_assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(private val items: MutableList<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.tvRowName)
        val amount: TextView = v.findViewById(R.id.tvRowAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_expense, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        holder.name.text = e.name
        holder.amount.text = "$%.2f".format(e.amount)
    }

    override fun getItemCount(): Int = items.size

    fun add(expense: Expense) {
        items.add(expense)
        notifyItemInserted(items.size - 1)
    }

    fun total(): Double = items.sumOf { it.amount }
}