package kz.tolegen.kinolar.app.moviedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kz.tolegen.core.ext.bindCLick
import kz.tolegen.core.ext.visible
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.moviedetail.viewmodel.MovieDetailViewModel
import kz.tolegen.kinolar.databinding.ViewMovieDetailBinding

class MovieDetailView : Fragment(R.layout.view_movie_detail) {

    private val binding by viewBinding(ViewMovieDetailBinding::bind)
    private val viewModel: MovieDetailViewModel by viewModels()

    private val movieId: Long
        get() = arguments!!.getLong(EXTRA_MOVIE_ID)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            getMovieDetail(movieId)
            movieDetail.observe(viewLifecycleOwner, {
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
        }

        with(binding) {
            toolbar.apply {
                toolbarBackBtn.visible()
                toolbarBackBtn bindCLick { viewModel.onBackPressed() }

                toolbarTitle.text = getString(R.string.movie_detail)
            }
        }
    }

    companion object {
        private const val EXTRA_MOVIE_ID = "extra_movie_id"
        fun create(movieId: Long) = MovieDetailView().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_MOVIE_ID, movieId)
            }
        }
    }

}