package com.app.assignmenttwo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity_LogIn extends AppCompatActivity {

    EditText editTextEmail,editTextPassword;
    TextView textViewSignIn;
    RelativeLayout relativeLayoutMain;
    AnimationDrawable animationDrawable;
    Snackbar snackbar;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+",stringId = "";
    DatabaseHelper db = new DatabaseHelper(Activity_LogIn.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relativeLayoutMain = (RelativeLayout)findViewById(R.id.mainlayout);
        editTextEmail = (EditText)findViewById(R.id.et_email);
        editTextPassword = (EditText)findViewById(R.id.et_pwd);
        textViewSignIn = (TextView)findViewById(R.id.tv_signin);

        animationDrawable =(AnimationDrawable)relativeLayoutMain.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;

                if (editTextEmail.getText().equals("")){
                    setSnackBarNegativeMsg("Enter Email / Mobile !!");
                    count ++;
                }if (editTextPassword.getText().equals("")){
                    setSnackBarNegativeMsg("Enter Valid Password !!");
                    count ++;
                }

                if (count == 0){
                    stringId = db.getLoginID(editTextEmail.getText().toString(),editTextPassword.getText().toString());

                    if (stringId.equals("")) {
                        setSnackBarNegativeMsg("Something went wrong Please re-enter the details !!");
                    }else {
                        setSnackBarNegativeMsg("Successful Login");
                        snackbar.addCallback(new Snackbar.Callback() {

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                //see Snackbar.Callback docs for event details
                                Intent intent = new Intent(Activity_LogIn.this, MainActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onShown(Snackbar snackbar) {
                            }
                        });
                    }
                }
            }
        });
    }

    public void setSnackBarNegativeMsg(String Msg){
        String msg = Msg;
        snackbar = Snackbar.make(relativeLayoutMain, msg, Snackbar.LENGTH_SHORT);
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
