package cn.kkk.usury.utils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.kkk.usury.model.bean.User;

/**
 * Created by apple on 2017/5/11.
 */

public class UserUtils {
    static User mUser = new User();

    public static User getUserFromJson(JSONObject jsonObject) {
        JSONObject userObject = null;
        try {
            userObject = jsonObject.getJSONObject("data").getJSONObject("user");
            mUser.setId(userObject.getInt("id"));
            mUser.setName(userObject.getString("name"));
            mUser.setTelephone(userObject.getString("telephone"));
            mUser.setEmail(userObject.getString("email"));
            mUser.setCreated_at(userObject.getString("created_at"));
            mUser.setUpdated_at(userObject.getString("updated_at"));
            mUser.setMac_uuid(userObject.getString("mac_uuid"));
            mUser.setAccess_token(userObject.getString("access_token"));
            return  mUser;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}