package com.example.aman.offercart_v1.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;

import butterknife.ButterKnife;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.example.aman.offercart_v1.helper.SharedPrefs;

import com.example.aman.offercart_v1.login.presenter.LoginScreenPresenter;
import com.example.aman.offercart_v1.verify_otp.view.OtpViewImpl;
import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.login.models.RetrofitLoginScreenProvider;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;
import com.example.aman.offercart_v1.login.presenter.LoginScreenPresenterImpl;


/**
 * Created by aman on 15/10/16.
 */
public class LoginScreenActivity extends Activity implements LoginScreenView {
    String email1;
    String name1;
    EditText name;
    Button login_button;
    EditText mobile;
    String mobile1;
    EditText email;
    private SharedPrefs sharedPrefs;
    private String access_token;
    private ProgressBar progressbar;
    private RetrofitLoginScreenProvider retrofitLoginScreenProvider;
    private LoginScreenPresenter loginScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        Log.d("Response","1");
          sharedPrefs=new SharedPrefs(this);
        access_token=sharedPrefs.getAccessToken();
        progressbar=(ProgressBar)findViewById(R.id.progressBar);
        Log.d("Response","2");

        login_button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        mobile = (EditText) findViewById(R.id.editText2);
        Log.d("Response","3");
        email = (EditText) findViewById(R.id.editText3);

        ButterKnife.bind(this);
        Log.d("Response","4");

        loginScreenPresenter =new LoginScreenPresenterImpl(this,
                new RetrofitLoginScreenProvider());

        Log.d("Response","5");
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name1 = name.getText().toString();
                mobile1 = mobile.getText().toString();
                 email1 = email.getText().toString();
                Log.d("Response","b1");
                if(name1.equals("") || name1.equals(null)){
                    name.setError("Please fill name");
                    name.requestFocus();
                }

            else if(mobile1.equals("") || mobile1.equals(null)){
                    mobile.setError("Please fill mobile");
                    mobile.requestFocus();

                }else if(email1.equals("") || email1.equals("")){
                    email.setError("Please fill email id ");
                    email.requestFocus();
                }

                else if(mobile1.length()!=10) {
                    mobile.setError("Invalid Mobile No.");
                    mobile.requestFocus();
                }
                else if(!validate(email1)){
                    email.setError("Invalid Email Address");
                    email.requestFocus();


                }

                if((name1.equals("") || name1.equals(null)) ||
                        ((mobile1.equals("") || mobile1.equals(null)) || mobile1.length()!=10)||
                        (email1.equals("") || email1.equals("")
                        ||!validate(email1) )

                        )

                {


                }
                else{
                    loginScreenPresenter.requestLogin(access_token,name1, mobile1, email1);
                }




                Log.d("Response","b2");
            }
        });
        Log.d("Response","6");


    }


    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressbar.setVisibility(View.VISIBLE);
        } else {
            progressbar.setVisibility(View.INVISIBLE);
        }
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginVerified() {
        sharedPrefs=new SharedPrefs(this);
        sharedPrefs.setLogin(true);
        Intent in=new Intent(LoginScreenActivity.this, OtpViewImpl.class);
        in.putExtra("mobile",mobile1);
        startActivity(in);

    }
}