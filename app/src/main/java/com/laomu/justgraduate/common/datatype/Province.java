package com.laomu.justgraduate.common.datatype;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yipengmu on 2014/11/21.
 *
 * 省份 bean
 */
public class Province implements Parcelable {
    @DatabaseField(generatedId=true)
    public Integer id;
    @DatabaseField
    public String name = "";

    @Override
    public String toString() {
        return "id=" + id + "  name=" ;
    }

    public Province() {
    }

    /**
     * @param in
     */
    public Province(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
    }
}
