package com.dingtao.common.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dingtao.common.bean.login.LoginBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOGIN_BEAN".
*/
public class LoginBeanDao extends AbstractDao<LoginBean, String> {

    public static final String TABLENAME = "LOGIN_BEAN";

    /**
     * Properties of entity LoginBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property SessionId = new Property(1, String.class, "sessionId", false, "SESSION_ID");
        public final static Property Age = new Property(2, String.class, "age", false, "AGE");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property HeadPic = new Property(4, String.class, "headPic", false, "HEAD_PIC");
        public final static Property InvitationCode = new Property(5, String.class, "invitationCode", false, "INVITATION_CODE");
        public final static Property JiGuangPwd = new Property(6, String.class, "jiGuangPwd", false, "JI_GUANG_PWD");
        public final static Property NickName = new Property(7, String.class, "nickName", false, "NICK_NAME");
        public final static Property Sex = new Property(8, String.class, "sex", false, "SEX");
        public final static Property UserName = new Property(9, String.class, "userName", false, "USER_NAME");
        public final static Property Weight = new Property(10, String.class, "weight", false, "WEIGHT");
        public final static Property WhetherBingWeChat = new Property(11, int.class, "whetherBingWeChat", false, "WHETHER_BING_WE_CHAT");
        public final static Property Ttt = new Property(12, int.class, "ttt", false, "TTT");
    }


    public LoginBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LoginBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_BEAN\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"SESSION_ID\" TEXT," + // 1: sessionId
                "\"AGE\" TEXT," + // 2: age
                "\"EMAIL\" TEXT," + // 3: email
                "\"HEAD_PIC\" TEXT," + // 4: headPic
                "\"INVITATION_CODE\" TEXT," + // 5: invitationCode
                "\"JI_GUANG_PWD\" TEXT," + // 6: jiGuangPwd
                "\"NICK_NAME\" TEXT," + // 7: nickName
                "\"SEX\" TEXT," + // 8: sex
                "\"USER_NAME\" TEXT," + // 9: userName
                "\"WEIGHT\" TEXT," + // 10: weight
                "\"WHETHER_BING_WE_CHAT\" INTEGER NOT NULL ," + // 11: whetherBingWeChat
                "\"TTT\" INTEGER NOT NULL );"); // 12: ttt
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginBean entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(2, sessionId);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(3, age);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(5, headPic);
        }
 
        String invitationCode = entity.getInvitationCode();
        if (invitationCode != null) {
            stmt.bindString(6, invitationCode);
        }
 
        String jiGuangPwd = entity.getJiGuangPwd();
        if (jiGuangPwd != null) {
            stmt.bindString(7, jiGuangPwd);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(8, nickName);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(9, sex);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(10, userName);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(11, weight);
        }
        stmt.bindLong(12, entity.getWhetherBingWeChat());
        stmt.bindLong(13, entity.getTtt());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginBean entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(2, sessionId);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(3, age);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(5, headPic);
        }
 
        String invitationCode = entity.getInvitationCode();
        if (invitationCode != null) {
            stmt.bindString(6, invitationCode);
        }
 
        String jiGuangPwd = entity.getJiGuangPwd();
        if (jiGuangPwd != null) {
            stmt.bindString(7, jiGuangPwd);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(8, nickName);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(9, sex);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(10, userName);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(11, weight);
        }
        stmt.bindLong(12, entity.getWhetherBingWeChat());
        stmt.bindLong(13, entity.getTtt());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public LoginBean readEntity(Cursor cursor, int offset) {
        LoginBean entity = new LoginBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sessionId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // age
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // email
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // headPic
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // invitationCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // jiGuangPwd
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // nickName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // sex
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // userName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // weight
            cursor.getInt(offset + 11), // whetherBingWeChat
            cursor.getInt(offset + 12) // ttt
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSessionId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAge(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHeadPic(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setInvitationCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setJiGuangPwd(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNickName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSex(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUserName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWeight(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setWhetherBingWeChat(cursor.getInt(offset + 11));
        entity.setTtt(cursor.getInt(offset + 12));
     }
    
    @Override
    protected final String updateKeyAfterInsert(LoginBean entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(LoginBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LoginBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
