package culture.baofeng.com.mpost;

import java.util.List;

/**
 * Created by huangyong on 2017/12/26.
 */

public class MovieInfo {

    /**
     * code : 200
     * message : 首页数据获取成功
     * data : [{"name":"生化危机3","picUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&","descripe":"生化危机3，这是个科幻悬疑的电影","orderid":"17","id":"14","time":"2017.11.6","type":"科幻"},{"name":"盗梦空间","picUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&","descripe":"盗梦空间，这是个科幻悬疑的电影","orderid":"17","id":"13","time":"2016.11.6","type":"科幻"},{"name":"别有动机","picUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&","descripe":"别有动机，这是个都市心理系列的电影","orderid":"16","id":"12","time":"2017.8.6","type":"都市"},{"name":"异星觉醒","picUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&","descripe":"异星觉醒，这是个科幻冒险系列的电影","orderid":"16","id":"11","time":"2017.8.6","type":"科幻"},{"name":"黄金罗盘","picUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&","descripe":"黄金罗盘，这是个冒险系列的电影","orderid":"15","id":"10","time":"2015.8.6","type":"科幻"},{"name":"变形金刚4","picUrl":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=357475","descripe":"变形金刚系列第四部","orderid":"12","id":"9","time":"2016.8.6","type":"科幻"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 生化危机3
         * picUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&
         * descripe : 生化危机3，这是个科幻悬疑的电影
         * orderid : 17
         * id : 14
         * time : 2017.11.6
         * type : 科幻
         */

        private String name;
        private String picUrl;
        private String descripe;
        private String orderid;
        private String id;
        private String time;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getDescripe() {
            return descripe;
        }

        public void setDescripe(String descripe) {
            this.descripe = descripe;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
