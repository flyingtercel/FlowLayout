package us.mifeng.addanddelete.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import us.mifeng.addanddelete.bean.Custom;

/**
 * Created by 黑夜之火 on 2017/8/22.
 */

public class CustomJson {
    public Custom getCustom(String json){
        Custom custom = new Custom();
        try {
            //创建Object对象
            JSONObject object = new JSONObject(json);
            ArrayList<Custom.D>cusd = new ArrayList<>();
            JSONArray array = object.optJSONArray("d");
            for(int i=0;i<array.length();i++){
                JSONObject ob1 = array.optJSONObject(i);
                String name = ob1.optString("name");
                String id = ob1.optString("id");
                String type = ob1.optString("type");
                String original = ob1.optString("original");
                String img = ob1.optString("img");
                Custom.D d = custom.new D(name);
                cusd.add(d);
            }

            JSONArray arr = object.optJSONArray("m");
            ArrayList<ArrayList<Custom.M>>cusm = new ArrayList<>();
            for(int i=0;i<arr.length();i++){
                ArrayList<Custom.M>ll = new ArrayList<>();
                JSONArray aaa = arr.optJSONArray(i);
                for(int j=0;j<aaa.length();j++){
                    JSONObject ob = aaa.optJSONObject(j);
                    String name = ob.optString("name");
                    String id = ob.optString("id");
                    String type = ob.optString("type");
                    String l_type = ob.optString("l_type");
                    Custom.M m = custom.new M(name);
                    ll.add(m);
                }
                cusm.add(ll);
            }
            custom.setD(cusd);
            custom.setM(cusm);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return custom;
    }
}
