package kz.tolegen.kinolar.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.tolegen.core.ui.adapters.base.BaseDelegate
import kz.tolegen.core.ui.adapters.base.BaseViewHolder
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.databinding.ItemLoadMoreVerticalFooterBinding

class LoadMoreVerticalFooterDelegate : BaseDelegate<LoadMoreVerticalFooterUiModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean =
        item is LoadMoreVerticalFooterUiModel

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = ItemLoadMoreVerticalFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemLoadMoreVerticalFooterBinding) :
        BaseViewHolder<LoadMoreVerticalFooterUiModel>(binding.root)


}

open class LoadMoreVerticalFooterUiModel : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean = true

    override fun areContentsTheSame(newItem: DiffItem): Boolean = true
}