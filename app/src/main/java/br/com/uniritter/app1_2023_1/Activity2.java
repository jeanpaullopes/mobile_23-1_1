package br.com.uniritter.app1_2023_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import br.com.uniritter.app1_2023_1.adapters.UsersAdapter;
import br.com.uniritter.app1_2023_1.models.User;
import br.com.uniritter.app1_2023_1.repositories.UserRepository;
import br.com.uniritter.app1_2023_1.services.ServiceDone;
import br.com.uniritter.app1_2023_1.services.UserService;

public class Activity2 extends AppCompatActivity  {
    private EditText edit;
    private List<User> users = new ArrayList<>();
    private RecyclerView rv;
    LinearLayoutManager llm;
    LinearLayoutManager llmh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        llm = new LinearLayoutManager(this);
        llmh = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        rv = findViewById(R.id.usersRV);

        Button btn2_1 = findViewById(R.id.button2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button3).setOnClickListener((v) -> {
            if (rv.getLayoutManager() == llm) {
                rv.setLayoutManager(llmh);
            } else {
                rv.setLayoutManager(llm);
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
                },error->{
            Toast.makeText(this, "Ocorreu uma falha na requisição "+error.getMessage(), Toast.LENGTH_LONG).show();
        });
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
        System.out.println("antes->"+UserRepository.getInstance().getUsers());

        UserService.getAllUsers(this, ()->{

            UsersAdapter adapter = new UsersAdapter(new ArrayList(UserRepository.getInstance().getUsers()));
            rv.setLayoutManager(llmh);
            rv.setAdapter(adapter);
        });

        ;

    }



}