package com.laomu.justgraduate.modules.login.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.laomu.justgraduate.common.datatype.MyTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yipengmu on 2014/11/21.
 */
public class UserInfo implements Parcelable {
    @DatabaseField(generatedId=true)
    public Integer id;
    @DatabaseField
    public String uid = "";
    @DatabaseField
    public String upassword = "";
    @DatabaseField
    public String uname = "";
    @DatabaseField
    public Integer male;
    @DatabaseField
    public String face_logo = "";
    @DatabaseField
    public String tel;
    /**
     * DATE值的格式是'YYYY-MM-DD'。按照标准的SQL，不允许其他格式
     */
    @DatabaseField(dataType= DataType.SERIALIZABLE)
    public MyTimestamp signup_date;
    @DatabaseField
    public String school_name = "";
    @DatabaseField
    public String school_id = "";
    @DatabaseField
    public String department;
    @DatabaseField
    public String classes = "";
    @DatabaseField
    public String grade = "";
    @DatabaseField
    public String degree = "";
    @DatabaseField(dataType= DataType.SERIALIZABLE)
    public MyTimestamp last_login_time;

    @Override
    public String toString() {
        return "uid=" + uid + "  uname=" + uname + " upassword=" + upassword;
    }

    public UserInfo() {
    }

    /**
     * @param in
     */
    public UserInfo(Parcel in) {
        id = in.readInt();
        uid = in.readString();
        upassword = in.readString();
        uname = in.readString();
        male = in.readInt();
        face_logo = in.readString();
        tel = in.readString();

        signup_date = in.readParcelable(MyTimestamp.class.getClassLoader());
        school_name = in.readString();
        school_id = in.readString();
        department = in.readString();
        classes = in.readString();
        grade = in.readString();
        degree = in.readString();

        last_login_time = in.readParcelable(MyTimestamp.class.getClassLoader());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(uid);
        out.writeString(upassword);
        out.writeString(uname);
        out.writeInt(male);
        out.writeString(face_logo);
        out.writeString(tel);
        out.writeParcelable(signup_date,0);
        out.writeString(school_name);
        out.writeString(school_id);
        out.writeString(department);
        out.writeString(classes);
        out.writeString(grade);
        out.writeString(degree);
        out.writeParcelable(last_login_time,0);


    }
}
