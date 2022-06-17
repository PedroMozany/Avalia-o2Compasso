package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Produto {
    private int id;
    private String nome;
    private String desricao;
    private double desconto;
    private double preço;
    private Date dataInicio;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public Produto(String nome,String desricao,double desconto,double preço,String dataInicio) throws ParseException {
        this.nome = nome;
        this.desricao = desricao;
        this.desconto = desconto;
        this.preço = preço;
        this.dataInicio = sdf.parse(dataInicio);
    }


    public Produto(int id, String nome,String desricao,double desconto,double preço,String dataInicio) throws ParseException {
        this.id = id;
        this.nome = nome;
        this.desricao = desricao;
        this.desconto = desconto;
        this.preço = preço;
        this.dataInicio = sdf.parse(dataInicio);
    }



    public String toString() {
        StringBuilder sb = new StringBuilder("============================ \n");
        sb.append("ID: " + this.id + " || ");
        sb.append("NOME: " + this.nome + " || ");
        sb.append("DESCRIÇÃO: " + this.desricao + "\n");
        sb.append("DESCONTO:" + this.desconto + "%" + " || ");
        sb.append("PREÇO:" + "R$ " + this.preço + "\n");
        sb.append("DATA INICIAL: " + sdf.format(dataInicio));
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDesricao() {
        return desricao;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getPreço() {
        return preço;
    }

    public String getDataInicio() {
        return sdf.format(dataInicio);
    }

}
