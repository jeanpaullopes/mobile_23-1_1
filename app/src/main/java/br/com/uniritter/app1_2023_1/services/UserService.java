package br.com.uniritter.app1_2023_1.services;

import android.content.Context;
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

import java.util.List;

import br.com.uniritter.app1_2023_1.models.Address;
import br.com.uniritter.app1_2023_1.models.Geo;
import br.com.uniritter.app1_2023_1.models.User;
import br.com.uniritter.app1_2023_1.repositories.UserRepository;

public class UserService {

    // cria um objeto Geo de um JSON
    public static Geo geoFromJson(JSONObject json) {
        Geo geo = null;
        try {
          geo = new Geo( json.getDouble("lat"), json.getDouble("lng"));
        }  catch (JSONException e) {
            System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
        }
        return geo;
    }
    // cria objeto Address apartir de um JSON
    public static Address addressFromJson(JSONObject json) {
        Address address = null;
        try{
            address = new Address(json.getString("street"),
                    json.getString("suite"),
                    json.getString("suite"),
                    json.getString("zip"),null);
            if (json.has("geo")) {
                address.setGeo( geoFromJson( json.getJSONObject("geo")));
            }
        }  catch (JSONException e) {
        System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
    }

        return address;
    }
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
                Address address = addressFromJson( jsonAdd);
                user.setAddress(address);
                // ou esta linha que substitui as 3 linhas anteriores
                // ---> user.setAddress(addressFromJson( json.getJSONObject("address") ));

                //JSONObject jsonGeo = jsonAdd.getJSONObject("geo");
                //Address address = new Address("","","","",null);
                //user.setAddress(address);


            }

        } catch (JSONException e) {
            System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
        }
        return user;
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

    //buscar um user pelo id no servidor REST
    public static void getUser(Context contexto, int id, ServiceDone callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users/"+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User user = null;
                        user = userFromJson( response);
                    }
                },error->{
            Toast.makeText(contexto, "Ocorreu uma falha na requisição "+error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(contexto);
        queue.add(request);
    }

}
