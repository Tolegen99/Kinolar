package kz.tolegen.core.ext

import android.text.SpannableStringBuilder

fun SpannableStringBuilder.appendExtended(
    text: CharSequence,
    what: List<Any>,
    flags: Int
): SpannableStringBuilder {
    val start = length
    append(text)
    what.forEach { setSpan(it, start, length, flags) }
    return this
}