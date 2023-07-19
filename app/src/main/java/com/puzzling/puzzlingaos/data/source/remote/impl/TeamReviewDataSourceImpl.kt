package com.puzzling.puzzlingaos.data.source.remote.impl

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRankingDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import com.puzzling.puzzlingaos.data.service.TeamReviewService
import com.puzzling.puzzlingaos.data.source.remote.TeamReviewDataSource
import javax.inject.Inject

class TeamReviewDataSourceImpl @Inject constructor(
    private val apiService: TeamReviewService,
) : TeamReviewDataSource {
    override suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): ResponseTeamReviewListDto =
        apiService.getTeamRetroList(projectId, startDate, endDate)

    override suspend fun getTeamPuzzle(projectId: Int, today: String): ResponseTeamPuzzleBoardDto =
        apiService.getTeamPuzzleBoard(projectId, today)

    override suspend fun getTeamRanking(projectId: Int): ResponseTeamRankingDto =
        apiService.getTeamRanking(projectId)
}
