package br.edu.ufam.icomp.stockskywalker.Locals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.ufam.icomp.stockskywalker.Home.SignupLocalActivity;
import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.Local;
import br.edu.ufam.icomp.stockskywalker.storage.LocalDAO;

public class CardsAdminFreeAdapter extends RecyclerView.Adapter<LocalViewHolder> {
    private Context context;
    private ArrayList<Local> locals;
    private int idUser;
    LocalDAO localDAO;

    public CardsAdminFreeAdapter(Context context, int idUser) {
        this.context = context;
        this.idUser = idUser;
        localDAO = new LocalDAO(context);
        update();
    }

    public void update() {
        locals = localDAO.getLocalByAdmin(idUser);
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

        holder.button.setText("ATUALIZAR");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUpdateLocal(
                        locals.get(position).getIdLocal(),
                        locals.get(position).getCategory(),
                        locals.get(position).getAddress(),
                        locals.get(position).getWidthInt(),
                        locals.get(position).getHeightInt(),
                        locals.get(position).getDepthInt(),
                        locals.get(position).getPriceInt()
                );
            }
        });
    }

    public int getItemCount() {return locals.size();}

    public void handleUpdateLocal(int idLocal, String category,String address, int width, int height, int depth, int price){
        Intent intent = new Intent(context, SignupLocalActivity.class);
        intent.putExtra("type", "update");
        intent.putExtra("id-user", idUser);
        intent.putExtra("id-local", idLocal);
        intent.putExtra("category", category);
        intent.putExtra("address", address);
        intent.putExtra("width", width);
        intent.putExtra("height", height);
        intent.putExtra("depth", depth);
        intent.putExtra("price", price);
        context.startActivity(intent);
    }
}
