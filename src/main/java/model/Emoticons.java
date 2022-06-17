package model;

public class Emoticons {

    private int contarDivertido;
    private int contarChateado;
    private String divertido = ":-)";
    private String chateado = ":-(";
    private String frase;
    private String descricao;


    public Emoticons(String frase) {
        this.frase = frase;
        contarEspresao();
        this.descricao = definirSentimento();
    }


    public String getFrase() {
        return frase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void contarEspresao() {
        for (int i = 0; i < frase.length(); i++) {
            if (frase.substring(i).startsWith(divertido)) {
                this.contarDivertido++;
            } else if (frase.substring(i).startsWith(chateado)) {
                this.contarChateado++;
            }
        }
        definirSentimento();
    }


    public String definirSentimento() {
        if (contarDivertido == contarChateado) {
            return "NEUTRO";
        } else if (contarDivertido > contarChateado) {
            return "DIVERTIDO";
        } else {
            return "CHATEADO";
        }
    }


    public String toString(){
         return  "FRASE: " + this.frase + "\n" +
                 "SENTIMENTO: " + this.descricao;
    }
}

