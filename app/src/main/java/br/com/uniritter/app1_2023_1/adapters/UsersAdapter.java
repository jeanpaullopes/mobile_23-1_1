package br.com.uniritter.app1_2023_1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collection;
import java.util.List;

import br.com.uniritter.app1_2023_1.R;
import br.com.uniritter.app1_2023_1.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{
    private List<User> usersList;
    private int tipoLayout;
    public UsersAdapter(List<User> usersList) {
        this.usersList = usersList;
        //this.tipoLayout = tipo;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_user, parent, false);
        view.findViewById(R.id.button4).setOnClickListener((v)->{
            ((User)view.getTag()).getId();
            Toast.makeText(view.getContext(), ((User)view.getTag()).getName(), Toast.LENGTH_SHORT).show();
        });
        return new ViewHolder(view);
    }
    public void onChange() {
        this.notifyDataSetChanged();

    }
    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        User user = usersList.get(position);
        //guarda o objeto User no atributo Tag do ViewHolder
        holder.view.setTag(user);
        ((TextView) holder.view.findViewById(R.id.vhUserName)).setText(user.getName());
        ((TextView) holder.view.findViewById(R.id.vhEmail)).setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
