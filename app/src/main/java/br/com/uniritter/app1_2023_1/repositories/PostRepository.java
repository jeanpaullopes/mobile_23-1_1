package br.com.uniritter.app1_2023_1.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.uniritter.app1_2023_1.models.Post;


public class PostRepository {
    private Map<Integer, Post> postsMap;

    //implementação do design Pattern Songleton
    private static PostRepository instance = null;


    // altera o construtor para private -> Singleton
    private PostRepository() {
        this.postsMap = new HashMap<>();
    }

    //implementação do Singleton
    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }


    //inclui um obj post no Repositorio / Map
    public void addPost(Post post) {
        //falta lançar uma exceção para tratamento -> throws
        if (post != null) {
            this.postsMap.put(post.getId(), post);
        }
    }

    // retorna se um obj post está presento no Mapa
    public boolean contains(Post post) {
        return this.postsMap.containsValue(post);
    }

    /*
        Nome Método:
        Parametros: nome - objetivo
        Retoro: o que deve retornar

        comportamento:

     */
    // retorna se um obj está presente no Mapa por sei id
    public boolean contains(Integer id) {
        return this.postsMap.containsKey(id);
    }

    // retorna uma Collection com todos os Post
    public Collection<Post> getPosts() {
        return this.postsMap.values();
    }

    // retorna uma Collection com todos os posts de 1 usuário
    public Collection<Post> getPostsUser(int userId) {
        return this.postsMap.values();
    }
    public Collection<Post> getPostsUserArray(int userId) {
        return this.postsMap.values().stream().filter((p)->p.getUser().getId() == userId).collect(Collectors.toList());
    }

    public Post getPost(int id) {
        return this.postsMap.get(id);
    }
}
