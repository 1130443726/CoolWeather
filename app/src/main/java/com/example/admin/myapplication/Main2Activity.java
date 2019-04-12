package com.example.admin.myapplication;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;

        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private String[] data={"北京","浙江","安徽"};
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

//        String weatherId="CN101020200";
        String weatherUrl = "http://guolin.tech/api/china";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                String[] result=parseJSONObject(responseText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(responseText);
                    }
                });
            }
        });
    }

    private String[] parseJSONObject(String responseText) {
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(responseText);
            String[] result=new String[jsonArray.length()];
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                result[i]=jsonObject.getString("name");
            }
            return result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
    }
}
