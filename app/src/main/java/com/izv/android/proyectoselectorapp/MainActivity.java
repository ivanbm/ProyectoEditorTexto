package com.izv.android.proyectoselectorapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {
    EditText etTexto;
    Uri data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        data = intent.getData();

        leer();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void leer(){
        File f=new File(data.getPath());

        etTexto = (EditText)findViewById(R.id.etTexto);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            StringBuilder texto = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                texto.append(linea);
                texto.append('\n');
            }

            etTexto.setText(texto);
            br.close();
        }catch (Exception e){
            System.out.println("ERROR AL LEER");
        }
    }


    public void guardar(View v){
        File f = new File(data.getPath());
        EditText escribe = (EditText)findViewById(R.id.etTexto);
        String es = escribe.getText().toString();

        try{

            FileWriter fw= new FileWriter(f, false);
            fw.write(es);
            fw.flush();
            fw.close();


        }catch (IOException e){
            Log.v("ARCHIVO", "Excepcion " + e.toString());
        }
        tostada(getString(R.string.msgguardar));
    }


    private void tostada(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
