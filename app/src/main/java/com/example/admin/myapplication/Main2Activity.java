package com.example.admin.myapplication;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private List<String> data2=new ArrayList();
    private int[] pids=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private String[] data={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",};
    private TextView textView;
    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.textView=(TextView) findViewById(R.id.abc);
        this.listview=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        listview.setAdapter(adapter);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("点击",""+position+":"+Main2Activity.this.pids[position]+":"+Main2Activity.this.data[position]);
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                intent.putExtra("pid",Main2Activity.this.pids[position]);
                startActivity(intent);
            }
        });

//        String weatherId="CN101020200";
        String weatherUrl = "http://guolin.tech/api/china";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                parseJSONObject(responseText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(responseText);
                    }
                });
            }
        });
    }

    private void parseJSONObject(String responseText) {
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(responseText);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                jsonObject.getString("name");
                this.data[i]=jsonObject.getString("name");
                this.pids[i]=jsonObject.getInt("id");
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
}
