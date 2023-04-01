package br.com.uniritter.app1_2023_1.presenters;

import android.util.Log;

import java.util.ArrayList;

import br.com.uniritter.app1_2023_1.adapters.UsersAdapter;
import br.com.uniritter.app1_2023_1.repositories.PostRepository;
import br.com.uniritter.app1_2023_1.repositories.UserRepository;
import br.com.uniritter.app1_2023_1.services.PostService;
import br.com.uniritter.app1_2023_1.services.UserService;

public class UserPresenter implements UserPresenterContract.Presenter{

    UserPresenterContract.View view;
    public UserPresenter(UserPresenterContract.View view) {
        this.view = view;
    }

    @Override
    public void getAllUsers() {
        System.out.println("antes->"+ UserRepository.getInstance().getUsers());

        UserService.getAllUsers(view.getContexto(), ()->{
            UsersAdapter adapter = new UsersAdapter(new ArrayList(UserRepository.getInstance().getUsers()));
            view.setUsersAdapter(adapter);

            PostService.getAllPosts(view.getContexto(), ()->{
                Log.d("getAllPosts", PostRepository.getInstance().getPost(1).toString() );
                ;
            });
        });
    }
}
