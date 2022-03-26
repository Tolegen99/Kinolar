package kz.tolegen.kinolar.ui.delegates

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kz.tolegen.core.ui.adapters.base.BaseDelegate
import kz.tolegen.core.ui.adapters.base.BaseViewHolder
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.databinding.ItemMovieBinding
import kotlin.math.log

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

            Glide
                .with(binding.root)
                .asDrawable()
                .transform(RoundedCorners(16))
                .load("https://image.tmdb.org/t/p/original${item.posterUrl}")
                .into(object : DrawableImageViewTarget(binding.ivPoster) {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        super.onResourceReady(resource, transition)
                    }
                })





        }
    }
}

data class MovieUiModel(
    val id: Long,
    val title: String,
    val posterUrl: String
) : DiffItem {
    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is MovieUiModel && this.id == newItem.id


    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        this == newItem
}