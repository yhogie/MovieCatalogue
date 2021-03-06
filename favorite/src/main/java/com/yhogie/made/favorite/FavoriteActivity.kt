package com.yhogie.made.favorite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yhogie.made.core.domain.model.Movie
import com.yhogie.made.core.ui.MovieAdapter
import com.yhogie.made.favorite.databinding.ActivityFavoriteBinding
import com.yhogie.made.moviecatalogue.detail.DetailActivity
import com.yhogie.made.moviecatalogue.di.FavoriteModuleDependencies
import com.yhogie.made.moviecatalogue.utils.Commons.hide
import com.yhogie.made.moviecatalogue.utils.Commons.show
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


@SuppressLint("GoogleAppIndexingApiWarning")
class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpToolbar()
        showFavoriteMovieList()
    }

    private fun setUpBinding() {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = resources.getString(R.string.favorite)
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showFavoriteMovieList() {
        val movieAdapter = MovieAdapter().apply {
            onItemClick = {
                navigateToDetailScreen(it)
            }
        }

        viewModel.favoriteMovie.observe(this, { movie ->
            movieAdapter.setData(movie)
            if (movie.isNullOrEmpty()) {
                binding.noData.root.show()
            } else {
                binding.noData.root.hide()
            }
        })

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun navigateToDetailScreen(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}