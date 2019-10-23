package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class EditTextActivity extends AppCompatActivity {
    private EditText mEtUserName,mEtPassword;
    private CheckBox mCbCheckbox;
    private Button button;
    private int remeberElag=0;
    private String password = "";
    private String name;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        mEtUserName=findViewById(R.id.et_1);
        mEtPassword=findViewById(R.id.et_2);
        mCbCheckbox=findViewById(R.id.checkbox);
        button=findViewById(R.id.btn_login);
        sharedPreferences = getSharedPreferences(MyOrders.REMEMBER_PASS_DATABASE, Context.MODE_PRIVATE);
        remeberElag= sharedPreferences.getInt("remeber_flag", -1);
        if (remeberElag == 1) {
            name = sharedPreferences.getString("name", "");
            password = sharedPreferences.getString("password", "");
            mCbCheckbox.setChecked(true);
            mEtPassword.setText(password);
            mEtUserName.setText(name);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEtUserName.getText().toString();
                String password = mEtPassword.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", username);
                if(username.equals("123") && password.equals("123")) {
                    if (mCbCheckbox.isChecked()) {
                        Log.d("测试","执行");
                        remeberElag = 1;
                        editor.putInt("remeber_flag", remeberElag);
                        editor.putString("password", password);
                        editor.putString("name",username);
                    } else {
                        editor.clear();
                        remeberElag = 0;
                        editor.putInt("remeber_flag", remeberElag);
                    }
                    editor.commit();
                    Toast.makeText(EditTextActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(EditTextActivity.this,Main2Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(EditTextActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}



