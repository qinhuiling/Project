package com.bwie.asus.thread.db;

/**
 * Created by ASUS on 2017/11/19.
 */

import android.annotation.SuppressLint;
import android.content.Context;

import com.bwie.asus.thread.FileResult;
import com.bwie.asus.thread.app.MyApp;
import com.wyk.greendaodemo.greendao.gen.FileResultDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 该类主要用来保存下载记录
 */
public class FileDBService {
    private String TAG="===============FileDBService================";
    //	private DBOpenHelper openHelper;
    private final MyApp instances;

    public FileDBService(Context context) {
//		openHelper = new DBOpenHelper(context);
        instances = (MyApp) context.getApplicationContext();
    }

    /**
     * 获取每条线程已经下载的文件长度
     *
     * @param path
     * @return
     */
    @SuppressLint("LongLogTag")
    public Map<Integer, Integer> getData(String path) {
        Map<Integer, Integer> data = new HashMap<Integer, Integer>();

        List<FileResult> list = instances.getDaoSession().getFileResultDao().queryBuilder().where(FileResultDao.Properties.Downpath.eq(path)).build().list();
        for (FileResult fileResult : list) {
            data.put(fileResult.getThreadid(),fileResult.getDownlength());
        }
//		SQLiteDatabase db = openHelper.getReadableDatabase();
//		Cursor cursor = db.rawQuery("select THREADID, DOWNLENGTH from FILE_RESULT where DOWNPATH=?", new String[] { path });

//		while (cursor.moveToNext()) {
//			data.put(cursor.getInt(0), cursor.getInt(1));
//			Log.i("=====sbhelper======", "getData: " +cursor.getInt(0) + "-->" + cursor.getInt(1));
//		}
//		cursor.close();
//		db.close();
        return data;
    }

    /**
     * 保存每条线程已经下载的文件长度
     * @param path
     * @param map
     */
    @SuppressLint("LongLogTag")
    public void save(String path, Map<Integer, Integer> map) {// int threadid,
        //使用sqlite数据库
//		SQLiteDatabase db = openHelper.getWritableDatabase();
//		db.beginTransaction();
//		try {
//			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//				db.execSQL("insert into FILE_RESULT(DOWNPATH, THREADID, DOWNLENGTH) values(?,?,?)",
//						new Object[] { path, entry.getKey(), entry.getValue() });
        //使用greendao数据库
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            instances.getDaoSession().getFileResultDao().insert(new FileResult(null,path,entry.getKey(),entry.getValue()));
        }

//			}
//			db.setTransactionSuccessful();
//		} finally {
//			db.endTransaction();
//		}
//		db.close();
    }

    /**
     * 实时更新每条线程已经下载的文件长度
     * @param path
     */
    @SuppressLint("LongLogTag")
    public void update(String path, int threadId, int pos) {

        //使用GreenDAO
        List<FileResult> list = instances.getDaoSession().getFileResultDao().queryBuilder().where(FileResultDao.Properties.Downpath.eq(path), FileResultDao.Properties.Threadid.eq(threadId)).build().list();

        for (FileResult fileResult : list) {
            Long id = fileResult.getId();
            instances.getDaoSession().getFileResultDao().update(new FileResult(id,path,threadId,pos));
        }

        //使用sqlite数据库
//		SQLiteDatabase db = openHelper.getWritableDatabase();
//		db.execSQL("update FILE_RESULT set DOWNLENGTH=? where DOWNPATH=? and THREADID=?",
//				new Object[]{pos, path, threadId});
//		db.close();

    }

    /**
     * 当文件下载完成后，删除对应的下载记录
     * @param path
     */
    @SuppressLint("LongLogTag")
    public void delete(String path) {
        //使用GreenDao数据库
        List<FileResult> list = instances.getDaoSession().getFileResultDao().queryBuilder().where(FileResultDao.Properties.Downpath.eq(path)).build().list();
        for (FileResult fileResult : list) {
            Long id = fileResult.getId();
            instances.getDaoSession().getFileResultDao().deleteByKey(id);
        }


        //使用sqlite数据库
//		SQLiteDatabase db = openHelper.getWritableDatabase();
//		db.execSQL("delete from FILE_RESULT where DOWNPATH=?",
//				new Object[] { path });
//		db.close();
    }

}
