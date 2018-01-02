package culture.baofeng.com.mpost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.donkingliang.imageselector.PreviewActivity;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import culture.baofeng.com.mpost.adapter.RVAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

/**
 * Created by huangyong on 2017/12/27.
 */

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_SELECT_IMG = 1;
    private static final int REC_REQUESTSINGLE = 1000;//单个文件
    private static final int REC_REQUESTMUTI = 1001;//单个文件
    private static final int REQUEST_CODE_SELECT_MUTIIMG = 2;
    @BindView(R.id.upresult)
    TextView upresult;
    @BindView(R.id.singleup)
    Button singleup;
    @BindView(R.id.mutiup)
    Button mutiup;
    @BindView(R.id.rv)
    RecyclerView rv;
    private MutiUpBean.DataBean dataBean;
    private RVAdapter rvAdapter;
    private static final int REQUEST_CODE = 0x00000011;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_layout);
        ButterKnife.bind(this);
        singleup.setOnClickListener(this);
        mutiup.setOnClickListener(this);
        dataBean = new MutiUpBean.DataBean();
        rvAdapter = new RVAdapter(this,dataBean);
        rv.setLayoutManager(new GridLayoutManager(this,3));

        rv.setAdapter(rvAdapter);
    }

    public void uploadSingleFile() {

//        ImageSelector.show(this, REQUEST_CODE_SELECT_IMG);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageSelectorUtils.openPhoto(UploadActivity.this, REQUEST_CODE_SELECT_IMG, true, 0);
            }
        });
    }

    public void upLoadMutiFile() {
//        ImageSelector.show(this, REQUEST_CODE_SELECT_MUTIIMG);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageSelectorUtils.openPhoto(UploadActivity.this, REQUEST_CODE_SELECT_MUTIIMG, false, 9);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.singleup:
                uploadSingleFile();
                break;
            case R.id.mutiup:
                upLoadMutiFile();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SELECT_IMG) {
            List<String> path =  data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            Log.d("imgSelector", "paths: " + path.get(0));
            upload(path.get(0));

         //   startActivity(new Intent(UploadActivity.this, PreviewActivity.class));
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SELECT_MUTIIMG) {
            List<String> path =  data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);//得到uri，后面就是将uri转化成file的过程。
            upload(path, true);
        }
    }

    private void upload(List<String> path, boolean b) {
        List<File> filesList = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            File files = new File(path.get(i));
            filesList.add(files);
        }
        rvAdapter.setFileData(path);
        rvAdapter.notifyDataSetChanged();
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
        Map<String, RequestBody> params = new HashMap<>();
        //这里需要注意的是"upload[]\"; filename=\""这个必须按这种格式写，因为是多个文件，以数组识别
        for (int i = 0; i < filesList.size(); i++) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), filesList.get(i));
//            params.put("upload[]\"; filename=\""+filesList.get(i).getName(), requestBody);
            final int tmp = i;
            RetrofitCallback<Result> callback = new RetrofitCallback<Result>() {
                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }

                @Override
                public void onSuccess(Call<Result> call, Response<Result> response) {
                    Log.d("监听进度", "这是第" + tmp + "个文件,文件上传成功");
                }

                @Override
                public void onLoading(final long total, final long progress) {
                    //此处进行进度更新
                   // rvAdapter.setProgress(tmp,progress/total*100.0f);
                    Log.d("监听进度", "这是第" + tmp + "个文件,文件总长" + total + "当前进度为" + progress);
                    ProgressBar rvp = rv.getChildAt(tmp).findViewById(R.id.rv_progress);
                   // ImageView rvm = rv.getChildAt(tmp).findViewById(R.id.rv_mask);

                    rvp.setProgress((int) (progress/total*100));

                }
            };
            //通过该行代码将RequestBody转换成特定的FileRequestBody
            FileRequestBody body = new FileRequestBody(requestBody, callback);
            params.put("upload[]\"; filename=\"" + filesList.get(i).getName(), body);
        }
        final ApiService mApi = retrofit.create(ApiService.class);
        // 执行请求
        mApi.uploadMuti(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MutiUpBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        upresult.setText("上传失败"  + e.getCause());
                        Log.d("AAATTT",  "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+e.getStackTrace().toString());
                         e.printStackTrace();
                        Log.d("AAATTT", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    }

                    @Override
                    public void onNext(MutiUpBean uploadBean) {
                        upresult.setText("上传成功");
                        rvAdapter.setFileData(uploadBean.getData().getPath());
                    }
                });
    }

    public void upload(String path) {

        File files = new File(path);
        if (files.exists()) {
            Log.d("AAAAAAAAAAAAAA", "文件存在:" + files.getAbsolutePath());
        }
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
                RequestBody.create(MediaType.parse("multipart/form-data"), files);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("upload", files.getName(), requestFile);
        // 执行请求
        mApi.upload(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UploadBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        upresult.setText("上传失败" + e.getMessage() + e.getCause());
                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {
                        upresult.setText("上传成功");
                    }
                });
        /*call.enqueue(new Callback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.d("AAAAAAAAAAAA", "上传成功");
                upresult.setText("上传成功");
            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                Log.d("AAAAAAAAAAAA", "上传失败");
                t.printStackTrace();
                upresult.setText("上传失败"+t.getMessage()+t.getCause());
            }
        });*/
       /* final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(UrlConfig.BaseUrl+"tup.php")
                .post(requestFile)
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("aa", "uploadMultiFile() e=" + e);
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.i("bb", "uploadMultiFile() response=" + response.body().string());
            }

        });*/
    }
}
