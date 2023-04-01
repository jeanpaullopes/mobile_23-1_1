package br.com.uniritter.app1_2023_1.presenters;

import android.util.Log;

import java.util.ArrayList;

import br.com.uniritter.app1_2023_1.adapters.UsersAdapter;
import br.com.uniritter.app1_2023_1.models.User;
import br.com.uniritter.app1_2023_1.repositories.PostRepository;
import br.com.uniritter.app1_2023_1.repositories.UserRepository;
import br.com.uniritter.app1_2023_1.services.PostService;
import br.com.uniritter.app1_2023_1.services.UserService;

public class UserPresenterLocal implements UserPresenterContract.Presenter{

    UserPresenterContract.View view;
    public UserPresenterLocal(UserPresenterContract.View view) {
        this.view = view;
    }

    @Override
    public void getAllUsers() {
        System.out.println("antes->"+ UserRepository.getInstance().getUsers());
        ArrayList<User> users = new ArrayList<User>();
        User a = new User(1, "Gabriela","gabi", "g@g.com.br");
        users.add(a);
        User b = new User(2, "Bruno","bruno", "b@b.com.br");
        users.add(b);
        UsersAdapter adapter = new UsersAdapter(users);
        view.setUsersAdapter(adapter);


    }
}
