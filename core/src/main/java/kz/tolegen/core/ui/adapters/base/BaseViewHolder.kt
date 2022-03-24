package kz.tolegen.core.ui.adapters.base

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.extensions.LayoutContainer
import kz.tolegen.core.ext.ContextAware

@ContainerOptions(cache = CacheImplementation.SPARSE_ARRAY)
open class BaseViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView),
    LayoutContainer, ContextAware {
    protected lateinit var item: T

    override val containerView = itemView

    override fun getContext(): Context = itemView.context

    @CallSuper
    open fun bind(item: T) {
        this.item = item
    }

    open fun onViewAttachedToWindow() = Unit

    open fun onViewDetachedFromWindow() = Unit

    open fun onViewRecycled() = Unit
}