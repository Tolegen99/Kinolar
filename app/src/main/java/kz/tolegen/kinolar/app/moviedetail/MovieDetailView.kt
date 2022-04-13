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
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kz.tolegen.core.ext.bindCLick
import kz.tolegen.core.ext.visible
import kz.tolegen.core.ui.adapters.base.BaseDelegateAdapter
import kz.tolegen.core.ui.adapters.base.DiffItem
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.app.moviedetail.viewmodel.MovieDetailViewModel
import kz.tolegen.kinolar.databinding.ViewMovieDetailBinding
import kz.tolegen.kinolar.ui.delegates.SlotGenreDelegate
import kz.tolegen.kinolar.ui.delegates.SlotGenreUiModel

class MovieDetailView : Fragment(R.layout.view_movie_detail) {

    private val binding by viewBinding(ViewMovieDetailBinding::bind)
    private val viewModel: MovieDetailViewModel by viewModels()

    private val movieId: Long
        get() = arguments!!.getLong(EXTRA_MOVIE_ID)

    private val adapter by lazy {
        BaseDelegateAdapter.create {
            delegate {
                SlotGenreDelegate()
            }
        }
    }

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

                adapter.items = kotlin.run {
                    return@run mutableListOf<DiffItem>().apply {
                        addAll(it.genres.map { genre ->
                            SlotGenreUiModel(
                                id = genre.id,
                                name = genre.name
                            )
                        })
                    }
                }
                if (binding.rvGenres.adapter == null) {
                    binding.rvGenres.swapAdapter(adapter, true)
                }

                binding.tvlTitle.setValue(it.title)
                binding.tvlTagline.setValue(it.tagline)
                binding.tvlOverview.setValue(it.overview)
            })
        }

        with(binding) {
            with(toolbar) {
                toolbarBackBtn.visible()
                toolbarBackBtn bindCLick { viewModel.onBackPressed() }

                toolbarTitle.text = getString(R.string.movie_detail)
            }

            with(rvGenres) {
                itemAnimator = null
                setItemViewCacheSize(10)

                val lm = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)
                lm.justifyContent = JustifyContent.FLEX_START

                layoutManager = lm
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