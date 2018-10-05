package com.example.administrador.meuteste;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.attr.value;

public class Main3Activity extends AppCompatActivity {
    Button btinsericl,btListcl,btvoltarcl;
    ListView listadecliente;
    ProgressDialog mprogressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btinsericl = (Button)findViewById(R.id.btinserircl);
        btListcl = (Button)findViewById(R.id.btListcl);
        listadecliente = (ListView) findViewById(R.id.listadecliente);
        btvoltarcl = (Button)findViewById(R.id.btvoltarcl);



        btinsericl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getBaseContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Cursor c = db.rawQuery("select max(cod_cliente) from clientes",null);
                int idcliente=0;
                if (c.moveToFirst())
                {
                    idcliente=c.getInt(0);
                }
                idcliente+=1;
                ContentValues values = new ContentValues();
                values.put("COD_CLIENTE", idcliente);
                values.put("NOME", "Teste - "+idcliente);
                values.put("CPFCGC", "02722414660/"+idcliente);
                long newRowId;
                newRowId = db.insert("CLIENTES",null,values);
            }
        });
        btListcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mprogressDialog = ProgressDialog.show(Main3Activity.this, "Aguarde", "Verificando...");
                new Thread(new Runnable() {
                    Handler hand = new Handler();
                    List<Pessoa> listadepessoas = new ArrayList<Pessoa>();
                    String erro="";
                    public void run() {
                        try {
                            hand.post(new Runnable() {
                                public void run() {
                                    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getBaseContext());
                                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                                    Cursor c = db.query("CLIENTES",new String[]{"COD_CLIENTE","NOME","CPFCGC"},null,null,null,null,"COD_CLIENTE");
                                    //listadepessoas = new ArrayList<Pessoa>();
                                    boolean proximo = true;

                                    if (c.moveToFirst())
                                    {
                                        while (proximo)
                                        {
                                            Pessoa people = new Pessoa();
                                            people.setCodigo(c.getInt(0));
                                            people.setNome(c.getString(1));
                                            people.setCpf(c.getString(2));
                                            listadepessoas.add(people);
                                            proximo=c.moveToNext();
                                        }
                                    }
                                    if (listadepessoas.size() > 0)
                                    {
                                        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(
                                                Main3Activity.this,
                                                android.R.layout.simple_list_item_1, listadepessoas);
                                        listadecliente.setAdapter(adapter);
                                    }
//                                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                                    imm.hideSoftInputFromWindow(edtpesqprodutos.getWindowToken(), 0);
//                                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                                }
                            });
                        } catch (Exception e) {
                            //mprogressDialog.setMessage("Erro: "+e.toString());
                            mprogressDialog.dismiss();
                            erro = e.toString();
                            hand.post(new Runnable() {
                                public void run() {
                                    //Toast.makeText(produtos.this, "ERRO "+erro.toString(), Toast.LENGTH_SHORT).show();
                                    //mensagem("ERRO", erro);
                                    Log.i("Clientes","ERRO "+erro.toString());
//			        					mprogressDialog.setMessage(erro);
                                }
                            });
                            Log.i("Clientes", "ERRO "+e.toString());
                        }
                        /////
                        mprogressDialog.dismiss();
                    }
                }).start();

            }
        });
        btvoltarcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
