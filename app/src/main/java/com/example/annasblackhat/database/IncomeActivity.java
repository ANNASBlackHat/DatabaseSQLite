package com.example.annasblackhat.database;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class IncomeActivity extends AppCompatActivity {

    List<Wallet> walletList;
    ListView mListView;
    WalletDatabase walletDb;
    ArrayAdapter<Wallet> walletArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        walletDb = new WalletDatabase(this);
        walletList = new ArrayList<>();

       walletArrayAdapter = new ArrayAdapter<Wallet>(this, R.layout.list_item){
           @Override
           public View getView(int position, View convertView, ViewGroup parent) {
               View v = LayoutInflater.from(IncomeActivity.this)
                       .inflate(R.layout.list_item, parent,false);
               TextView txtDesk = (TextView) v.findViewById(R.id.txtTitle);
               txtDesk.setText(walletList.get(position).getDeskripsi());
               return v;
           }

           @Override
           public int getCount() {
               return walletList.size();
           }
       };

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(walletArrayAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                String[] actions = {"Edit","Delete"};
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
                alertDialog.setTitle("Choose Action")
                        .setItems(actions, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
                                    //proses edit
                                }else{
                                    walletDb.deleteData(walletList.get(position).getId());
                                    loadDataFromDb();
                                }
                            }
                        }).show();
                return false;
            }
        });

        loadDataFromDb();
    }

    private void loadDataFromDb(){
        walletList.clear();
        walletList.addAll(walletDb.getDataByJenis("Income"));
        walletArrayAdapter.notifyDataSetChanged();
    }
}
