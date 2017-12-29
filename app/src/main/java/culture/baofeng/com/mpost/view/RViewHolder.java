package culture.baofeng.com.mpost.view;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import culture.baofeng.com.mpost.R;

/**
 * Created by huangyong on 2017/12/29.
 */

public class RViewHolder extends ViewHolder {

    public ImageView rvpic;
    public ImageView rvmsk;
    public ProgressBar rvp;

    public RViewHolder(View itemView) {
        super(itemView);
        rvpic = itemView.findViewById(R.id.rv_pic);
        rvmsk = itemView.findViewById(R.id.rv_mask);
        rvp = itemView.findViewById(R.id.rv_progress);
    }
}
