package br.com.uniritter.app1_2023_1.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.uniritter.app1_2023_1.models.User;

public class UserRepository {
    private Map<Integer, User> usersMap;

    //implementação do design Pattern Songleton
    private static UserRepository instance = null;


    // altera o construtor para private -> Singleton
    private UserRepository() {
        this.usersMap = new HashMap<>();
    }

    //implementação do Singleton
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }


    //inclui um obj User no Repositorio / Map
    public void addUser(User user) {
        //falta lançar uma exceção para tratamento -> throws
        if (user != null) {
            this.usersMap.put(user.getId(), user);
        }
    }

    // retorna se um obj User está presento no Mapa
    public boolean contains(User user) {
        return this.usersMap.containsValue(user);
    }

    /*
        Nome Método:
        Parametros: nome - objetivo
        Retoro: o que deve retornar

        comportamento:

     */
    // retorna se um obj está presente no Mapa por sei id
    public boolean contains(Integer id) {
        return this.usersMap.containsKey(id);
    }

    // retorna uma Collection com todos os usuários
    public Collection<User> getUsers() {
        return this.usersMap.values();
    }
}
