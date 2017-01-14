package testapp.ingrammicro.com.aqicn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by INJESS01 on 12/11/2016.
 */

public interface MyApiEndpointInterface {

    @GET("feed/geo:{geo}/?token=4135f54423cf1995ec56ae892324264b9743644d")
    Call<ResponseAqi> getAQI(@Path("geo") String location);

    //https://api.waqi.info/feed/geo:19.0759837;72.8776559/?token=4135f54423cf1995ec56ae892324264b9743644d

}
