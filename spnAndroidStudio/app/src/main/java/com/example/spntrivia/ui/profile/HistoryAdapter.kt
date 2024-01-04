package com.example.spntrivia.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spntrivia.R
import com.example.spntrivia.gameHistoryDB.QuizResult

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    private var resultList = emptyList<QuizResult>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chosenDifficulty: TextView = itemView.findViewById(R.id.chosen_difficulty)
        val calculatedScore: TextView = itemView.findViewById(R.id.calculated_score)
        val calculatedRank: TextView = itemView.findViewById(R.id.calculated_rank)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = resultList[position]
        val difficultyText = when (currentResult.difficulty) {
            1 -> "Easy"
            2 -> "Medium"
            3 -> "Hard"
            else -> ""
        }
        holder.chosenDifficulty.text = difficultyText
        holder.calculatedScore.text = currentResult.score.toString()
        holder.calculatedRank.text = currentResult.rankTitle
    }

    fun setData(result: List<QuizResult>) {
        this.resultList = result
        notifyDataSetChanged()
    }
}