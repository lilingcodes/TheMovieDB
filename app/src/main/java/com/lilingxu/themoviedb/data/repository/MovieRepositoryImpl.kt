package com.lilingxu.themoviedb.data.repository

import android.content.Context
import com.lilingxu.themoviedb.R
import com.lilingxu.themoviedb.data.network.performFlowTemplate
import com.lilingxu.themoviedb.data.network.services.DiscoverService
import com.lilingxu.themoviedb.data.network.services.GenresService
import com.lilingxu.themoviedb.data.network.services.MovieService
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.domain.model.Genre
import com.lilingxu.themoviedb.domain.model.HomeData
import com.lilingxu.themoviedb.domain.model.Movie
import com.lilingxu.themoviedb.domain.model.MovieDetails
import com.lilingxu.themoviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
enum class HomeSectionName(private val nameResId: Int){
    POPULAR(R.string.popular),
    UPCOMING(R.string.upcoming),
    NOW_PLAYING(R.string.upcoming),
    TOP_RATED(R.string.top_rated);

    fun getName(context: Context): String {
        return context.getString(nameResId)
    }

}
class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val discoverService: DiscoverService,
    private val genresService: GenresService,
    private val context: Context
) : MovieRepository {
    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        return movieService.getMovieDetails(movieId)
    }

    override suspend fun getHomeData(): Flow<Resource<MutableList<HomeData>>> {
        return performFlowTemplate {
            val homeDataList = mutableListOf<HomeData>()

            homeDataList.addApiResource(HomeSectionName.POPULAR)
            homeDataList.addApiResource(HomeSectionName.UPCOMING)
            homeDataList.addApiResource(HomeSectionName.NOW_PLAYING)
            homeDataList.addApiResource(HomeSectionName.TOP_RATED)

            Resource.Success(homeDataList)
        }
    }

    private suspend fun MutableList<HomeData>.addApiResource(homeSectionName: HomeSectionName){
        val apiResult: Resource<List<Movie>> = when(homeSectionName){
            HomeSectionName.POPULAR ->{
                movieService.getPopular()
            }
            HomeSectionName.UPCOMING ->{
                movieService.getUpcoming()
            }
            HomeSectionName.NOW_PLAYING ->{
                movieService.getNowPlaying()
            }
            HomeSectionName.TOP_RATED ->{
                movieService.getTopRated()
            }
        }

        if (apiResult is Resource.Success) {
            this.add(HomeData( apiResult.data ?: emptyList(), homeSectionName.getName(context)))
        }
    }

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return movieService.getPopular()
    }

    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        return movieService.getNowPlaying()
    }

    override suspend fun getUpcomingMovies(): Resource<List<Movie>> {
        return movieService.getUpcoming()
    }

    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return movieService.getTopRated()
    }

    override suspend fun getGenresTypeList(): Resource<List<Genre>> {
        return genresService.getMovieGenresList()
    }

    override suspend fun getMoviesByGenre(genreId: Int, page: Int): Resource<List<Movie>> {
        return discoverService.getMoviesByGenre(genreId, page)
    }


}