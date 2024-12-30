package com.example.data.service

import com.example.domain.repository.model.LineInfoResponse
import com.example.domain.repository.model.StationDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SubwayService {

    @GET("/SearchSTNBySubwayLineInfo/1/1000")
    suspend fun getAllLineInfo(): Result<LineInfoResponse>

    @GET("/CardSubwayStatsNew1/1000")
    suspend fun getStationInfo(
        @Path("USE_YMD") date: String,
        @Path("SBWY_ROUT_LN_NM") line: String,
    ): Result<StationDetailResponse>

}
