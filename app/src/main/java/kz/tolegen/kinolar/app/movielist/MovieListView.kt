package kz.tolegen.kinolar.app.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kz.tolegen.core.ui.adapters.base.BaseDelegateAdapter
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.core.ui.views.EndlessRecyclerViewScrollListener
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.movielist.viewmodel.MovieListViewModel
import kz.tolegen.kinolar.data.models.enums.MovieListType
import kz.tolegen.kinolar.databinding.ViewMovieListBinding
import kz.tolegen.kinolar.ui.delegates.LoadMoreVerticalFooterDelegate
import kz.tolegen.kinolar.ui.delegates.LoadMoreVerticalFooterUiModel
import kz.tolegen.kinolar.ui.delegates.MovieUiModel
import kz.tolegen.kinolar.ui.delegates.MoviesDelegate

class MovieListView : Fragment(R.layout.view_movie_list) {

    private val binding by viewBinding(ViewMovieListBinding::bind)
    private val viewModel: MovieListViewModel by viewModels()

    private val movieListType: MovieListType
        get() = arguments!!.getSerializable(EXTRA_MOVIE_LIST_TYPE) as MovieListType

    private val adapter = BaseDelegateAdapter.create {
        delegate {
            MoviesDelegate {
            }
        }
        delegate {
            LoadMoreVerticalFooterDelegate()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            getMovies(movieListType)

            movies.observe(viewLifecycleOwner, {
                adapter.items = kotlin.run {
                    return@run mutableListOf<DiffItem>().apply {
                        if (it != null) {
                            addAll(it.map {
                                MovieUiModel(
                                    id = it.id,
                                    title = it.title,
                                    posterUrl = it.poster_path
                                )
                            })
                        }
                        if (viewModel.isNextPageLoading)
                            add(
                                LoadMoreVerticalFooterUiModel()
                            )
                    }
                }
                if (binding.recycler.adapter == null) {
                    binding.recycler.swapAdapter(adapter, true)
                }
            })
        }

        with(binding) {
            with(toolbar) {
                toolbarTitle.text = when (movieListType) {
                    MovieListType.TOP_RATED -> getString(R.string.top_rated_movies)
                    MovieListType.POPULAR -> getString(R.string.popular_movies)
                    MovieListType.UPCOMING -> getString(R.string.upcoming_movies)
                }
            }
            with(recycler) {
                itemAnimator = null
                setHasFixedSize(true)
                setItemViewCacheSize(10)

                val lm = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                layoutManager = lm

                clearOnScrollListeners()

                addOnScrollListener(object : EndlessRecyclerViewScrollListener(lm) {
                    override fun onLoadMore() {
                        viewModel.loadNextPage(movieListType)
                    }
                })
            }
        }

    }

    companion object {
        private const val EXTRA_MOVIE_LIST_TYPE = "extra_movie_list_type"
        fun create(movieListType: MovieListType) = MovieListView().apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_MOVIE_LIST_TYPE, movieListType)
            }
        }

    }
}