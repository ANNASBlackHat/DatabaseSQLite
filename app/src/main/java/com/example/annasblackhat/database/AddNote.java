package com.example.annasblackhat.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNote extends AppCompatActivity {

    EditText txtDeskripsi;
    EditText txtTotal;
    Spinner spinKategori;
    WalletDatabase walletDb;
    RadioButton rbIncome;
    RadioButton rbExpense;
    String[] categories = {"Eating","Sport","Internet","Study"};
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        walletDb = new WalletDatabase(this);

        txtDeskripsi = (EditText) findViewById(R.id.edt_deskripsi);
        txtTotal = (EditText) findViewById(R.id.edt_total);
        spinKategori = (Spinner) findViewById(R.id.spinner);
        rbExpense = (RadioButton) findViewById(R.id.rb_expense);
        rbIncome = (RadioButton) findViewById(R.id.rb_income);

        spinKategori.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categories));

    }

    private void simpanCatatan(){
        String jenis;
        if(rbIncome.isChecked()) jenis = "Income";
        else jenis = "Expense";

        Wallet w = new Wallet();
        w.setJenis(jenis);
        w.setDeskripsi(txtDeskripsi.getText().toString());
        w.setTotal(Integer.parseInt(txtTotal.getText().toString()));
        w.setTgl(dateFormat.format(new Date()));
        walletDb.insertData(w);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addnote, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            simpanCatatan();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
