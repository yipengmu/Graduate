package com.laomu.justgraduate.common.datatype;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yipengmu on 2014/11/21.
 *
 * 大学 bean
 */
public class School implements Parcelable {
    @DatabaseField(generatedId=true)
    public Integer id;
    @DatabaseField
    public String name = "";
    @DatabaseField
    public Integer uid;

    @Override
    public String toString() {
        return "id=" + id + "  name=" + name + " uid=" + uid;
    }

    public School() {
    }

    /**
     * @param in
     */
    public School(Parcel in) {
        id = in.readInt();
        name = in.readString();
        uid = in.readInt();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeInt(uid);

    }
}
