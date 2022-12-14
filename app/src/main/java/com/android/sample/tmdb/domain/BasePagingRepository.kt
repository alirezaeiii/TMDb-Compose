package com.android.sample.tmdb.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.sample.tmdb.data.paged.BasePagingSource
import com.android.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.flow.Flow

abstract class BasePagingRepository<T : TMDbItem> {

    protected abstract val pagingSource: BasePagingSource<T>

    val fetchResultStream: Flow<PagingData<T>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        pagingSourceFactory = { pagingSource }
    ).flow

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}