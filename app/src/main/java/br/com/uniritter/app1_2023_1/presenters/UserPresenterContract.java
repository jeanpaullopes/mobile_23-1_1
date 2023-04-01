package br.com.uniritter.app1_2023_1.presenters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface UserPresenterContract {
    interface View {
        public void setUsersAdapter(RecyclerView.Adapter adapter);
        public Context getContexto();

    }
    interface Presenter {

        public void getAllUsers();

    }
}
