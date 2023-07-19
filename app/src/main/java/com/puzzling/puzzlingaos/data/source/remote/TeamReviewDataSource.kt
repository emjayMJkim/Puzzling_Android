package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRankingDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto

interface TeamReviewDataSource {

    suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): ResponseTeamReviewListDto

    suspend fun getTeamPuzzle(
        projectId: Int,
        today: String,
    ): ResponseTeamPuzzleBoardDto

    suspend fun getTeamRanking(
        projectId: Int,
    ): ResponseTeamRankingDto
}
