package com.example.santi.primeraapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;
    EditText editTextPassword;
    EditText editTextEmail;
    static TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonLogin = findViewById(R.id.buttonLogin);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void validate() {
        EditText editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);


        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        editTextEmail.setError(null);
        editTextPassword.setError(null);
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(getString(R.string.error_field_required));
            editTextEmail.requestFocus();



        }
        if (TextUtils.isEmpty(password)){
            editTextPassword.setError(getString(R.string.error_field_required));
            editTextPassword.requestFocus();

            return;
        }
        //Toast.makeText(getApplicationContext(), "llego request", Toast.LENGTH_LONG).show();
        requestLogin();
    }

    private void requestLogin() {
        String url = "http://192.168.201.69:40000/pizzas/login";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            LoginActivity.textViewWelcome.setText(response.getString("message"));
                        } catch (JSONException e) {
                            LoginActivity.textViewWelcome.setText("Error de respuesta");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        // Access the RequestQueue through your singleton class.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
}
