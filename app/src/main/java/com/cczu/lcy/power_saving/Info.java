package com.cczu.lcy.power_saving;

import android.app.usage.UsageStats;

/**
 * Created by 13156 on 2018/1/14.
 */

public class Info {
    private UsageStats name;
    private String number;
    private String times;
    private String bytes;


    public void setName(UsageStats name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public UsageStats getName(String s) {
        return name;
    }

    public String getNumber(long useDurationWithL) {
        return number;
    }

    public String getTimes(long useTimeWithL) {
        return times;
    }

    public String getBytes() {
        return bytes;
    }

    public UsageStats getName() {
        return name;
    }
}
