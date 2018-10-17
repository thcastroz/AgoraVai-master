package com.example.administrador.meuteste;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContatoOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextContatoNome = (EditText) formElementsView.findViewById(R.id.editTextContatoNome);
        final EditText editTextContatoEmail = (EditText) formElementsView.findViewById(R.id.editTextContatoEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Criar Contato")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // Regra de negócio para Incluir novos contatos
                                String contatoNome = editTextContatoNome.getText().toString();
                                String contatoEmail = editTextContatoEmail.getText().toString();

                                Contato contato = new Contato();
                                contato.setNome(contatoNome);
                                contato.setEmail(contatoEmail);

                                boolean criadoComSucesso = new ContatoController(context).create(contato);

                                if (criadoComSucesso) {
                                    Toast.makeText(context, "Contato incluído com sucesso.", Toast.LENGTH_SHORT).show();

                                    ((Adicionar) context).contadorDeRegistros();
                                    ((Adicionar) context).atualizarListaDeContatos();

                                } else {
                                    Toast.makeText(context, "Não foi possível incluir o contato.", Toast.LENGTH_SHORT).show();
                                }



                                dialog.cancel();
                            }

                        }).show();

    }
}
