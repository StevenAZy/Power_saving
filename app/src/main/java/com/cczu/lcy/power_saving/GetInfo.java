package com.cczu.lcy.power_saving;


import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 13156 on 2018/1/14.
 */

public class GetInfo {

    //List<Info> infolist = new ArrayList<Info>();
//    List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
//
//        for(int i=0;i<packages.size();i++) {
//            PackageInfo packageInfo = packages.get(i);
//            AppInfo tmpInfo =new AppInfo();
//            tmpInfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
//            tmpInfo.packageName = packageInfo.packageName;
//            tmpInfo.versionName = packageInfo.versionName;
//            tmpInfo.versionCode = packageInfo.versionCode;
//            tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
//            appList.add(tmpInfo);
//
//    }

        //获取包名
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public  List<UsageStats> getUsageStatsList(Context context){

            if(null == context) return null;

            UsageStatsManager usm = getUsageStatsManager(context);

            Calendar beginCal = Calendar.getInstance();
            beginCal.set(Calendar.DATE, 31);
            beginCal.set(Calendar.MONTH, 12);
            beginCal.set(Calendar.YEAR, 1970);

            long startTime = beginCal.getTimeInMillis();
            long endTime = System.currentTimeMillis();

            final List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST, startTime, endTime);

            if(usageStatsList.isEmpty()){
                Log.d("NULL","usage stats list is null");
            }else{
                Log.d("All of Pkg","usage stats list size = " + usageStatsList.size());
            }

            return usageStatsList;
        }


        private UsageStatsManager getUsageStatsManager(Context context){
            UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            return usm;
        }



    //使用时长
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public long getUseDurationWithL(List<UsageStats> usageStatsList, UsageStats pkName) {
        for (UsageStats stat : usageStatsList){
            if(stat.getPackageName().equals(pkName)){
                return stat.getTotalTimeInForeground();
            }
        }
        return 0;
    }



    /**
     * {@hide}
     */
    //点击次数
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public long getUseTimeWithL(List<UsageStats> usageStatsList, UsageStats pkName) {
        for (UsageStats stat : usageStatsList){

            if(stat.getPackageName().equals(pkName)){

                int count = 0;

                try {
                    Field field = stat.getClass().getDeclaredField("mLaunchCount");
                    count = field.getInt(stat);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                return count;
            }
        }
        return 0;
    }



    //获取流量
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public List<TrafficInfo> getTrafficInfo(Context context) {
        //获取到配置权限信息的应用程序
        PackageManager pms = context.getPackageManager();
        List<PackageInfo> packinfos = pms
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);
        //存放具有Internet权限信息的应用
        List<TrafficInfo> trafficInfos = new ArrayList<TrafficInfo>();
        for(PackageInfo packinfo : packinfos){
            //获取该应用的所有权限信息
            String[] permissions = packinfo.requestedPermissions;
            if(permissions!=null&&permissions.length>0){
                for(String permission : permissions){
                    //筛选出具有Internet权限的应用程序
                    if("android.permission.INTERNET".equals(permission)){
                        //用于封装具有Internet权限的应用程序信息
                        TrafficInfo trafficInfo = new TrafficInfo();
                        //封装应用信息
                        trafficInfo.setPackname(packinfo.packageName);
                        trafficInfo.setIcon(packinfo.applicationInfo.loadIcon(pm));
                        trafficInfo.setAppname(packinfo.applicationInfo.loadLabel(pm).toString());
                        //获取到应用的uid（user id）
                        int uid = packinfo.applicationInfo.uid;
                        //TrafficStats对象通过应用的uid来获取应用的下载、上传流量信息


                        //发送的 上传的流量byte
                        trafficInfo.setRx(TrafficStats.getUidRxBytes(uid));
                        //下载的流量 byte
                        trafficInfo.setTx(TrafficStats.getUidTxBytes(uid));
                        trafficInfos.add(trafficInfo);
                        trafficInfo = null;
                        break;
                    }
                }
            }
        }
        return trafficInfos;
    }



    }



