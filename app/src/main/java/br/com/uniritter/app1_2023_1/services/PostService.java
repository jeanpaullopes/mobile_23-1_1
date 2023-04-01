package br.com.uniritter.app1_2023_1.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.uniritter.app1_2023_1.models.Address;
import br.com.uniritter.app1_2023_1.models.Geo;
import br.com.uniritter.app1_2023_1.models.Post;
import br.com.uniritter.app1_2023_1.models.Post;
import br.com.uniritter.app1_2023_1.models.User;
import br.com.uniritter.app1_2023_1.repositories.PostRepository;
import br.com.uniritter.app1_2023_1.repositories.PostRepository;
import br.com.uniritter.app1_2023_1.repositories.UserRepository;

public class PostService {


    //cria objeto Post apartir de um JSON
    public static Post postFromJson(JSONObject json) {
        Post post = null;
        try {
            post = new Post(
                    json.getInt("id"),
                    json.getString("title"),
                    json.getString("body"),
                    null);
            User user  = UserRepository.getInstance().getUser( json.getInt("userId"));
            post.setUser(user);
        } catch (JSONException e) {
            System.out.println("erro no Json. Fogo no parquinho "+e.getMessage());
        }
        return post;
    }
    //buscar todos os posts no servidor REST
    public static void getAllPosts(Context contexto, ServiceDone callback) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts", null,
                response -> {
                    System.out.println("recebi o retorno!");
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject json = response.getJSONObject(i);
                             PostRepository.getInstance().addPost( postFromJson(json) );
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

    //buscar um post pelo id no servidor REST
    public static void getPost(Context contexto, int id, ServiceDone callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts/"+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Post post = null;
                        post = postFromJson( response);
                    }
                },error->{
            Toast.makeText(contexto, "Ocorreu uma falha na requisição "+error.getMessage(), Toast.LENGTH_LONG).show();
        });
        RequestQueue queue = Volley.newRequestQueue(contexto);
        queue.add(request);
    }

}
