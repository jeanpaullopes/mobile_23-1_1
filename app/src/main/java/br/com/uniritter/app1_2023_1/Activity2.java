package br.com.uniritter.app1_2023_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.uniritter.app1_2023_1.models.User;

public class Activity2 extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {
    private EditText edit;
    private List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Button btn2_1 = findViewById(R.id.button2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        edit = findViewById(R.id.edName);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users/2", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User user = null;
                        try {
                            user = new User(
                            response.getInt("id"),
                            response.getString("name"),
                            response.getString("username"),
                            response.getString("email"));
                            edit.setText(user.getName());
                        } catch (JSONException e) {
                            System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
                        }

                        System.out.println("Chegou");


                    }
                },this);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        System.out.println("depois do add");
        System.out.println("ainda depois do add");
        Button btn = findViewById(R.id.btBuscaTodos);
        btn.setOnClickListener(view->{
            getAllUsers();
        });
    }
    private void getAllUsers() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users", null,
                this, this);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Ocorreu uma falha na requisição "+error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {

        for (int i = 0; i < response.length(); i++) {
            User user = null;
            try {
                JSONObject json = response.getJSONObject(i);

                user = new User(
                        json.getInt("id"),
                        json.getString("name"),
                        json.getString("username"),
                        json.getString("email"));
                users.add(user);
            } catch (JSONException e) {
                System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
            }
        }
        System.out.println(users);
    }
}