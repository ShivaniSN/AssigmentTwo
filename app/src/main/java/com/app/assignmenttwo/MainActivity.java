package com.app.assignmenttwo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayoutMain;
    AnimationDrawable animationDrawable;
    EditText editTextName,editTextMobileNumber,editTextEmail,editTextPassword;
    TextView textViewSignUp;
    Snackbar snackbar;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseHelper db = new DatabaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayoutMain = (ConstraintLayout)findViewById(R.id.mainlayout);
        editTextName = (EditText)findViewById(R.id.et_name);
        editTextMobileNumber = (EditText)findViewById(R.id.et_mobile);
        editTextEmail = (EditText)findViewById(R.id.et_email);
        editTextPassword = (EditText)findViewById(R.id.et_pwd);
        textViewSignUp = (TextView)findViewById(R.id.tv_signup);

        animationDrawable =(AnimationDrawable)constraintLayoutMain.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;

                if (editTextName.getText().equals("")){
                    setSnackBarNegativeMsg("Enter Name !!");
                    count ++;
                }if (editTextMobileNumber.getText().length() != 10){
                    setSnackBarNegativeMsg("Enter Valid Number !!");
                    count ++;
                }if (editTextEmail.getText().equals("") || !editTextEmail.getText().toString().matches(emailPattern)){
                    setSnackBarNegativeMsg("Enter Valid Email !!");
                    count ++;
                }if (editTextPassword.getText().equals("")){
                    setSnackBarNegativeMsg("Enter Password !!");
                    count ++;
                }

                if (count == 0){
                    db.createLogin(new List_Login(editTextName.getText().toString(),editTextMobileNumber.getText().toString(),
                            editTextEmail.getText().toString(),editTextPassword.getText().toString()));

                    setSnackBarNegativeMsg("Data Saved");

                    Intent intent = new Intent(MainActivity.this, Activity_LogIn.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_to_right,
                            R.anim.right_to_left);
                }
            }
        });
    }

    public void setSnackBarNegativeMsg(String Msg){
        String msg = Msg;
        snackbar = Snackbar.make(constraintLayoutMain, msg, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
        TextView mainTextView = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        mainTextView.setTextColor(Color.BLACK);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        animationDrawable.start();
    }
}