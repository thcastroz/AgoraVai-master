package com.example.administrador.meuteste;
import java.io.Serializable;


public class Pessoa implements Serializable {


    private String nome;
    private String cpf;
    private String idade;
    private String cidade;
    private int codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {return idade;}

    public void setIdade (String idade) {this.idade = idade;}

    public String getCidade() {return cidade;}
    public void setCidade (String cidade) {this.cidade = cidade;}

    public void setCodigo(int codigo){
        this.codigo=codigo;
    }

    public int getCodigo(){
        return this.codigo;
    }

    @Override
    public String toString() {
        return  nome;
    }

}
