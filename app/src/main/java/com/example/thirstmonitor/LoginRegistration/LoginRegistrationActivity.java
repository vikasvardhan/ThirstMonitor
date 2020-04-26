package com.example.thirstmonitor.LoginRegistration;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thirstmonitor.Database.DrinkDataSource;
import com.example.thirstmonitor.MainWindow.MainActivity;
import com.example.thirstmonitor.R;
import com.example.thirstmonitor.Settings.PrefsHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

public class LoginRegistrationActivity extends AppCompatActivity {
    Button Login;
    ViewFlipper regVf;

    //Login
    EditText loginUName;
    EditText loginPwd;

    TextInputLayout loginUNameTxt;
    TextInputLayout loginPwdTxt;

    //Registration
    EditText regFullName;
    EditText regUName;
    EditText regPwd;
    EditText regCnfPwd;


    TextInputLayout regFullNameTxt;
    TextInputLayout regUnameTxt;
    TextInputLayout regPwdTxt;
    TextInputLayout regCnfTxt;

    private DrinkDataSource db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login_registration);

        Login = (Button) findViewById(R.id.button_submit);
        regVf = (ViewFlipper) findViewById(R.id.loginRegFlipper);
        db= new DrinkDataSource(this);
        db.open();

        //Login
        //InputFields
        loginUName = (EditText) findViewById(R.id.login_username);
        loginPwd = (EditText) findViewById(R.id.login_password);

        //lblFields
        loginUNameTxt = (TextInputLayout) findViewById(R.id.login_username_lbl);
        loginPwdTxt = (TextInputLayout) findViewById(R.id.login_password_lbl);

        //Registration
        //InputFields
        regFullName = (EditText) findViewById(R.id.register_full_name);
        regUName = (EditText) findViewById(R.id.register_username);
        regPwd = (EditText) findViewById(R.id.register_password);
        regCnfPwd = (EditText) findViewById(R.id.register_cnf_password);

        //lblFields
        regFullNameTxt = (TextInputLayout) findViewById(R.id.register_fullname_lbl);
        regUnameTxt = (TextInputLayout) findViewById(R.id.register_username_lbl);
        regPwdTxt = (TextInputLayout) findViewById(R.id.register_pwd_lbl);
        regCnfTxt = (TextInputLayout) findViewById(R.id.register_cnf_pwd_lbl);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginUName.length() != 0 || loginPwd.length() != 0) {
                    if (db.validateLogin(loginUName.getText().toString(), loginPwd.getText().toString())) {
                        Intent splashIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(splashIntent);
                        finish();
                    }else{
                        Snackbar snackbar = Snackbar.make(v, "Enter a valid username and password to login", 2000);
                        snackbar.show();
                    }
                }{
                    if(loginUName.length() == 0){
                        loginUNameTxt.setError("\u2022 User Name is Required!");
                    }
                    if(loginPwd.length() == 0){
                        loginPwdTxt.setError("\u2022 Password is Required!");
                    }
                }
            }
        });
    }

    public void register(View v){
        if(validate()){
            if(db.checkUser(regUName.getText().toString())) {
                if(db.createUserAccount(regFullName.getText().toString(),regUName.getText().toString(),regPwd.getText().toString())){
                    Snackbar snackbar = Snackbar.make(v, "User created successfully", 2000);
                    snackbar.show();
                    regVf.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
                    regVf.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
                    regVf.showPrevious();
                    PrefsHelper.setFirstTimeRunPrefs(getApplicationContext(),true);
                }
            }else{
                Snackbar snackbar = Snackbar.make(v, "User already exists", 2000);
                snackbar.show();
            }
        }
    }

    private boolean validate(){
        boolean valid = true;
        if(regFullName.getText().toString().length() == 0){
            valid = false;
            regFullNameTxt.setError("\u2022 Full Name of the User is Required");
        }
        else if(!regFullName.getText().toString().matches("^[a-zA-Z ]*$")){
            valid = false;
            regFullNameTxt.setError("\u2022 Name must contain only alphabets");
        }
        if(regUName.getText().toString().length() == 0){
            valid = false;
            regUnameTxt.setError("\u2022 Username is Required");
        }
        else if(!regUName.getText().toString().matches("^[a-z0-9A-Z]{6,15}$")){
            valid = false;
            regUnameTxt.setError("\u2022 User name must be alpha numeric");
            regUnameTxt.setError("\u2022 User name must be of range from 6 to 15 characters");
        }
        if(regPwd.getText().toString().length() == 0){
            valid = false;
            regPwdTxt.setError("\u2022 Password is Required");
        }
        else if(!regPwd.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            valid = false;
            regPwdTxt.setError("\u2022 Password must contain atleast 1 lowercase character");
            regPwdTxt.setError("\u2022 Password must contain atleast 1 uppercase character");
            regPwdTxt.setError("\u2022 Password must contain atleast 1 special character");
            regPwdTxt.setError("\u2022 Password must contain atleast 1 numeric character");
            regPwdTxt.setError("\u2022 Password must contain atleast 8 characters in length");
        }
        if(regCnfPwd.getText().toString().length() == 0){
            valid = false;
            regCnfTxt.setError("\u2022 Confirm Password is Required");
        }
        else if(!regCnfPwd.getText().toString().equals(regPwd.getText().toString())){
            valid = false;
            regCnfTxt.setError("\u2022 Confirm Password must match Password");
        }
        return valid;
    }
    public void directView(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
        regVf.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        regVf.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
        regVf.showNext();
    }

    public void prevView(View v) {

        regVf.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        regVf.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        regVf.showPrevious();
    }

    public void resView(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
        regVf.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        regVf.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
        regVf.showNext();
    }
}
