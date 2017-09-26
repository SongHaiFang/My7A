package demo.song.com.my7a;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.sn.xlistviewlibrary.XListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
//            CollManager();
        }
    };
    private XListView listView;
    private MyBaseAdapter adapter;
    private List<MyBean.ApkBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (XListView) findViewById(R.id.list_001);
        listView.setXListViewListener(this);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        okGet();
    }

    private void okGet() {
        OkHttpUtils.get()
                .url("http://huixinguiyu.cn/Assets/js/data.js")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String string = response.toString();
                        MyBean myBean = new Gson().fromJson(string, MyBean.class);
                        list = myBean.apk;
                        adapter = new MyBaseAdapter(MainActivity.this, list);
                        listView.setAdapter(adapter);
                    }
                });

    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(0,list.get(2));
                //list.add(list.get(3));
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(list.get(2));
                //list.add(list.get(3));
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }
}
