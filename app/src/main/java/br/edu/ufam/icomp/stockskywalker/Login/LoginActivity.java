package br.edu.ufam.icomp.stockskywalker.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ufam.icomp.stockskywalker.Home.HomeActivity;
import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.User;
import br.edu.ufam.icomp.stockskywalker.storage.UserDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try{
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        setContentView(R.layout.activity_login);

        Button buttonLogin = (Button) findViewById(R.id.btnLogin);
        TextView buttonSignup = (TextView) findViewById(R.id.btnSignup);
        EditText fieldEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText fieldPassword = (EditText) findViewById(R.id.editTextTextPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = fieldEmail.getText().toString();
                String password = fieldPassword.getText().toString();
                request(email, password);
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSignup();
            }
        });
    }

    private void goSignup(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void request(String email, String password){
        if(email.length() == 0 || password.length() == 0) {
            Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
        } else {
            UserDAO userDAO = new UserDAO(this);
            User user = userDAO.get(email, password);
            if(user != null) {
                if(user.getCategory().equals("client")){
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("id", user.getIdUser());
                    intent.putExtra("name", user.getName());
                    intent.putExtra("type-user", "client");
                    startActivity(intent);
                    finish();
                }
                if(user.getCategory().equals("admin")) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("id", user.getIdUser());
                    intent.putExtra("name", user.getName());
                    intent.putExtra("type-user", "admin");
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Ooops! Não foi possível fazer login, confira seus dados ou cadastre um usuário.", Toast.LENGTH_LONG).show();
            }
        }
    }
}