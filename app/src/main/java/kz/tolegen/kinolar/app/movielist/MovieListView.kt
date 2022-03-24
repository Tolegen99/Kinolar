package kz.tolegen.kinolar.app.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kz.tolegen.core.ui.adapters.base.BaseDelegateAdapter
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.movielist.viewmodel.MovieListViewModel
import kz.tolegen.kinolar.base.BaseFragment
import kz.tolegen.kinolar.databinding.ViewMovieListBinding
import kz.tolegen.kinolar.ui.delegates.MovieUiModel
import kz.tolegen.kinolar.ui.delegates.MoviesDelegate

class MovieListView : BaseFragment<ViewMovieListBinding>(R.layout.view_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()

    private val adapter = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getTopRatedMovies()

        viewModel.topRatedMovies.observe(viewLifecycleOwner, {
            adapter.items = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(it.movies.map {
                        MovieUiModel(
                            id = it.id.toLong(),
                            title = it.title,
                            description = it.overview
                        )
                    })
                }
            }

            if (binding.rvMovies.adapter == null) {
                binding.rvMovies.swapAdapter(adapter, true)
            }
        })

        return binding {
        }.root
    }


    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewMovieListBinding = ViewMovieListBinding.inflate(inflater, container, false)
}