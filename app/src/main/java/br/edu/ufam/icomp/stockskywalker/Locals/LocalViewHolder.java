package br.edu.ufam.icomp.stockskywalker.Locals;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ufam.icomp.stockskywalker.R;

public class LocalViewHolder extends RecyclerView.ViewHolder {
    public Context context;
    public TextView category, address, width, height, depth, price, fieldStartDate, startDate, fieldEndDate, endDate, fieldOptional, optional;
    public Button button;
    public int id;

    public LocalViewHolder(ConstraintLayout constraintLayout, Context context) {
        super(constraintLayout);
        this.context = context;

        category = constraintLayout.findViewById(R.id.categoryStock);
        address = constraintLayout.findViewById(R.id.addressStock);
        width = constraintLayout.findViewById(R.id.widthStock);
        height = constraintLayout.findViewById(R.id.heightStock);
        depth = constraintLayout.findViewById(R.id.depthStock);
        price = constraintLayout.findViewById(R.id.priceStock);
        fieldStartDate = constraintLayout.findViewById(R.id.fieldStartDateStock);
        startDate = constraintLayout.findViewById(R.id.startDateStock);
        endDate = constraintLayout.findViewById(R.id.endDateStock);
        optional = constraintLayout.findViewById(R.id.optionalStock);
        fieldEndDate = constraintLayout.findViewById(R.id.fieldEndDateStock);
        fieldOptional = constraintLayout.findViewById(R.id.fieldOptionalStock);
        button = constraintLayout.findViewById(R.id.buttonCard);
    }
}
