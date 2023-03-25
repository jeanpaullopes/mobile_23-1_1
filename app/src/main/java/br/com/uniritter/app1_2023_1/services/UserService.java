package br.com.uniritter.app1_2023_1.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.uniritter.app1_2023_1.models.Address;
import br.com.uniritter.app1_2023_1.models.User;
import br.com.uniritter.app1_2023_1.repositories.UserRepository;

public class UserService {

    //cria objeto User apartir de um JSON
    public static User userFromJson(JSONObject json) {
        User user = null;
        try {
            user = new User(
                    json.getInt("id"),
                    json.getString("name"),
                    json.getString("username"),
                    json.getString("email"));
            if (json.has("address")) {
                JSONObject jsonAdd = json.getJSONObject("address");
                JSONObject jsonGeo = jsonAdd.getJSONObject("geo");

                Address address = new Address("","","","",null);
                user.setAddress(address);
            }

        } catch (JSONException e) {
            System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
        }
        return user;
    }
    public static void getUser(Context contexto, int id, ServiceDone callback) {
    }
    //buscar todos os users no servidor REST
    public static void getAllUsers(Context contexto, ServiceDone callback) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users", null,
                response -> {
                    System.out.println("recebi o retorno!");
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject json = response.getJSONObject(i);
                             UserRepository.getInstance().addUser( userFromJson(json) );
                        } catch (JSONException e) {
                            System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
                        }
                    }
                    if (callback != null) {
                        callback.onServiceDone();
                    }
                },
                error->{
                    Toast.makeText(contexto, "Ocorreu uma falha na requisição "+error.getMessage(), Toast.LENGTH_LONG).show();
                });
        RequestQueue queue = Volley.newRequestQueue(contexto);
        System.out.println("antes de ir para a queue");
        queue.add(request);
        System.out.println("depois de ir para a queue");
    }

}
