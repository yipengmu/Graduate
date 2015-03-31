/**
 * 
 */
package com.laomu.justgraduate.common.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.laomu.justgraduate.application.JGApplication;
import com.laomu.justgraduate.common.datatype.Province;
import com.laomu.justgraduate.common.datatype.School;
import com.laomu.justgraduate.common.datatype.Univ;
import com.laomu.justgraduate.modules.login.account.UserInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author luoyuan.myp
 *
 * 2014-4-15
 */
public class OrmDbManager {
	private static OrmDbManager ins = null;

	private Context mContext;
	private TableHelper4db mTableHelper4db;

    private Dao<UserInfo, Integer> accountDao = null;
    private Dao<Province, Integer> provinceDao = null;
    private Dao<School, Integer> schoolDao = null;
    private Dao<Univ, Integer> univDao = null;

    private List<UserInfo> userinfoList;
    private List<Province> provincesList;
    private List<School> schoolsList;
    private List<Univ> univsList;

	private OrmDbManager(Context c){
		mContext = c;
		mTableHelper4db = new TableHelper4db(mContext);

        try {
            accountDao = mTableHelper4db.getDao(UserInfo.class);
            provinceDao = mTableHelper4db.getDao(Province.class);
            schoolDao = mTableHelper4db.getDao(School.class);
            univDao = mTableHelper4db.getDao(Univ.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public static OrmDbManager getInstance(){
		if(null == ins){
			ins = new OrmDbManager(JGApplication.appContext);
    }
		return ins ;
	}
	
	public void addAccount(UserInfo obj){
		try {
            accountDao.create(obj);
            accountDao.clearObjectCache();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public List<UserInfo> getAllUserInfo(){
        if(userinfoList != null){
            return userinfoList;
        }

        try {
            userinfoList = accountDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userinfoList;
    }

    public List<Province> getAllProvices(){
        if(provincesList != null){
            return provincesList;
        }

        try {
            provincesList = provinceDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return provincesList;
    }


    public List<School> getAllSchools(){
        if(schoolsList != null){
            return schoolsList;
        }

        try {
            schoolsList = schoolDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolsList;
    }


    public List<Univ> getAllUniv(){
        if(univsList != null){
            return univsList;
        }

        try {
            univsList = univDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return univsList;
    }


//
//	public void delNote(NoteBean note){
//		try {
//			noteDao = mTableHelper4db.getDao(NoteBean.class);
//			noteDao.delete(note);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateNote(NoteBean note){
//		try {
//			noteDao = mTableHelper4db.getDao(NoteBean.class);
//			noteDao.update(note);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public List<NoteBean> queryNote(){
//		List<NoteBean> list = null;
//		try {
//			noteDao = mTableHelper4db.getDao(NoteBean.class);
//			list = noteDao.queryForAll();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<NoteBean> queryNote(int searchId){
//		List<NoteBean> list = null;
//		try {
//			noteDao = mTableHelper4db.getDao(NoteBean.class);
//			list = noteDao.queryForEq("id",searchId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//
//	public int addLocation(LocationBean loc){
//		int locationId = -1;
//		try {
//			locationDao = mTableHelper4db.getDao(LocationBean.class);
//			//向location 表中先插入一条记录
//			locationDao.create(loc);
//			//在从表中尝试取出此条记录，并拿到主键的key,即locationId
//			List<LocationBean> resultList = locationDao.queryForMatching(loc);
//			if(resultList != null && resultList.size() == 1){
//				locationId = resultList.get(0).id;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return locationId;
//	}
//
//	public  queryLocation(int searchId){
//		List<LocationBean> list = null;
//		try {
//			locationDao = mTableHelper4db.getDao(LocationBean.class);
//			lList<LocationBean>ist = locationDao.queryForEq("id",searchId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public void queryLocationId(LocationBean mLocation) {
//		try {
//			noteDao = mTableHelper4db.getDao(LocationBean.class);
//			noteDao.queryForId(0);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}


}
