package us.mifeng.addanddelete.utils;

import android.content.Context;

/**
 * Created by 黑夜之火 on 2017/8/22.
 */

public class Dip2PxUtils {
    public static int dip2px(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5f);
    }
    public static int px2dip(Context context,float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }
}
