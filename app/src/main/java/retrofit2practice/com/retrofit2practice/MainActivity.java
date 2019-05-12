package retrofit2practice.com.retrofit2practice;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("Loading...");
        progressBar.show();

        /*Create handle for the RetrofitInstance interface*/
       GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                                 .create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                progressBar.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {

                progressBar.dismiss();
                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateDataList(List<RetroPhoto> body) {
        recyclerView = findViewById(R.id.customRecyclerView);
        customAdapter = new CustomAdapter(this,body);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);
    }


}
