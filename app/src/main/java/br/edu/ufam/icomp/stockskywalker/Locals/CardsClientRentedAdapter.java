package br.edu.ufam.icomp.stockskywalker.Locals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.Local;
import br.edu.ufam.icomp.stockskywalker.storage.LocalDAO;
import br.edu.ufam.icomp.stockskywalker.storage.Rent;
import br.edu.ufam.icomp.stockskywalker.storage.RentDAO;

public class CardsClientRentedAdapter extends RecyclerView.Adapter<LocalViewHolder> {
    private Context context;
    private ArrayList<Local> locals;
    private int idUser;
    LocalDAO localDAO;
    RentDAO rentDAO;

    public CardsClientRentedAdapter(Context context, int idUser) {
        this.context = context;
        this.idUser = idUser;
        localDAO = new LocalDAO(context);
        rentDAO = new RentDAO(context);
        update();
    }

    public void update() { locals = localDAO.getLocalsRent(idUser); }

    public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        locals = localDAO.getLocalsRent(idUser);
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        LocalViewHolder localViewHolder = new LocalViewHolder(constraintLayout, context);
        return localViewHolder;
    }

    public void onBindViewHolder(LocalViewHolder holder, int position){
        Rent rent = rentDAO.getRent(locals.get(position).getIdLocal());
        holder.category.setText(locals.get(position).getCategory());
        holder.address.setText(locals.get(position).getAddress());
        holder.width.setText(locals.get(position).getWidth());
        holder.height.setText(locals.get(position).getHeight());
        holder.depth.setText(locals.get(position).getDepth());
        holder.price.setText(locals.get(position).getPrice());
        holder.startDate.setText(rent.getStartDate());
        holder.endDate.setText(rent.getEndDate());
        holder.button.setVisibility(View.INVISIBLE);

        String optional = "";
        if(rent.getSecurity()){
            optional += "S; ";
        }
        if(rent.getKeyExtra()){
            optional += "CE; ";
        }
        if(rent.getControlWeather()){
            optional += "CC; ";
        }

        holder.optional.setText(optional);
    }

    public int getItemCount() {return locals.size();}
}
