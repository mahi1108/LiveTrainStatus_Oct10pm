package cubex.mahesh.livetrainstatus_oct10pm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import cubex.mahesh.livetrainstatus_oct10pm.beans.LiveTrainStatusBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        train_status.setOnClickListener {

            var r = Retrofit.Builder().
                          addConverterFactory(GsonConverterFactory.create()).
                          baseUrl("https://api.railwayapi.com/").
                          build()
            var api = r.create(LiveTrainStatusAPI::class.java)
            var call = api.getTrainStatus(tno.text.toString(),
                                                                        date.text.toString())
            call.enqueue(object : Callback<LiveTrainStatusBean> {
                override fun onFailure(call: Call<LiveTrainStatusBean>, t: Throwable) {
                }
                override fun onResponse(call: Call<LiveTrainStatusBean>, response: Response<LiveTrainStatusBean>) {
                    var bean = response.body()
                    var temp_list = mutableListOf<String>()
                    temp_list.add(bean!!.position)
                    for(st in bean.route!!)
                    {
                        temp_list.add("Name: "+st.station.name+"\n"+
                       "Arr Time:"+st.actarr+"\t"+"Dept Time: "+st.actdep+
                                "\n"+st.status)
                    }
                    var adapter = ArrayAdapter<String>(
                            this@MainActivity,
                            R.layout.indiview,
                            temp_list)
                    lview.adapter = adapter
                }
            })
        }

    } // onCreate( )
}
