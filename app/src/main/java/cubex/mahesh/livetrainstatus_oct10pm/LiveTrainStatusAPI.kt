package cubex.mahesh.livetrainstatus_oct10pm

import cubex.mahesh.livetrainstatus_oct10pm.beans.LiveTrainStatusBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LiveTrainStatusAPI {

    @GET("v2/live/train/{tno}/date/{date}/apikey/3us4nq7cyl/")
    fun getTrainStatus(@Path("tno") t:String,
                  @Path("date") d:String):Call<LiveTrainStatusBean>
}