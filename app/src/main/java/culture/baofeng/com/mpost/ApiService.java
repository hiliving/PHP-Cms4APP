package culture.baofeng.com.mpost;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huangyong on 2017/12/26.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("php/cms/add.php?format=json")
    Observable<Data> commitMsg(@Field("name") String movieName,
                               @Field("descripe") String movieDesc,
                               @Field("picUrl") String picUrl,
                               @Field("orderid")  String movieOrder,
                               @Field("time")  String pTime,
                               @Field("type")  String pType);
    @Multipart
    @POST("singleUpload.php?format=json")
    Observable<UploadBean> upload(@Part MultipartBody.Part body);//单个文件上传
     @Multipart
    @POST("mutiUpload.php?format=json")
     Observable<MutiUpBean> uploadMuti(@PartMap Map<String,RequestBody> params);//多个文件上传,未测通过
    @GET("php/cms/getData.php")
    Observable<MovieInfo> getData(@Query("pagesize") int pagesize,@Query("page") int page);
}
