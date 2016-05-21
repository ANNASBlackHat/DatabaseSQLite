package com.example.annasblackhat.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn_Click(View v){
        if(v.getId() == R.id.btnIncome){
            startActivity(new Intent(this, IncomeActivity.class));
        }else if(v.getId() == R.id.btnExpense){
            startActivity(new Intent(this, ExpenseActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            startActivity(new Intent(this, AddNote.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
