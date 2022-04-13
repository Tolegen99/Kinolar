package kz.tolegen.kinolar.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.tolegen.core.ui.adapters.base.BaseDelegate
import kz.tolegen.core.ui.adapters.base.BaseViewHolder
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.databinding.ItemSlotGenreBinding

class SlotGenreDelegate : BaseDelegate<SlotGenreUiModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ) =
        item is SlotGenreUiModel


    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            ItemSlotGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemSlotGenreBinding) :
        BaseViewHolder<SlotGenreUiModel>(binding.root) {
        override fun bind(item: SlotGenreUiModel) {
            super.bind(item)

            binding.tvName.text = item.name.capitalize()
        }
    }
}

data class SlotGenreUiModel(
    val id: Long,
    val name: String
) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem) =
        newItem is SlotGenreUiModel && this.id == newItem.id

    override fun areContentsTheSame(newItem: DiffItem) =
        this == newItem

}