package kz.tolegen.kinolar.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.tolegen.core.ui.adapters.base.BaseDelegate
import kz.tolegen.core.ui.adapters.base.BaseViewHolder
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.databinding.ItemMovieBinding

class MoviesDelegate : BaseDelegate<MovieUiModel>() {
    override fun isForViewType(
        item: DiffItem,
        items: MutableList<DiffItem>,
        position: Int
    ): Boolean =
        item is MovieUiModel

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<MovieUiModel> {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        BaseViewHolder<MovieUiModel>(binding.root) {
        override fun bind(item: MovieUiModel) {
            super.bind(item)

            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
        }
    }
}

data class MovieUiModel(
    val id: Long,
    val title: String,
    val description: String
) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is MovieUiModel && this.id == newItem.id


    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        this == newItem
}