package kz.tolegen.kinolar.app.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kz.tolegen.core.ui.BaseFragment
import kz.tolegen.kinolar.app.movielist.viewmodel.MovieListViewModel
import kz.tolegen.kinolar.data.models.enums.MovieListType
import kz.tolegen.kinolar.databinding.ViewMovieListBinding

class MovieListView : BaseFragment<ViewMovieListBinding>() {

    private val viewModel: MovieListViewModel by viewModels()

    private val movieListType: MovieListType
        get() = arguments!!.getSerializable(EXTRA_MOVIE_LIST_TYPE) as MovieListType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getMovies(movieListType)

        viewModel.movies.observe(viewLifecycleOwner, {

        })


        return binding {

        }.root
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ViewMovieListBinding.inflate(inflater, container, false)

    companion object {
        private const val EXTRA_MOVIE_LIST_TYPE = "extra_movie_list_type"
        fun create(movieListType: MovieListType) = MovieListView().apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_MOVIE_LIST_TYPE, movieListType)
            }
        }

    }
}