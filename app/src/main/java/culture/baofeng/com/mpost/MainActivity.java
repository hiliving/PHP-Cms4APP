package culture.baofeng.com.mpost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.descripe)
    EditText descripe;
    @BindView(R.id.postpic)
    EditText postpic;
    @BindView(R.id.orderid)
    EditText orderid;
    @BindView(R.id.time)
    EditText time;
    @BindView(R.id.type)
    EditText type;
    @BindView(R.id.show)
    TextView show;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.query)
    Button query;
    private String movieName;
    private String movieDesc;
    private String picUrl;
    private String movieOrder;
    private String pTime;
    private String pType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    movieName = editable.toString();
                } catch (Exception e) {

                }

            }
        });
        descripe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    movieDesc = editable.toString();
                } catch (Exception e) {

                }
            }
        });
        postpic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    picUrl = editable.toString();
                } catch (Exception e) {

                }
            }
        });
        orderid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    movieOrder = editable.toString();
                } catch (Exception e) {

                }
            }
        });
        time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    pTime = editable.toString();
                } catch (Exception e) {

                }
            }
        });
        type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    pType = editable.toString();
                } catch (Exception e) {

                }
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ListActivity.class));
            }
        });
    }

    /**
     * private String movieName;
     * private String movieDesc;
     * private String picUrl;
     * private String movieOrder;
     * private String pTime;
     * private String pType;
     */
    private void submit() {

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

        Subscription subscription = mApi.commitMsg(movieName, movieDesc, picUrl, movieOrder, pTime, pType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AAAAAAAAAAAAAAA", e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Data info) {
                        show.setText(info.getMessage());
                    }
                });

    }

    public void upload() {

        File files = new File("文件路径");//截取有效路径
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

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/otcet-stream"), files);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("myfile", files.getName(), requestFile);

        String descriptionString = "This is a description";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);
        // 执行请求
        Call<UploadBean> call = mApi.upload(description, body);

        call.enqueue(new Callback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.d("AAAAAAAAAAAA", "上传成功");
            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                Log.d("AAAAAAAAAAAA", "上传失败");
            }
        });
    }
}
