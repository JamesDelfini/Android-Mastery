package com.delfini.android_mastery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*

    Volley HTTP GET, POST, PUT, and DELETE Method Request Samples
    https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put

*/

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtRegister;
    Button btnLogin;
    EditText edtUsername, edtPassword;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("");

        queue = Volley.newRequestQueue(this);

        txtRegister = findViewById(R.id.signupTXT);
        btnLogin = findViewById(R.id.loginBTN);
        edtUsername = findViewById(R.id.usernameEDT);
        edtPassword = findViewById(R.id.passwordEDT);

        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBTN:
                final String url = "http://192.168.1.3:8080/api/users/login";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (!jsonObject.getBoolean("error")) {
                                        // Login Successful
                                        showToast(jsonObject.getString("message"));
                                        finish();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    } else {
                                        // Login Failed
                                        showToast(jsonObject.getString("message"));
                                    }
                                } catch (JSONException e) {
                                    showToast("JSON ERROR: " + e.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast(error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", edtUsername.getText().toString());
                        params.put("password", edtPassword.getText().toString());

                        return params;
                    }
                };

                queue.add(postRequest);
                break;
            case R.id.signupTXT:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
