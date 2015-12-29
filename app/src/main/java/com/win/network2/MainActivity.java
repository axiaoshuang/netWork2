package com.win.network2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.win.network2.adapters.MyAdapter;
import com.win.network2.domain.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback {
    TextView textView;
    private Call call;
    private ListView listView;
    private MyAdapter adapter;
       ArrayList<Item>  list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      // textView  =(TextView)findViewById(R.id.txt);
        listView= (ListView) findViewById(R.id.main_list);

        adapter=new MyAdapter(this);
        listView.setAdapter(adapter);
        //1.Okhttp的应用


        OkHttpClient client=new OkHttpClient();
       Request request=new Request.Builder().url("http://m2.qiushibaike.com/article/list/suggest?page=").get().build();

        call=client.newCall(request);
        //同步请求
       // Response
        call.enqueue(this);
    }

    /**
     * 失败（在飞UI线程中执行）
     * @param request
     * @param e
     */
    @Override
    public void onFailure(Request request, IOException e) {

      runOnUiThread(new Runnable() {
          @Override
          public void run() {
              Toast.makeText(MainActivity.this,"网络错误",Toast.LENGTH_LONG).show();
          }
      });
    }

    /**
     * 成功（在非UI线程中执行）
     * @param response
     * @throws IOException
     */
    @Override
    public void onResponse(Response response) throws IOException {
       final String s = response.body().string();

        try {
            JSONObject  object=new JSONObject(s);
            JSONArray items = object.getJSONArray("items");
           list=new ArrayList<>();
            for (int i = 0; i <items.length() ; i++) {
//                 list.add(items.getJSONObject(i).getString("content"));
                list.add(new Item(items.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addAll(list);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        call.cancel();
    }
}
