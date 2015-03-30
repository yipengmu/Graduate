package com.laomu.justgraduate.common.datatype;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by yipengmu on 2014/12/14.
 */
public class MyTimestamp extends Timestamp implements Parcelable {

    public MyTimestamp(long theTime) {
        super(theTime);
    }

    public MyTimestamp(int theYear, int theMonth, int theDate, int theHour, int theMinute, int theSecond, int theNano) throws IllegalArgumentException {
        super(theYear, theMonth, theDate, theHour, theMinute, theSecond, theNano);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}