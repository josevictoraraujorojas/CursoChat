package br.gov.ifgoiano.cursochat.model;

import java.time.LocalDateTime;

public class Mensagem {
    String usuario;
    String conteudo;
    LocalDateTime data;

    public Mensagem(String usuario, String conteudo, LocalDateTime data) {
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

}
