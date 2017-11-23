package com.bwie.mcowu.lx_zhouthree.Utils;

import android.content.Context;
import android.util.Log;

import com.bwie.mcowu.lx_zhouthree.Bean2;
import com.bwie.mcowu.lx_zhouthree.Bean2Dao;
import com.bwie.mcowu.lx_zhouthree.DaoMaster;
import com.bwie.mcowu.lx_zhouthree.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 姓名：McoWu
 * 时间:2017/11/22 10:31.
 * 本类作用:
 */

public class DBHelper {
    private static Context mContext;
    private static DBHelper instance;
    Bean2Dao bean2Dao;
    Bean2 bean;
    private DBHelper() {
    }
    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper();
            if (mContext == null) {
                mContext = context;
            }

            // 数据库对象
            // 数据库对象
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, "student.db", null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            DaoSession daoSession = daoMaster.newSession();
            instance.bean2Dao = daoSession.getBean2Dao();
        }
        return instance;
    }
    /** 添加数据 */
    public void addToCityInfoTable(Bean2 item)
    {
        bean2Dao.insert(item);
    }
    /** 查询 */

    /** 通过城市id查找其类型id */
    public List<Bean2> getCityInfoList(int threadNum) {
        QueryBuilder<Bean2> qb = bean2Dao.queryBuilder();
        qb.where(Bean2Dao.Properties.ThreadNum.eq(threadNum));
        List<Bean2> list = qb.list();
        Log.i("========", "getCityInfoList: " + list);
        return list;
    }

    public void updateInfo(int id,long rang){
        QueryBuilder<Bean2> qb = bean2Dao.queryBuilder();
        qb.where(Bean2Dao.Properties.ThreadNum.eq(id));
        List<Bean2> list = qb.list();
        Bean2 bean2 = list.get(0);
        bean2.setRange(rang);
        bean2Dao.update(bean2);
    }
}
