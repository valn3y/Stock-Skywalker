package br.edu.ufam.icomp.stockskywalker.Locals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.ufam.icomp.stockskywalker.Home.RentActivity;
import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.Local;
import br.edu.ufam.icomp.stockskywalker.storage.LocalDAO;

public class CardsClientFreeAdapter extends RecyclerView.Adapter<LocalViewHolder> {
    private Context context;
    private ArrayList<Local> locals;
    private int idUser;
    LocalDAO localDAO;

    public CardsClientFreeAdapter(Context context, int idUser) {
        this.context = context;
        this.idUser = idUser;
        localDAO = new LocalDAO(context);
        update();
    }

    public void update() {
        locals = localDAO.getLocals();
    }

    public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        LocalViewHolder localViewHolder = new LocalViewHolder(constraintLayout, context);
        return localViewHolder;
    }

    public void onBindViewHolder(LocalViewHolder holder, int position){
        holder.category.setText(locals.get(position).getCategory());
        holder.address.setText(locals.get(position).getAddress());
        holder.width.setText(locals.get(position).getWidth());
        holder.height.setText(locals.get(position).getHeight());
        holder.depth.setText(locals.get(position).getDepth());
        holder.price.setText(locals.get(position).getPrice());
        holder.fieldStartDate.setVisibility(View.INVISIBLE);
        holder.startDate.setVisibility(View.INVISIBLE);
        holder.fieldEndDate.setVisibility(View.INVISIBLE);
        holder.endDate.setVisibility(View.INVISIBLE);
        holder.fieldOptional.setVisibility(View.INVISIBLE);
        holder.optional.setVisibility(View.INVISIBLE);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleMakeRent(locals.get(position).getIdLocal());
            }
        });
    }

    public int getItemCount() {return locals.size();}

    public void handleMakeRent(int idLocal){
        Intent intent = new Intent(context, RentActivity.class);
        intent.putExtra("id-local", idLocal);
        intent.putExtra("id-user", idUser);
        context.startActivity(intent);
    }
}
