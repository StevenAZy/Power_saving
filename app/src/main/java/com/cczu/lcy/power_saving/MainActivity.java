package com.cczu.lcy.power_saving;

import android.app.usage.UsageStats;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.getinfo);
//        List<PackageInfo> apps = new ArrayList<PackageInfo>();
//
//        PackageManager packageManager = this.getPackageManager();
//        List<PackageInfo> pkgLists = packageManager.getInstalledPackages(0);
//        for (PackageInfo packageInfo : pkgLists) {
//            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
//            if ((applicationInfo.flags & applicationInfo.FLAG_SYSTEM) <= 0) {// is
//                // customer
//                apps.add(packageInfo);
//                Log.d("pin", "applicationInfo.packageName->" + applicationInfo.packageName);
//            }
//        }
//        Log.d("pin", "apps.size()->" + apps.size());
//
//        List<ResolveInfo> ResolveInfoLists = getResolveInfoLists();
//        Log.d("pin", "ResolveInfoLists .size()->" + ResolveInfoLists.size());
//
//        for (ResolveInfo resolveInfo : ResolveInfoLists) {
//
//            Log.d("pin", "resolveInfo.activityInfo.packageName->" + resolveInfo.activityInfo.packageName);
//        }
//    }
//
//    /**
//     * @Title: getResolveInfoLists
//     * @Description: 它是通过解析< Intent-filter>标签得到有 　　< action
//     *               android:name=”android.intent.action.MAIN”/> 　　< action
//     *               android:name=”android.intent.category.LAUNCHER”/>
//     * @param ：
//     * @return List<ResolveInfo>
//     * @throws
//     */
//    private List<ResolveInfo> getResolveInfoLists() {
//
//        // TODO Auto-generated method stub
//        PackageManager packageManager = this.getPackageManager();
//
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//        return packageManager.queryIntentActivities(intent, 0);
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getinfo);
        List<Info> infolist = new ArrayList<Info>();
        Info info = new Info();


        //获取应用名
//        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
//        List<Info> infolist = new ArrayList<Info>();
//        for (int i = 0; i < packages.size(); i++) {
//            PackageInfo packageInfo = packages.get(i);
//            Info tmpInfo = new Info();
//            tmpInfo.getName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
//            System.out.println("应用名：" + packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
//            infolist.add(tmpInfo);
//
//        }

            GetInfo gf = new GetInfo();
            List<UsageStats> name = gf.getUsageStatsList(getApplicationContext());
            for(int i=0;i<name.size();i++){
                info.setName(name.get(i));
                info.getTimes(gf.getUseTimeWithL(name,info.getName()));
                info.getNumber(gf.getUseDurationWithL(name,info.getName()));
            }

    }



}
