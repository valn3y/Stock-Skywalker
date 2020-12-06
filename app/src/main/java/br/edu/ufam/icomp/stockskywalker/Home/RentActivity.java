package br.edu.ufam.icomp.stockskywalker.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.LocalDAO;
import br.edu.ufam.icomp.stockskywalker.storage.RentDAO;

public class RentActivity extends AppCompatActivity {
    private int idLocal;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        try{
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        setContentView(R.layout.activity_rent);


        if(getIntent().hasExtra("id-local")){
            Bundle extras = getIntent().getExtras();
            idLocal = extras.getInt("id-local");
            idUser = extras.getInt("id-user");
        }

        EditText fieldStartDate = (EditText) findViewById(R.id.editStartDate);
        EditText fieldEndDate = (EditText) findViewById(R.id.editEndDate);
        CheckBox checkSecurity = (CheckBox) findViewById(R.id.checkSecurity);
        CheckBox checkControlWeather = (CheckBox) findViewById(R.id.checkControlWeather);
        CheckBox checkKeyExtra = (CheckBox) findViewById(R.id.checkKeyExtra);

        SimpleMaskFormatter simpleMask = new SimpleMaskFormatter("NN-NN-NNNN");
        MaskTextWatcher maskStartDate = new MaskTextWatcher(fieldStartDate, simpleMask);
        MaskTextWatcher maskEndDate = new MaskTextWatcher(fieldEndDate, simpleMask);
        fieldStartDate.addTextChangedListener(maskStartDate);
        fieldEndDate.addTextChangedListener(maskEndDate);

        Button buttonRent = (Button) findViewById(R.id.buttonRent);
        buttonRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = fieldStartDate.getText().toString();
                String endDate = fieldEndDate.getText().toString();
                boolean security = checkSecurity.isChecked();
                boolean controlWeather = checkControlWeather.isChecked();
                boolean keyExtra = checkKeyExtra.isChecked();

                request(startDate, endDate, security, controlWeather, keyExtra);
            }
        });
    }

    public void request(String startDate, String endDate, boolean security, boolean controlWeather, boolean keyExtra){
        if(startDate.length() == 0 || endDate.length() == 0) {
            Toast.makeText(RentActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            if(startDate.length() == 10 && endDate.length() == 10){
                RentDAO rentDAO = new RentDAO(this);
                LocalDAO localDAO = new LocalDAO(this);
                boolean result = rentDAO.createRent(startDate, endDate, security, keyExtra, controlWeather, idLocal);
                if(result){
                    localDAO.changeAvailable(false, idLocal, idUser);
                    finish();
                } else{
                    Toast.makeText(RentActivity.this, "Ooops! Não foi possível alugar, tente novamente.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(RentActivity.this, "Ooops! Formato data incompleta", Toast.LENGTH_LONG).show();
            }
        }
    }
}