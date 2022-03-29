package kz.tolegen.kinolar.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.tolegen.core.ext.bindCLick
import kz.tolegen.core.ui.adapters.base.BaseDelegate
import kz.tolegen.core.ui.adapters.base.BaseViewHolder
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.databinding.ItemShowMoreBinding

class ShowMoreDelegate(private val itemClick: ((ShowMoreUiModel) -> Unit)?) :
    BaseDelegate<ShowMoreUiModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean =
        item is ShowMoreUiModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ShowMoreUiModel> {
        val binding = ItemShowMoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemShowMoreBinding) :
        BaseViewHolder<ShowMoreUiModel>(binding.root) {
        init {
            binding.root bindCLick { itemClick?.invoke(item) }
        }
    }
}

open class ShowMoreUiModel : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean = true

    override fun areContentsTheSame(newItem: DiffItem): Boolean = true
}