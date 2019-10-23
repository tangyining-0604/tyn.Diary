package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class EditTextActivity extends AppCompatActivity {
    private EditText mEtUserName,mEtPassword;
    private CheckBox mCbCheckbox;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        sharedPreferences = getSharedPreferences("remenberpass", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean Remenber = sharedPreferences.getBoolean("remenberpass", false);
        if (Remenber) {
            String  name = sharedPreferences.getString("name","");
            String pass = sharedPreferences.getString("pass", "");
            mEtUserName.setText(name);
            mEtPassword.setText(pass);
            mCbCheckbox.setChecked(true);
        }
    }
    private void initViews() {
        mEtUserName = findViewById(R.id.et_1);
        mEtPassword = findViewById(R.id.et_2);
        mCbCheckbox= findViewById(R.id.checkbox);
    }
            public void login(View v) {
                String name = mEtUserName.getText().toString();
                String password = mEtPassword.getText().toString();
                if(name.length() == 0){
                    Toast.makeText(EditTextActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(name.equals("12345")&&password.equals("123456")){
                        SharedPreferences.Editor editor = sharedPreferences.edit();;
                        if (mCbCheckbox.isChecked()) {
                            editor.putBoolean("remenberpass", true);
                            editor.putString("name", name);
                            editor.putString("pass", password);
                        } else {
                            editor.clear();
                        }
                    editor.commit();
                    Intent intent=new Intent(EditTextActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                    }
                else {
                    Toast.makeText(EditTextActivity.this,"账号或密码有错",Toast.LENGTH_SHORT).show();
                }
            }

    }

