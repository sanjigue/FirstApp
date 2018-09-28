package com.example.santi.primeraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class PizzaMainActivity extends AppCompatActivity {

    Button buttonPizzaBbq;
    Button buttonPizzaHawai;
    Button buttonPizzaCheese;
    Button buttonPizzaBbqChicken;

   // TextView textViewPizzaBbq;
    TextView textPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_main);



        //textPrueba =  findViewById(R.id.textPrueba);

        buttonPizzaBbq =  findViewById(R.id.buttonPizzaBbq);
        buttonPizzaHawai =  findViewById(R.id.buttonPizzaHawai);
        buttonPizzaCheese =  findViewById(R.id.buttonPizzaCheese);
        buttonPizzaBbqChicken =  findViewById(R.id.buttonPizzaChicken);

        buttonPizzaBbq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameButton = buttonPizzaBbq.getText().toString();


                Intent intent = new Intent(getApplicationContext(), PizzaBbqActivity.class);
                startActivity(intent);

                requestPizza(nameButton);
            }
        });

        buttonPizzaHawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameButton = buttonPizzaHawai.getText().toString();


                Intent intent = new Intent(getApplicationContext(), PizzaBbqActivity.class);
                startActivity(intent);

                requestPizza(nameButton);
            }
        });

        buttonPizzaCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameButton = buttonPizzaCheese.getText().toString();


                Intent intent = new Intent(getApplicationContext(), PizzaBbqActivity.class);
                startActivity(intent);

                requestPizza(nameButton);
            }
        });

        buttonPizzaBbqChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameButton = buttonPizzaBbqChicken.getText().toString();


                Intent intent = new Intent(getApplicationContext(), PizzaBbqActivity.class);
                startActivity(intent);

                requestPizza(nameButton);
            }
        });
    }

    private void requestPizza(String pizzaName) {
        String url = "http://192.168.201.69:40000/pizzas/" + pizzaName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            PizzaBbqActivity.textViewPizzaBbq.setText(response.getString("message"));
                        } catch (JSONException e) {
                            PizzaBbqActivity.textViewPizzaBbq.setText("Error de respuesta");
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

    private static final int MNU_OPC1 = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Login")
                .setIcon(R.mipmap.ic_login).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MNU_OPC1:
                //Toast.makeText(getApplicationContext(), "Funcionó", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
