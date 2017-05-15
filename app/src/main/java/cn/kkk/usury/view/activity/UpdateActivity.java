package cn.kkk.usury.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.kkk.usury.Application.I;
import cn.kkk.usury.Application.SharePreferenceUtils;
import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.User;
import cn.kkk.usury.utils.L;
import cn.kkk.usury.utils.MFGT;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UpdateActivity extends AppCompatActivity {
    private static final String TAG = "UpdateActivity";

    EditText mEtUserName, mEtPassword;
    Button mBtnSave;
    String name, password, access_token;
    LinearLayout mLayoutBack;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initData();
        initView();
        setListener();
    }

    private void initData() {
        SharePreferenceUtils.init(this);
        user_id = SharePreferenceUtils.getInstance().getId();
        access_token = SharePreferenceUtils.getInstance().getAccessToken();
    }

    private void setListener() {
        mLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.finish(UpdateActivity.this);
            }
        });
    }

    private void initView() {
        mEtUserName = (EditText) findViewById(R.id.et_update_user_name);
        mEtPassword = (EditText) findViewById(R.id.et_update_user_password);
        mLayoutBack = (LinearLayout) findViewById(R.id.layout_backArea);
    }

    public void onUpdateSave(View view) {
        // https://modelx.yuzhidushu.com/api/v1/user
        // Content-Type:application/json  Authorization:Bearer access_token
        // 参数 user_id name password
        if (checkInput()) {
           // 思路 requestBody request call
            RequestBody requestBody = new FormBody.Builder()
                    .add(I.Update.USER_ID, String.valueOf(user_id))
                    .add(I.Update.NAME, name)
                    .add(I.Update.PASSWORD, password)
                    .build();
            Request request = new Request.Builder()
                    .url(I.REQUEST_USER_UPDATE)
                    .addHeader("Content-Type","application/x-www-form-urlencoded")
                    .addHeader("Authorization", "Bearer "+access_token)
                    .put(requestBody)
                    .build();
            Call call = new OkHttpClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        L.e(TAG, "onUpdateSave, jsonObject = "+jsonObject);
                        if (jsonObject.getString("errmsg").equals("success")) {

//                            User user = UserUtils.getUserFromJson(jsonObject);
                            User user = new User();
                            JSONObject userObject = jsonObject.getJSONObject("data").getJSONObject("user");
                            user.setId(userObject.getInt("id"));
                            user.setName(userObject.getString("name"));
                            user.setTelephone(userObject.getString("telephone"));
                            L.e(TAG, "onUpdateSave, getUserFromJson, user ="+user);

//                            UserUtils.setUserToSharePreference(user, UpdateActivity.this);
                            // 使用SharePreferenceUtils给SharePreference的属性赋值
                            if (user!=null) {
                                SharePreferenceUtils.init(UpdateActivity.this);
                                SharePreferenceUtils.getInstance().setId(user.getId());
                                SharePreferenceUtils.getInstance().setTelephone(user.getTelephone());
                                SharePreferenceUtils.getInstance().setAccessToken(user.getAccess_token());
                                SharePreferenceUtils.getInstance().setName(user.getName());
                            }


                            L.e(TAG, "update 修改后的结果, telephone = "+SharePreferenceUtils.getInstance().getTelephone()+
                                    ", name = "+SharePreferenceUtils.getInstance().getName());

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        }
    }

    private boolean checkInput() {
        name = mEtUserName.getText().toString().trim();
        password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "修改信息不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
