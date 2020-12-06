package br.edu.ufam.icomp.stockskywalker.Locals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import br.edu.ufam.icomp.stockskywalker.Home.SignupLocalActivity;
import br.edu.ufam.icomp.stockskywalker.R;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardsClientFreeAdapter cardsClientFreeAdapter;
    private CardsClientRentedAdapter cardsClientRentedAdapter;
    private CardsAdminFreeAdapter cardsAdminFreeAdapter;
    private CardsAdminRentedAdapter cardsAdminRentedAdapter;
    private int idUser;
    private String typeRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        try{
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().hasExtra("type-render")){
            Bundle extras = getIntent().getExtras();

            typeRender = extras.getString("type-render");
            idUser = extras.getInt("id");
        }

        ImageButton buttonAdd = (ImageButton) findViewById(R.id.addNewLocal);
        if(typeRender.equals("client-available-locals")){
            cardsClientFreeAdapter = new CardsClientFreeAdapter(this, idUser);
            recyclerView.setAdapter(cardsClientFreeAdapter);
        }

        if(typeRender.equals("client-my-locals")){

            cardsClientRentedAdapter = new CardsClientRentedAdapter(this, idUser);
            recyclerView.setAdapter(cardsClientRentedAdapter);
        }

        if(typeRender.equals("admin-available-locals")){
            buttonAdd.setVisibility(View.VISIBLE);
            cardsAdminFreeAdapter = new CardsAdminFreeAdapter(this, idUser);
            recyclerView.setAdapter(cardsAdminFreeAdapter);

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleAddNewLocal();
                }
            });
        }

        if(typeRender.equals("admin-my-locals")){
            cardsAdminRentedAdapter = new CardsAdminRentedAdapter(this, idUser);
            recyclerView.setAdapter(cardsAdminRentedAdapter);
        }

    }

    @Override
    protected void onRestart(){
        super.onRestart();

        if(typeRender.equals("client-available-locals")){
            cardsClientFreeAdapter.update();
        }

        if(typeRender.equals("client-my-locals")){
            cardsClientRentedAdapter.update();
        }

        if(typeRender.equals("admin-available-locals")){
            cardsAdminFreeAdapter.update();
        }

        if(typeRender.equals("admin-my-locals")){
            cardsAdminRentedAdapter.update();
        }
    }

    private void handleAddNewLocal(){
        Intent intent = new Intent(ListActivity.this, SignupLocalActivity.class);
        intent.putExtra("type", "create");
        intent.putExtra("id-user", idUser);
        startActivity(intent);
    }
}