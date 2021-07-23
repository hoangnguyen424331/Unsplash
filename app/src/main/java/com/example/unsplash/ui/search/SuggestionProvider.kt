package com.example.unsplash.ui.search

import android.content.SearchRecentSuggestionsProvider
import com.example.unsplash.BuildConfig.APPLICATION_ID

class SuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(APPLICATION_ID, DATABASE_MODE_QUERIES)
    }
}
