package kz.tolegen.kinolar.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : Fragment() {

//    protected var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    private var _binding: Binding? = null

    protected val binding: Binding
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    protected inline fun binding(block: Binding.() -> Unit): Binding {
        return binding.apply(block)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = provideViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
}