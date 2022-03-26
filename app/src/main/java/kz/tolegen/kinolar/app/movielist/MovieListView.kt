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

    private val adapterTopRatedMovies = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate()
        }
    }
    private val adapterPopularMovies = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate()
        }
    }
    private val adapterUpcomingMovies = BaseDelegateAdapter.create {
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

        binding.toolbar.toolbarTitle.text = getString(R.string.app_name)

        viewModel.topRatedMovies.observe(viewLifecycleOwner, {
            adapterTopRatedMovies.items = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(it.movies.map {
                        MovieUiModel(
                            id = it.id.toLong(),
                            title = it.title,
                            posterUrl = it.poster_path
                        )
                    })
                }
            }
            if (binding.rvTopRatedMovies.adapter == null) {
                binding.rvTopRatedMovies.swapAdapter(adapterTopRatedMovies, true)
            }
        })

        viewModel.popularMovies.observe(viewLifecycleOwner, {
            adapterPopularMovies.items = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(it.movies.map {
                        MovieUiModel(
                            id = it.id.toLong(),
                            title = it.title,
                            posterUrl = it.poster_path
                        )
                    })
                }
            }
            if (binding.rvPopularMovies.adapter == null) {
                binding.rvPopularMovies.swapAdapter(adapterPopularMovies, true)
            }
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, {
            adapterUpcomingMovies.items = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    addAll(it.movies.map {
                        MovieUiModel(
                            id = it.id.toLong(),
                            title = it.title,
                            posterUrl = it.poster_path
                        )
                    })
                }
            }
            if (binding.rvUpcomingMovies.adapter == null) {
                binding.rvUpcomingMovies.swapAdapter(adapterUpcomingMovies, true)
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