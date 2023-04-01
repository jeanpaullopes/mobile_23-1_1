package br.com.uniritter.app1_2023_1.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.uniritter.app1_2023_1.R;
//import br.com.uniritter.app1_2023_1.models.User;
import br.com.uniritter.app1_2023_1.presenters.UserPresenter;
import br.com.uniritter.app1_2023_1.presenters.UserPresenterContract;
import br.com.uniritter.app1_2023_1.presenters.UserPresenterLocal;

public class Activity2 extends AppCompatActivity implements UserPresenterContract.View {
    private EditText edit;
    //private List<User> users = new ArrayList<>();
    private RecyclerView rv;
    LinearLayoutManager llm;
    LinearLayoutManager llmh;
    private UserPresenterContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //presenter = new UserPresenter(this);
        presenter = new UserPresenterLocal(this);
        llm = new LinearLayoutManager(this);
        llmh = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        rv = findViewById(R.id.usersRV);
        rv.setLayoutManager(llm);

        Button btn2_1 = findViewById(R.id.button2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button3).setOnClickListener((v) -> {
            /*
            if (rv.getLayoutManager() == llm) {
                rv.setLayoutManager(llmh);
            } else {
                rv.setLayoutManager(llm);
            }
            */
            // troca o presenter da Activity
            // na pratica está trocando todo o comportamento da retaguarda
            // e o comportamento da UI se mantem
            if (presenter instanceof UserPresenter) {
                presenter = new UserPresenterLocal(this);

            } else {
                presenter = new UserPresenter(this);

            }
            presenter.getAllUsers();
        });
        edit = findViewById(R.id.edName);

        System.out.println("depois do add");
        System.out.println("ainda depois do add");
        Button btn = findViewById(R.id.btBuscaTodos);
        btn.setOnClickListener(view->{
            presenter.getAllUsers();
        });
    }

    //movido para o presenter
    /*
    private void getAllUsers() {

        System.out.println("antes->"+UserRepository.getInstance().getUsers());

        UserService.getAllUsers(this, ()->{
            // movi para o método da Interface view do UserPresenterContract
            //UsersAdapter adapter = new UsersAdapter(new ArrayList(UserRepository.getInstance().getUsers()));

            //rv.setAdapter(adapter);

            PostService.getAllPosts(this, ()->{
                Log.d("PgetPosts", PostRepository.getInstance().getPost(1).toString() );
                ;
            });
        });

        ;

    }
     */

    @Override
    public void setUsersAdapter(RecyclerView.Adapter adapter) {
        rv.setAdapter(adapter);
    }

    @Override
    public Context getContexto() {
        return this.getApplicationContext();
    }
}