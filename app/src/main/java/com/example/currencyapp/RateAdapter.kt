package com.example.currencyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_networking.Rate
import com.example.currencyapp.databinding.ItemRateBinding

class RateAdapter : androidx.recyclerview.widget.ListAdapter<Rate, RateAdapter.RateViewHolder>(
    RatesDiffCallback
) {
    class RateViewHolder(
        private val binding: ItemRateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate, context: Context) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
            ItemRateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }

}

object RatesDiffCallback : DiffUtil.ItemCallback<Rate>() {
    override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem == newItem
    }
}
