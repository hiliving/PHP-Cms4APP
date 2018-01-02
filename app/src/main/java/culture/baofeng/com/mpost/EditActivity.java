package culture.baofeng.com.mpost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangyong on 2018/1/2.
 */

public class EditActivity extends AppCompatActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.edit_rv)
    RecyclerView editRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        ButterKnife.bind(this);


    }
}
