package culture.baofeng.com.mpost;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huangyong on 2017/12/26.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("add.php?format=json")
    Observable<Data> commitMsg(@Field("name") String movieName,
                               @Field("descripe") String movieDesc,
                               @Field("picUrl") String picUrl,
                               @Field("orderid")  String movieOrder,
                               @Field("time")  String pTime,
                               @Field("type")  String pType);
    @Multipart
    @POST("upload.php")
    Call<UploadBean> upload(@Part("description") RequestBody description,

                                                          @Part MultipartBody.Part body);
    @GET("getData.php")
    Observable<MovieInfo> getData(@Query("pagesize") int pagesize,@Query("page") int page);
}
