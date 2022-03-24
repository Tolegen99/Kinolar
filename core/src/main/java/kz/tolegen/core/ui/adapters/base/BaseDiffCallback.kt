package kz.tolegen.core.ui.adapters.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffCallback : DiffUtil.ItemCallback<DiffItem>() {
    override fun areItemsTheSame(oldItem: DiffItem, newItem: DiffItem): Boolean =
        oldItem.areContentsTheSame(newItem)


    override fun areContentsTheSame(oldItem: DiffItem, newItem: DiffItem): Boolean =
        oldItem.areContentsTheSame(newItem)

}