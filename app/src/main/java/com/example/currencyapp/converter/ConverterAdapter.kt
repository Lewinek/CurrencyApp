package com.example.currencyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.converter.CurrencyDisplayable
import com.example.currencyapp.databinding.ItemRateBinding
import java.math.BigDecimal

class ConverterAdapter(
    private val onItemClick: (String) -> Unit,
    private val onValueChange: (BigDecimal) -> Unit
) : androidx.recyclerview.widget.ListAdapter<CurrencyDisplayable, ConverterAdapter.ConverterViewHolder>(
    ConverterDiffCallback
) {
    class ConverterViewHolder(
        private val binding: ItemRateBinding,
        onItemClick: (String) -> Unit,
        onValueChange: (BigDecimal) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var baseCurrency: CurrencyDisplayable? = null

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

        fun bind(currency: CurrencyDisplayable) {
            baseCurrency = currency
            binding.name.text = currency.name
            if (currency.convertedValue == null) {
                binding.value.setText(currency.value.toString())
            } else {
                binding.value.setText(currency.convertedValue.toString())
            }
            binding.value.isEnabled = currency.isBaseCurrency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterViewHolder {
        return ConverterViewHolder(
            ItemRateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick, onValueChange
        )
    }

    override fun onBindViewHolder(holder: ConverterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object ConverterDiffCallback : DiffUtil.ItemCallback<CurrencyDisplayable>() {
    override fun areItemsTheSame(
        oldItem: CurrencyDisplayable,
        newItem: CurrencyDisplayable
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: CurrencyDisplayable,
        newItem: CurrencyDisplayable
    ): Boolean {
        return oldItem == newItem
    }
}
