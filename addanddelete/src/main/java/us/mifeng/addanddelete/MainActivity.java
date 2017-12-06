package us.mifeng.addanddelete;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.lankton.flowlayout.FlowLayout;
import us.mifeng.addanddelete.adapter.CustomAdapter;
import us.mifeng.addanddelete.bean.Custom;
import us.mifeng.addanddelete.utils.CustomJson;
import us.mifeng.addanddelete.utils.Dip2PxUtils;

public class MainActivity extends AppCompatActivity implements CustomAdapter.ICustomData{

    private Custom custom;
    private FlowLayout flowLayout;
    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载网络数据，  //显示效果     http://www.2cto.com/kf/201510/445880.html
        custom = loadJsonData();
        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        listView = (ListView) findViewById(R.id.listview);
        adapter = new CustomAdapter(this,custom,this);
        listView.setAdapter(adapter);
        initListListener();

    }

    private void initListListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private Custom loadJsonData() {
        try {
            InputStream in = getAssets().open("custom.json");
            InputStreamReader reader = new InputStreamReader(in,"utf-8");
            BufferedReader rea = new BufferedReader(reader);
            StringBuffer sb  = new StringBuffer();
            String str = null;
            while((str = rea.readLine())!=null){
                sb.append(str);
            }
            CustomJson customJson = new CustomJson();
            Custom custom = customJson.getCustom(new String(sb));
            return custom;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public void removeData(Custom.M m,int postion) {
        custom.getM().get(postion).remove(m);
        adapter.notifyDataSetChanged();
        initFloatLayout(m.getName(),postion);
    }

    public void initFloatLayout(final String ss, final int postion){
        int ranHeight = Dip2PxUtils.dip2px(this,30);
        int ranWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ranWidth,ranHeight);
        lp.setMargins(Dip2PxUtils.dip2px(this,10),5,Dip2PxUtils.dip2px(this,10),5);

        final TextView tv = new TextView(this);
        tv.setPadding(Dip2PxUtils.dip2px(this,15),0,Dip2PxUtils.dip2px(this,15),0);
        tv.setTextColor(Color.parseColor("#ff3030"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        tv.setText(ss);

        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setLines(1);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.parseColor("#cccccc"));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeView(tv);
                custom.getM().get(postion).add(new Custom().new M(ss));
                adapter.notifyDataSetChanged();
            }
        });
        flowLayout.addView(tv, lp);
    }
}
