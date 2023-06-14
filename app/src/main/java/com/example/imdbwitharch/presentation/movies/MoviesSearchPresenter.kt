package com.example.imdbwitharch.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.imdbwitharch.util.Creator
import com.example.imdbwitharch.R
import com.example.imdbwitharch.domain.api.MoviesInteractor
import com.example.imdbwitharch.domain.models.Movie

class MoviesSearchPresenter(private val view: MoviesView,
                            private val context: Context) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }
    private val movies = ArrayList<Movie>()

    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

        fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.showPlaceholderMessage(false)
            view.showMoviesList(false)
            view.showProgressBar(true)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        view.showProgressBar(false)
                        if (foundMovies != null) {

                            // Обновляем список на экране
                            movies.clear()
                            movies.addAll(foundMovies)
                            view.updateMoviesList(movies)
                            view.showMoviesList(true)
                        }
                        if (errorMessage != null) {
                            showMessage(context.getString(R.string.something_went_wrong), errorMessage)
                        } else if (movies.isEmpty()) {
                            showMessage(context.getString(R.string.nothing_found), "")
                        } else {
                            hideMessage()
                        }}}})}}

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)
            movies.clear()
            view.updateMoviesList(movies)
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                // Добавили метод показа Toast
                view.showToast(additionalMessage)
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        // Заменили работу с элементами UI на
        // вызовы методов интерфейса
        view.showPlaceholderMessage(false)
    }}