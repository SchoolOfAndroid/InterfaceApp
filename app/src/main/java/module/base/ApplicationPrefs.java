package module.base;


import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by ominfowave on 2/6/15.
 */

public class ApplicationPrefs {

    private static ApplicationPrefs prefsT;
    protected Context context;
    public static final String PREF_NAME = "AndroidChat";
    private static final String USER_ID = "userId";
    private static final String NICK_NAME = "nickname";
    private static final String DEVICE_ID = "device_id";


    public ApplicationPrefs(Context context) {
        this.context = context;
    }


    public static ApplicationPrefs getInstance(Context context) {
        if (prefsT == null) {
            prefsT = new ApplicationPrefs(context);
        }
        return prefsT;
    }


    public void clearData() {
        SharedPreferences.Editor editor = getPrefsEditor();
        editor.clear();
        editor.commit();
    }


    public void setUserId(String userId) {
        SharedPreferences.Editor editor = getPrefsEditor();
        editor.putString(USER_ID, userId);
        editor.commit();
    }


    public String getUserId() {
        return getPrefs().getString(USER_ID, null);
    }


    public boolean isLogin() {
        return getUserId() != null && getUserId().toString().trim().length() > 0;
    }


    public void setNickName(String nickName) {
        SharedPreferences.Editor editor = getPrefsEditor();
        editor.putString(NICK_NAME, nickName);
        editor.commit();
    }


    public String getNickName() {
        return getPrefs().getString(NICK_NAME, null);
    }


    public String getDeviceid() {
        return getPrefs().getString(DEVICE_ID, null);
    }


    public void setDeviceId(String deviceId) {
        SharedPreferences.Editor editor = getPrefsEditor();
        editor.putString(DEVICE_ID, deviceId);
        editor.commit();
    }


    public void clearUser() {
        setUserId(null);
        setNickName(null);
    }


    public void clear() {
    }


    protected SharedPreferences getPrefs() {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    protected SharedPreferences.Editor getPrefsEditor() {
        return getPrefs().edit();
    }


}
