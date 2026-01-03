package com.example.data.usecase.main.board

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.model.toDomainModel
import com.example.data.retrofit.BoardService
import com.example.domain.model.Board
import java.lang.Exception
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

class BoardPagingSource @Inject constructor(
    private val boardService: BoardService
) : PagingSource<Int, Board>() {
    override fun getRefreshKey(state: PagingState<Int, Board>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Board> {
        try {
            val page = params.key ?: 1
            val size = params.loadSize
            val response = boardService.getBoards(page, size)
            val getSize = response.data.size
            return LoadResult.Page(
                data = response.data.map {
                    it.toDomainModel()
                },
                prevKey = null,
                nextKey = if (getSize == size) page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}