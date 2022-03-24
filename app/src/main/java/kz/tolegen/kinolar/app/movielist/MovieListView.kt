package kz.tolegen.kinolar.app.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.movielist.viewmodel.MovieListViewModel
import kz.tolegen.kinolar.base.BaseFragment
import kz.tolegen.kinolar.databinding.ViewMovieListBinding

class MovieListView : BaseFragment<ViewMovieListBinding>(R.layout.view_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getTopRatedMovies()

        viewModel.topRatedMovies.observe(viewLifecycleOwner, {
            binding.text.text = it.toString()
        })

        return binding {
        }.root
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewMovieListBinding = ViewMovieListBinding.inflate(inflater, container, false)
}