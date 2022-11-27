package com.example.currencyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_networking.Rate
import com.example.currencyapp.databinding.ItemRateBinding

class RateAdapter(
    private val onItemClick: (String) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Rate, RateAdapter.RateViewHolder>(
    RatesDiffCallback
) {
    class RateViewHolder(
        private val binding: ItemRateBinding,
        onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var baseCurrency: Rate? = null

        init {
            binding.item.setOnClickListener {
                baseCurrency?.let { onItemClick.invoke(it.name) }
            }
        }

        fun bind(rate: Rate, context: Context) {
            baseCurrency = rate
            binding?.name?.text = rate.name
            binding?.value?.setText(rate.rate.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
            ItemRateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
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
