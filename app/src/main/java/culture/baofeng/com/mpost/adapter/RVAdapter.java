package culture.baofeng.com.mpost.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import culture.baofeng.com.mpost.MutiUpBean;
import culture.baofeng.com.mpost.R;
import culture.baofeng.com.mpost.view.RViewHolder;

/**
 * Created by huangyong on 2017/12/29.
 */

public class RVAdapter extends RecyclerView.Adapter<RViewHolder> {

    private Context context;
    private MutiUpBean.DataBean databean;
    private float progresss = 0;
    private int pos =0;
    private List<String> paths =new ArrayList<>();
    private boolean finish = false;

    public RVAdapter(Context context, MutiUpBean.DataBean databean) {
        this.context = context;
        this.databean = databean;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item,null);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        Glide.with(context).load(paths.get(position)).into(holder.rvpic);
        if (finish){
            holder.rvp.setVisibility(View.GONE);
            holder.rvmsk.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }

    @Override
    public long getItemId(int position) {
        return pos;
    }

    public void setProgress(int tmp, float v) {
        this.pos = tmp;
        this.progresss = v;
    }


    public void setFileData(List<String> path) {
        this.paths = path;
    }

    public void setFinish() {
        this.finish = true;
        notifyDataSetChanged();
    }
}
