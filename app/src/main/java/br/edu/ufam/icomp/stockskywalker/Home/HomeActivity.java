package br.edu.ufam.icomp.stockskywalker.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import br.edu.ufam.icomp.stockskywalker.Locals.ListActivity;
import br.edu.ufam.icomp.stockskywalker.Login.LoginActivity;
import br.edu.ufam.icomp.stockskywalker.R;

public class HomeActivity extends AppCompatActivity {
    private int idUser;
    private String nameUser;
    private String typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try{
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        setContentView(R.layout.activity_home);

        if(getIntent().hasExtra("id")){
            Bundle extras = getIntent().getExtras();
            idUser = extras.getInt("id");
            nameUser = extras.getString("name");
            typeUser = extras.getString("type-user");
        }

        TextView helloUser = (TextView) findViewById(R.id.helloUser);
        helloUser.setText("Olá, " + nameUser + "!");

        ImageButton logout = (ImageButton) findViewById(R.id.buttonLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        Button placesFree = (Button) findViewById(R.id.btnPlacesFree);
        placesFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeUser.equals("client")){
                    openListLocal("client-available-locals");
                }
                if(typeUser.equals("admin")){
                    openListLocal("admin-available-locals");
                }
            }
        });

        Button placesRent = (Button) findViewById(R.id.btnPlacesRent);
        placesRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeUser.equals("client")){
                    openListLocal("client-my-locals");
                }
                if(typeUser.equals("admin")){
                    openListLocal("admin-my-locals");
                }
            }
        });
    }

    private void exit(){
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void openListLocal(String buttonListener){
        Intent intent = new Intent(HomeActivity.this, ListActivity.class);
        intent.putExtra("type-render", buttonListener);
        intent.putExtra("id", idUser);
        startActivity(intent);
    }
}