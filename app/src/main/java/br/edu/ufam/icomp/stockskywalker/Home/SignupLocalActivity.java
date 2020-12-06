package br.edu.ufam.icomp.stockskywalker.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.edu.ufam.icomp.stockskywalker.R;
import br.edu.ufam.icomp.stockskywalker.storage.LocalDAO;

public class SignupLocalActivity extends AppCompatActivity {
    private String typeAction;
    private LocalDAO localDAO;
    private int idUser;
    private int idLocal;
    private String paramCategory;
    private String paramAddress;
    private Integer paramWidth;
    private Integer paramHeight;
    private Integer paramDepth;
    private Integer paramPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_local);

        try{
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        setContentView(R.layout.activity_signup_local);

        if(getIntent().hasExtra("type")){
            Bundle extras = getIntent().getExtras();
            typeAction = extras.getString("type");
            idUser = extras.getInt("id-user");
            idLocal = extras.getInt("id-local");
            paramCategory = extras.getString("category");
            paramAddress = extras.getString("address");
            paramWidth = extras.getInt("width");
            paramHeight = extras.getInt("height");
            paramDepth = extras.getInt("depth");
            paramPrice = extras.getInt("price");
        }

        EditText fieldAddress = (EditText) findViewById(R.id.signupAddress);
        EditText fieldWidth = (EditText) findViewById(R.id.signupWidth);
        EditText fieldHeight = (EditText) findViewById(R.id.signupHeight);
        EditText fieldDepth = (EditText) findViewById(R.id.signupDepth);
        EditText fieldPrice = (EditText) findViewById(R.id.signupPrice);
        RadioGroup categoryRadio = (RadioGroup) findViewById(R.id.groupCategory);

        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdateLocal);
        Button buttonDelete = (Button) findViewById(R.id.buttonDeleteLocal);

        if(typeAction.equals("update")){
            buttonDelete.setVisibility(View.VISIBLE);
            buttonUpdate.setText("ATUALIZAR");

            fieldAddress.setText(paramAddress);
            fieldDepth.setText(paramDepth.toString());
            fieldHeight.setText(paramHeight.toString());
            fieldWidth.setText(paramWidth.toString());
            fieldPrice.setText(paramPrice.toString());

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectRadio = categoryRadio.getCheckedRadioButtonId();
                    RadioButton categoryStock = (RadioButton) findViewById(selectRadio);
                    String category = categoryStock.getText().toString();

                    String address = fieldAddress.getText().toString();
                    String width = fieldWidth.getText().toString();
                    String height = fieldHeight.getText().toString();
                    String depth = fieldDepth.getText().toString();
                    String price = fieldPrice.getText().toString();

                    handleUpdateLocal(category,address,width,height,depth,price);
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleDeleteLocal();
                }
            });
        }

        if(typeAction.equals("create")){
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectRadio = categoryRadio.getCheckedRadioButtonId();
                    RadioButton categoryStock = (RadioButton) findViewById(selectRadio);
                    String category = categoryStock.getText().toString();

                    String address = fieldAddress.getText().toString();
                    String width = fieldWidth.getText().toString();
                    String height = fieldHeight.getText().toString();
                    String depth = fieldDepth.getText().toString();
                    String price = fieldPrice.getText().toString();

                    handleCreateNewLocal(address, width, height, depth, category, price);
                }
            });
        }
    }

    private void handleCreateNewLocal(String address, String width, String height, String depth, String category, String price){
        if(address.length() == 0 || width.length() == 0 || height.length() == 0 || depth.length() == 0 || category.length() == 0 || price.length() == 0){
            Toast.makeText(SignupLocalActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        } else {
            localDAO = new LocalDAO(this);
            int parseWidth = Integer.parseInt(width);
            int parseHeight = Integer.parseInt(height);
            int parseDepth = Integer.parseInt(depth);
            int parsePrice = Integer.parseInt(price);

            boolean result = localDAO.createLocal(category, address, parseWidth, parseHeight, parseDepth, parsePrice, true, idUser);
            if(result){
                finish();
            } else {
                Toast.makeText(SignupLocalActivity.this, "Ooops! Não foi possível cadastrar, tente novamente.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleUpdateLocal(String category, String address, String width, String height, String depth, String price){
        if(category.length() == 0 || address.length() == 0 || width.length() == 0 || height.length() == 0 || depth.length() == 0 || price.length() == 0){
            Toast.makeText(SignupLocalActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        } else {
            localDAO = new LocalDAO(this);
            int parseWidth = Integer.parseInt(width);
            int parseHeight = Integer.parseInt(height);
            int parseDepth = Integer.parseInt(depth);
            int parsePrice = Integer.parseInt(price);

            boolean result = localDAO.updateLocal(category, address,parseWidth, parseHeight, parseDepth, parsePrice, idLocal);
            if(result){
                finish();
            }else {
                Toast.makeText(SignupLocalActivity.this, "Ooops! Erro em atualizar o local, tente novamente.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleDeleteLocal(){
        localDAO = new LocalDAO(this);
        boolean result = localDAO.deleteLocal(idLocal);
        if(result){
            finish();
        } else {
            Toast.makeText(SignupLocalActivity.this, "Ooops! Erro em excluir o local, tente novamente.", Toast.LENGTH_LONG).show();
        }
    }
}