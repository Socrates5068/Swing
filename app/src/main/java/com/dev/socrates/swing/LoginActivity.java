package com.dev.socrates.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.socrates.swing.connect.Link;
import com.dev.socrates.swing.connect.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private LoginActivity root = this;
    Button loginButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loadComponents();
    }

    private void loadComponents() {
        loginButton = this.findViewById(R.id.login);
        registerButton = this.findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(root, RegisterUser.class);
                root.startActivity(registerActivity);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Envio a la API
                AsyncHttpClient client = new AsyncHttpClient();

                EditText email = root.findViewById(R.id.email);
                EditText password = root.findViewById(R.id.password);

                RequestParams params = new RequestParams();
                params.add ("Correo", email.getText().toString());
                params.add ("Password", password.getText().toString());

                client.post(Link.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            UserData.MSN = response.getString("msn");
                            if (UserData.MSN.equals("Bienvenido al Sistema")) {
                                Toast.makeText(root, UserData.MSN, Toast.LENGTH_LONG).show();
                                Intent loginActivity = new Intent(root, MainMenuAdmin.class);
                                root.startActivity(loginActivity);
                            }
                            if (UserData.MSN.equals("Credenciales incorrectas")) {
                                Toast.makeText(root, UserData.MSN, Toast.LENGTH_LONG).show();
                            }

                            if (UserData.MSN.equals("El Correo es Necesario para logear")) {
                                Toast.makeText(root, UserData.MSN, Toast.LENGTH_LONG).show();
                            }

                            if (UserData.MSN.equals("Es necesario introducir su password")) {
                                Toast.makeText(root, UserData.MSN, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(root, UserData.MSN, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}