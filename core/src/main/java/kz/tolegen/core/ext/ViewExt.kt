package kz.tolegen.core.ext

import android.os.SystemClock
import android.view.View

fun View.invisible() = run { visibility = View.INVISIBLE }

fun View.visible() = run { visibility = View.VISIBLE }
fun View.gone() = run { visibility = View.GONE }

fun View.toggleVisibility(visibilty: Boolean, visibiltyWhenFalse: Int = View.GONE) =
    run { visibility = if (visibilty) View.VISIBLE else visibiltyWhenFalse }


infix fun View.bindCLick(action: (v: View) -> Unit) {
    this.clickWithDebounce(action = action)
}

fun View.clickWithDebounce(debounceTime: Long = 1000L, action: (v: View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
