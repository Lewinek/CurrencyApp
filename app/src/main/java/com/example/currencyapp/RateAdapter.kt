package com.example.currencyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_networking.Currency
import com.example.currencyapp.databinding.ItemRateBinding
import java.math.BigDecimal

class RateAdapter(
    private val onItemClick: (String) -> Unit,
    private val onValueChange: (BigDecimal) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Currency, RateAdapter.RateViewHolder>(
    RatesDiffCallback
) {
    class RateViewHolder(
        private val binding: ItemRateBinding,
        onItemClick: (String) -> Unit,
        onValueChange: (BigDecimal) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var baseCurrency: Currency? = null

        init {
            binding.item.setOnClickListener {
                baseCurrency?.let { onItemClick.invoke(it.name) }
            }
            binding.value.doAfterTextChanged {
                if (baseCurrency?.isBaseCurrency == true) {
                    onValueChange.invoke(it.toString().toBigDecimal())
                }
            }
        }

        fun bind(rate: Currency) {
            baseCurrency = rate
            binding.name.text = rate.name
            if (rate.convertedValue == null){
                binding.value.setText(rate.rate.toString())
            } else {
                binding.value.setText(rate.convertedValue.toString())
            }
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
        holder.bind(getItem(position))
    }

}

object RatesDiffCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}
