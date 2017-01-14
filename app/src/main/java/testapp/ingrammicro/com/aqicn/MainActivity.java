package testapp.ingrammicro.com.aqicn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtNewMumbai,txtUS,txtBandra,txtOutput;
    //Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBandra = (TextView)findViewById(R.id.txtbandra);

        txtUS = (TextView)findViewById(R.id.txtusconsulate);
        txtNewMumbai = (TextView)findViewById(R.id.txtnewmumbai);

        txtOutput = (TextView)findViewById(R.id.txtoutput);

    }

    public void OnBandraClick(View v){

        getAirQuallityIndex("19.041847,72.865513");
    }

    public void OnNewMumbaiClick(View v){
        getAirQuallityIndex("19.0759837,72.8776559");
    }

    public void OnUSClick(View v){

        getAirQuallityIndex("19.0643285,72.8688142");
    }


    private void getAirQuallityIndex(String cordinates){
        //final Integer[] aqi = new Integer[1];
        MyApiEndpointInterface service =
                 ServiceGenerator.createService(MyApiEndpointInterface.class);

         // Fetch and print a list of repositories for user “fs-opensource”
         Call<ResponseAqi> call = service.getAQI(cordinates);
         call.enqueue(new Callback<ResponseAqi>() {
             @Override
             public void onResponse(Call<ResponseAqi> call, Response<ResponseAqi> response) {
                //setBgColor(response.body().data.aqi);
                 Log.d("TAG",response.body().toString());
             }

             @Override
             public void onFailure(Call<ResponseAqi> call, Throwable t) {
                 setBgColor(0);
             }
         });

       // return aqi[0];
    }


    private void setBgColor(Integer airquality){
        txtOutput.setText(airquality.toString());
        if(airquality >=0 && airquality <=50){
            txtOutput.setBackgroundColor(getResources().getColor(R.color.good));
        }else if(airquality >=51 && airquality <=100){
            txtOutput.setBackgroundColor(getResources().getColor(R.color.moderate));
        }else if(airquality >=101 && airquality <=150){
            txtOutput.setBackgroundColor(getResources().getColor(R.color.unhealthysensitive));
        }else if (airquality >=151 && airquality <=200){
            txtOutput.setBackgroundColor(getResources().getColor(R.color.unhealthy));
        }else if(airquality >=201 && airquality <=300){
            txtOutput.setBackgroundColor(getResources().getColor(R.color.veryunhealthy));
        }else{
            txtOutput.setBackgroundColor(getResources().getColor(R.color.hazardous));
        }

    }
}
