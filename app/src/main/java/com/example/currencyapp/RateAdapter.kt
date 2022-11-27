package com.example.currencyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_networking.Rate
import com.example.currencyapp.databinding.ItemRateBinding

class RateAdapter(
    private val onItemClick: (String) -> Unit,
    private val onValueChange: (Double) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Rate, RateAdapter.RateViewHolder>(
    RatesDiffCallback
) {
    class RateViewHolder(
        private val binding: ItemRateBinding,
        onItemClick: (String) -> Unit,
        onValueChange: (Double) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var baseCurrency: Rate? = null

        init {
            binding.item.setOnClickListener {
                baseCurrency?.let { onItemClick.invoke(it.name) }
            }
            binding.value.doAfterTextChanged {
                if (baseCurrency?.isBaseCurrency == true) {
                    onValueChange.invoke(it.toString().toDouble())
                }
            }
        }

        fun bind(rate: Rate, context: Context) {
            baseCurrency = rate
            binding.name.text = rate.name
            if (rate.convertedValue == null){
                binding.value.setText(rate.rate.toString())
            } else {
                binding.value.setText(rate.convertedValue.toString())
            }
//            binding?.value?.setText(rate.convertedValue.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
            ItemRateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick, onValueChange
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
