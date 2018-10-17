package com.example.administrador.meuteste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContatoController extends DataBaseAdapter {
    public ContatoController(Context context){
        super(context);
    }

    public boolean create(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("cidade", contato.getCidade());
        values.put("idade", contato.getIdade());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("contato", null, values) > 0;
        db.close();

        return isCreate;
    }

    public int totalDeContatos(){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }

    public List<Contato> listarContatos(){

        List<Contato> contatos = new ArrayList<>();

        String sql  = "SELECT * FROM contato ORDER by id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
                String idade = cursor.getString(cursor.getColumnIndex("idade"));

                Contato contato = new Contato();

                contato.setId(id);
                contato.setNome(nome);
                contato.setEmail(email);
                contato.setCidade(cidade);
                contato.setIdade(idade);
                contatos.add(contato);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();



        return contatos;
    }

    public Contato buscarContatoPeloID(int contatoID){

        Contato contato = new Contato();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato WHERE id = "+contatoID;

        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()) {

            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String idade = cursor.getString(cursor.getColumnIndex("idade"));


            contato.setId(contatoID);
            contato.setNome(nome);
            contato.setEmail(email);
            contato.setCidade(cidade);
            contato.setIdade(idade);

        }

        return contato;
    }

    public boolean update(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());
        values.put("cidade", contato.getCidade());
        values.put("idade", contato.getIdade());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(contato.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isUpdate = db.update("contato", values,
                where, whereArgs) > 0;

        db.close();

        return  isUpdate;

    }

    public boolean delete(int contatoID){

        boolean isDeletado = false;

        SQLiteDatabase db = this.getWritableDatabase();
        isDeletado = db.delete("contato", "id ='" + contatoID + "'", null) > 0;
        db.close();

        return isDeletado;

    }
}
