# FlowLayout
Android第三方库——FlowLayout

一、添加依赖
  compile 'com.nex3z:flow-layout:0.1.2'  
二、使用
     1.在Xml中声明：
          <span style="font-size:12px;"><com.nex3z.flowlayout.FlowLayout  
              android:id="@+id/flow_layout"  
              android:layout_width="match_parent"  
              android:layout_height="match_parent"  
              app:childSpacing="6dp"  
              app:rowSpacing="8dp" />  
          <!--自定义属性-->  
          <!--  
              flow = "true"  流式布局  
              childSpacing：子控件之间的距离  "auto"  
              rowSpacing:行间距  
              childSpacingForLastRow：子控件最后一行的间距  
              rtl = "true" 一行从右往左一次排列  
          --></span>  
    2.给子控件设置shape
          <span style="font-size:12px;"><shape xmlns:android="http://schemas.android.com/apk/res/android">  
              <solid android:color="#ff00ff"/>  
              <corners android:radius="25dp"/>  
              <stroke android:width="1dp" android:color="#3799f4"/>  
          </shape</span>>    
          
     3.java代码中动态添加子控件
            private void getList(List<ListBean> list) {  
                   mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);  
                   /** 
                    * 根据集合动态生成控件TextView 
                    */  
                   for (final ListBean b: list) {  
                       TextView textView = new TextView(this);  
                       textView.setPadding(16,8,16,8);  
                       textView.setText(b.getName());  
                       textView.setTextSize(16);  
                       textView.setBackgroundResource(R.drawable.s);  
                       textView.setOnClickListener(new View.OnClickListener() {  
                           @Override  
                           public void onClick(View v) {  
                               Snackbar.make(v,b.getTitle(),Snackbar.LENGTH_SHORT).show();  
                           }  
                       });  


                       mFlowLayout.addView(textView);  
                   }  
               } 
   本应用是在继承Android基础上做了一些修改，实现对FlowLayout中的数据添加与删除。
   首先在顶部定义了一个FlowLayout,
   在下面定义了一个ListView,而ListView的item上也是一个FlowLayout,
   当点击ListView的条目的FlowLayout中的数据的时候，将数据添加到顶部的FlowLayou中，
   当点击顶部的FlowLayout中数据的时候，将数据添加到底部ListView的条目上的FlowLayout中
   实现代码
      视图绘制
       <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
        android:layout_height="match_parent" tools:context="us.mifeng.addanddelete.MainActivity"
        android:orientation="vertical">
        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="50dp"
                    android:text="我的个性定制"
                    android:textSize="18sp"/>
                <cn.lankton.flowlayout.FlowLayout
                    android:id="@+id/flowLayout"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                </cn.lankton.flowlayout.FlowLayout>
                <us.mifeng.addanddelete.view.MyListView
                    android:id="@+id/listview"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"></us.mifeng.addanddelete.view.MyListView>
            </LinearLayout>
        </ScrollView>

    </LinearLayout>

     Activity中的逻辑实现
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
  
  
     ListView的适配器中的逻辑实现
        public class CustomAdapter extends BaseAdapter {
          private Context context;
          private Custom custom;
          private LayoutInflater inflater;
          private ICustomData iCustomData;

          public CustomAdapter(Context context, Custom custom, ICustomData iCustomData) {
              this.context = context;
              this.custom = custom;
              inflater = LayoutInflater.from(context);
              this.iCustomData = iCustomData;
          }

          @Override
          public int getCount() {
              return custom.getD().size();
          }

          @Override
          public Custom.D getItem(int position) {
              return custom.getD().get(position);
          }

          @Override
          public long getItemId(int position) {
              return position;
          }

          @Override
          public View getView(int position, View convertView, ViewGroup parent) {
              ViewHolder vh;
              if (convertView == null) {
                  convertView = inflater.inflate(R.layout.custom_item, null);
                  vh = new ViewHolder(convertView);
                  convertView.setTag(vh);
              } else {
                  vh = (ViewHolder) convertView.getTag();
              }
              Custom.D d = getItem(position);
              vh.textView.setText(d.getName());
              initRan(vh.flowLayout, custom.getM().get(position), position);
              return convertView;
          }

          private void initRan(final FlowLayout flowLayout, final ArrayList<Custom.M> ms, final int position) {
              flowLayout.removeAllViews();
              for (int i = 0; i < ms.size(); i++) {
                  int ranHeight = Dip2PxUtils.dip2px(context, 30);
                  int ranWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
                  ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ranWidth, ranHeight);
                  lp.setMargins(Dip2PxUtils.dip2px(context, 10), 5, Dip2PxUtils.dip2px(context, 10), 5);
                  final TextView tv = new TextView(context);
                  tv.setPadding(Dip2PxUtils.dip2px(context, 15), 0, Dip2PxUtils.dip2px(context, 15), 0);
                  tv.setTextColor(Color.parseColor("#ff3030"));
                  tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                  tv.setText(ms.get(i).getName());

                  tv.setGravity(Gravity.CENTER_VERTICAL);
                  tv.setLines(1);
                  tv.setTextColor(Color.BLACK);
                  tv.setBackgroundColor(Color.parseColor("#cccccc"));
                  tv.setTag(i);
                  tv.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          iCustomData.removeData(ms.get((Integer) tv.getTag()),position);
                      }
                  });
                  flowLayout.addView(tv, lp);
              }

          }

          public class ViewHolder {
              public TextView textView;
              public FlowLayout flowLayout;

              public ViewHolder(View view) {
                  textView = (TextView) view.findViewById(R.id.textview);
                  flowLayout = (FlowLayout) view.findViewById(R.id.flowLayout);
              }

          }

          public interface ICustomData {
              void removeData(Custom.M m,int postion);
          }
      }
          
          
          
          
