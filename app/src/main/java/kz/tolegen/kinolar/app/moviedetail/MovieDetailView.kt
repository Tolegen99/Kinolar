package kz.tolegen.kinolar.app.moviedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kz.tolegen.core.ui.BaseFragment
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.moviedetail.viewmodel.MovieDetailViewModel
import kz.tolegen.kinolar.databinding.ViewMovieDetailBinding

class MovieDetailView : BaseFragment<ViewMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val movieId: Long
        get() = arguments!!.getLong(EXTRA_MOVIE_ID)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getMovieDetail(movieId)
        viewModel.movieDetail.observe(viewLifecycleOwner, {
            Glide
                .with(binding.root)
                .asDrawable()
                .transform(RoundedCorners(64))
                .load("https://image.tmdb.org/t/p/original${it.backdrop_path}")//TODO()
                .into(object : DrawableImageViewTarget(binding.ivBackdrop) {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        super.onResourceReady(resource, transition)
                    }
                })

            binding.tvlTitle.setValue(it.title)
            binding.tvlTagline.setValue(it.tagline)
            binding.tvlOverview.setValue(it.overview)
        })

        return binding {
            toolbar.toolbarTitle.text = getString(R.string.movie_detail)
        }.root
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewMovieDetailBinding = ViewMovieDetailBinding.inflate(inflater, container, false)

    companion object {

        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun create(movieId: Long) = MovieDetailView().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_MOVIE_ID, movieId)
            }
        }
    }

}