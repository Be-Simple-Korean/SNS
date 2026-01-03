package com.example.data.usecase.main.board

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.database.BoardDataBase
import com.example.data.database.BoardRemoteMediator
import com.example.data.model.toDomainModel
import com.example.domain.model.Board
import com.example.domain.usecase.main.board.GetBoardsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBoardUseCaseImpl @Inject constructor(
    private val boardDataBase: BoardDataBase,
    private val mediator: BoardRemoteMediator
) : GetBoardsUseCase {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(): Result<Flow<PagingData<Board>>> = runCatching {
        Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10
            ),
            remoteMediator = mediator,
            pagingSourceFactory = {
                boardDataBase.boardDao().getAll()
            },
        ).flow
            .map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
    }
}