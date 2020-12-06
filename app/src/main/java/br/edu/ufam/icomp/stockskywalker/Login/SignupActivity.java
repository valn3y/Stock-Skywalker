package br.edu.ufam.icomp.stockskywalker.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.UserDAO;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try{
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        setContentView(R.layout.activity_signup);

        EditText fieldName = (EditText) findViewById(R.id.nameSignup);
        EditText fieldEmail = (EditText) findViewById(R.id.emailSignup);
        EditText fieldPassword = (EditText) findViewById(R.id.passwordSignup);
        EditText fieldConfirmPass = (EditText) findViewById(R.id.confirmPassSignup);
        RadioGroup typeUsersRadio = (RadioGroup) findViewById(R.id.typeUserRadio);
        Button signupButton = (Button) findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fieldName.getText().toString();
                String email = fieldEmail.getText().toString();
                String password = fieldPassword.getText().toString();
                String confirmPass = fieldConfirmPass.getText().toString();
                int selectRadio = typeUsersRadio.getCheckedRadioButtonId();
                RadioButton typeUser = (RadioButton) findViewById(selectRadio);
                String user = typeUser.getText().toString();

                preRequest(name, email, password, confirmPass, user);
            }
        });
    }

    private void preRequest(String name, String email, String password, String confirmPass, String category){
        if(name.length() == 0 || email.length() == 0 || password.length() == 0 || confirmPass.length() == 0){
            Toast.makeText(SignupActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
        } else {
            if(password.equals(confirmPass)){
                request(name, email, password, category);
            } else {
                Toast.makeText(SignupActivity.this, "As senhas devem ser iguais", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void request(String name, String email, String password, String category){
        UserDAO userDAO = new UserDAO(this);
        String newCategory = "";
        if(category.equals("Cliente")){
            newCategory = "client";
        }
        if(category.equals("Administrador")){
            newCategory = "admin";
        }

        boolean result = userDAO.add(name, email, password, newCategory);
        if(result){
            finish();
        } else {
            Toast.makeText(SignupActivity.this, "Ooops! Não foi possível cadastrar o usuário, tente novamente", Toast.LENGTH_LONG).show();
        }
    }
}