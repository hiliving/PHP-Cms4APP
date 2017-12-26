package culture.baofeng.com.mpost;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangyong on 2017/12/26.
 */

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.movielist)
    ListView movielist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //添加应用拦截器
        OkHttpClient client = new OkHttpClient.Builder()
                //添加应用拦截器
                .connectTimeout(35, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlConfig.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        final ApiService mApi = retrofit.create(ApiService.class);

        Subscription subscription = mApi.getData(6, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieInfo info) {

                        MyAdapter adapter = new MyAdapter(info);
                        movielist.setAdapter(adapter);
                    }
                });
    }

    class MyAdapter extends BaseAdapter {
        private MovieInfo info;
        public MyAdapter(MovieInfo info) {
            this.info = info;
        }

        @Override
        public int getCount() {
            return info.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View vis = LayoutInflater.from(ListActivity.this).inflate(R.layout.item,null);
            TextView name = vis.findViewById(R.id.mname);
            TextView mdesc = vis.findViewById(R.id.desc);
            TextView time = vis.findViewById(R.id.publicTime);
            ImageView pic = vis.findViewById(R.id.pic);
            Glide.with(ListActivity.this).load(info.getData().get(i).getPicUrl()).into(pic);
            name.setText(info.getData().get(i).getName());
            mdesc.setText(info.getData().get(i).getDescripe());
            time.setText(info.getData().get(i).getTime());
            return vis;
        }
    }
}
