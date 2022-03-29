package kz.tolegen.core.ext

import android.os.SystemClock
import android.view.View


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
