package kz.tolegen.kinolar.ui.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Parcelable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import kotlinx.parcelize.Parcelize
import kz.tolegen.core.ext.appendExtended
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.databinding.TitleValueFieldBinding

class TitleValueView : LinearLayout {

    private val binding = TitleValueFieldBinding.inflate(LayoutInflater.from(context), this)

    private var title: String? = null
    private var value: String? = null


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TitleValueView, 0, 0
        )

        title = a.getString(R.styleable.TitleValueView_title)
        value = a.getString(R.styleable.TitleValueView_value)

        setTitleInfo(title, value)
    }

    fun setTitleValue(title: String?, value: String?) {
        this.title = title
        this.value = value

        setTitleInfo(title, value)
    }

    fun setValue(value: String?) {
        this.value = value

        setTitleInfo(title, value)
    }

    private fun setTitleInfo(title: String?, value: String?) {
        if (title.isNullOrBlank()) {
            binding.tvText.text = ""
            return
        }
        binding.tvText.apply {
            highlightColor = Color.TRANSPARENT
            text = SpannableStringBuilder()
                .apply {
                    appendExtended(
                        title,
                        listOf(
                            TextAppearanceSpan(
                                context,
                                R.style.TextAppearance_StyreneAlcBold_White_16sp
                            )
                        ),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    if (!value.isNullOrBlank()) {
                        append(" ")
                        appendExtended(
                            value,
                            listOf(
                                TextAppearanceSpan(
                                    context,
                                    R.style.TextAppearance_StyreneAlcRegular_White_14sp
                                )
                            ),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
        }
    }

    private fun ViewGroup.saveChildViewStates(): SparseArray<Parcelable> {
        val childViewStates = SparseArray<Parcelable>()
        children.forEach { child -> child.saveHierarchyState(childViewStates) }
        return childViewStates
    }

    public override fun onSaveInstanceState(): Parcelable? {
        val initialState = super.onSaveInstanceState()
        val state = SavedState(
            title = title,
            value = title,
            parcelable = initialState,
            childrenStates = saveChildViewStates()
        )
        return state
    }

    private fun ViewGroup.restoreChildViewStates(childViewStates: SparseArray<Parcelable>) {
        children.forEach { child -> child.restoreHierarchyState(childViewStates) }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        savedState.childrenStates?.let { restoreChildViewStates(it) }
        title = savedState.title
        value = savedState.value
    }

    @Parcelize
    private class SavedState(
        val title: String?,
        val value: String?,
        val parcelable: Parcelable?,
        val childrenStates: SparseArray<Parcelable>?
    ) : BaseSavedState(parcelable)
}