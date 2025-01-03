package com.example.data.service

import com.example.data.model.LineInfoResponse
import com.example.data.model.StationDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SubwayService {

    @GET("/SearchSTNBySubwayLineInfo/1/1000")
    suspend fun getAllLineInfo(): LineInfoResponse

    @GET("/CardSubwayStatsNew/1/1000/{USE_YMD}/{SBWY_ROUT_LN_NM}")
    suspend fun getStationInfo(
        @Path("USE_YMD") date: String,
        @Path("SBWY_ROUT_LN_NM") line: String,
    ): StationDetailResponse

}
