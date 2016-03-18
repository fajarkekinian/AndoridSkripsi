package com.app.quranqu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    String username;
    String password;
    String fullname;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutReg);

        final EditText edtName = (EditText) findViewById(R.id.reg_name);
        final EditText edtReg = (EditText) findViewById(R.id.reg_username);
        final EditText edtPass = (EditText) findViewById(R.id.reg_password);

        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtReg.getText().toString();
                password = edtPass.getText().toString();
                fullname = edtName.getText().toString();

                if (fullname.equalsIgnoreCase("") || fullname.equalsIgnoreCase(" ")){
                    Snackbar.make(coordinatorLayout, "Insert Your Fullname", Snackbar.LENGTH_LONG).show();
                }else if (username.equalsIgnoreCase("") || username.equalsIgnoreCase(" ")){
                    Snackbar.make(coordinatorLayout, "Insert Your Username", Snackbar.LENGTH_LONG).show();
                }else if (password.equalsIgnoreCase("") || password.equalsIgnoreCase(" ")){
                    Snackbar.make(coordinatorLayout, "Insert Your Password", Snackbar.LENGTH_LONG).show();
                }else {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
