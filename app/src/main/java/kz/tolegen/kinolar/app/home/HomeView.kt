package kz.tolegen.kinolar.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import kz.tolegen.core.ext.bindCLick
import kz.tolegen.core.ext.visible
import kz.tolegen.core.ui.adapters.base.BaseDelegateAdapter
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.home.viewmodel.HomeViewModel
import kz.tolegen.kinolar.data.models.enums.MovieListType
import kz.tolegen.kinolar.databinding.ViewHomeBinding
import kz.tolegen.kinolar.ui.delegates.MovieUiModel
import kz.tolegen.kinolar.ui.delegates.MoviesDelegate
import kz.tolegen.kinolar.ui.delegates.ShowMoreDelegate
import kz.tolegen.kinolar.ui.delegates.ShowMoreUiModel

class HomeView : Fragment(R.layout.view_home) {

    private val binding by viewBinding(ViewHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val adapterTopRatedMovies = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate {
                viewModel.navigateToMovieDetailScreen(it.id)
            }
        }
        delegate {
            ShowMoreDelegate {
                viewModel.navigateToMovieListScreen(MovieListType.TOP_RATED)
            }
        }
    }
    private val adapterPopularMovies = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate {
                viewModel.navigateToMovieDetailScreen(it.id)
            }
        }
        delegate {
            ShowMoreDelegate {
                viewModel.navigateToMovieListScreen(MovieListType.POPULAR)
            }
        }
    }
    private val adapterUpcomingMovies = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate {
                viewModel.navigateToMovieDetailScreen(it.id)
            }
        }
        delegate {
            ShowMoreDelegate {
                viewModel.navigateToMovieListScreen(MovieListType.UPCOMING)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.apply {

                toolbarTitle.text = getString(R.string.app_name)
            }
        }

        with(viewModel) {

            topRatedMovies.observe(viewLifecycleOwner, {
                adapterTopRatedMovies.items = kotlin.run {
                    return@run mutableListOf<DiffItem>().apply {
                        addAll(it.movies.map { movie ->
                            MovieUiModel(
                                id = movie.id,
                                title = movie.title ?: "",
                                posterUrl = movie.poster_path
                            )
                        })
                        add(ShowMoreUiModel())
                    }
                }
                if (binding.rvTopRatedMovies.adapter == null) {
                    binding.rvTopRatedMovies.swapAdapter(adapterTopRatedMovies, true)
                }
            })

            popularMovies.observe(viewLifecycleOwner, {
                adapterPopularMovies.items = kotlin.run {
                    return@run mutableListOf<DiffItem>().apply {
                        addAll(it.movies.map {
                            MovieUiModel(
                                id = it.id,
                                title = it.title ?: "",
                                posterUrl = it.poster_path
                            )
                        })
                        add(ShowMoreUiModel())
                    }
                }
                if (binding.rvPopularMovies.adapter == null) {
                    binding.rvPopularMovies.swapAdapter(adapterPopularMovies, true)
                }
            })

            upcomingMovies.observe(viewLifecycleOwner, {
                adapterUpcomingMovies.items = kotlin.run {
                    return@run mutableListOf<DiffItem>().apply {
                        addAll(it.movies.map {
                            MovieUiModel(
                                id = it.id,
                                title = it.title ?: "",
                                posterUrl = it.poster_path
                            )
                        })
                        add(ShowMoreUiModel())
                    }
                }
                if (binding.rvUpcomingMovies.adapter == null) {
                    binding.rvUpcomingMovies.swapAdapter(adapterUpcomingMovies, true)
                }
            })
        }
    }
}