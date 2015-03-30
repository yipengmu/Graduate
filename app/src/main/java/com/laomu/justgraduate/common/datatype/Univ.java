package com.laomu.justgraduate.common.datatype;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yipengmu on 2014/11/21.
 *
 * 大学 bean
 */
public class Univ implements Parcelable {
    @DatabaseField(generatedId=true)
    public Integer id;
    @DatabaseField
    public String name = "";
    @DatabaseField
    public Integer pid;

    @Override
    public String toString() {
        return "id=" + id + "  name=" + name + " pid=" + pid;
    }

    public Univ() {
    }

    /**
     * @param in
     */
    public Univ(Parcel in) {
        id = in.readInt();
        name = in.readString();
        pid = in.readInt();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeInt(pid);

    }
}
