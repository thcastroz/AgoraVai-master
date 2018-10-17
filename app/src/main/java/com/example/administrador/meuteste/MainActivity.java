package com.example.administrador.meuteste;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
int valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Programe o botão flutuante", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_testesem) {
            Intent intent = new Intent(MainActivity.this, Main4Activity.class);
            startActivity(intent);
            //return true;
        }

        if (id == R.id.action_testecom) {

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("PARTESTE", "com");
            startActivityForResult(intent, 1);

        }

        if (id == R.id.action_bd) {
            Intent intent = new Intent(MainActivity.this, Adicionar.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }


        if (id == R.id.action_sair)
        {
            mensagem("Mensagem","Fechar Aplicativo mesmo?",1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            mensagem("Titulo da mensagem", data.getStringExtra("returnedData"));
        }
    }

    public void mensagem(String titulo, String mensagem) {
        AlertDialog.Builder alertateste = new AlertDialog.Builder(MainActivity.this);
        alertateste.setMessage(mensagem);
        alertateste.setTitle(titulo);
        alertateste.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        alertateste.show();
    }

    public void mensagem(String titulo,String mensagem,int itx)
    {
        AlertDialog.Builder alertateste = new AlertDialog.Builder(MainActivity.this);
        alertateste.setMessage(mensagem);
        alertateste.setTitle(titulo);
        alertateste.setCancelable(true);
        valor = itx;
        alertateste.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (valor == 1)
                {
                    System.exit(0);
                }

            }
        });
        alertateste.setNegativeButton("NAO", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //              singdados teste = new singdados();
                //singdados.getInstance();
            }
        });
        alertateste.show();
    }


}
