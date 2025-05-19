package br.gov.ifgoiano.cursochat.model;

import java.time.LocalDateTime;

public record MensagemVO(String usuario, String conteudo, LocalDateTime dat) {
}
