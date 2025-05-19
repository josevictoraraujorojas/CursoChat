package br.gov.ifgoiano.cursochat.controller;

import br.gov.ifgoiano.cursochat.model.Mensagem;
import br.gov.ifgoiano.cursochat.model.MensagemVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatTempoRealController {
    // Define que este método será executado sempre que uma mensagem for enviada para o destino "/app/new-message"
    @MessageMapping("/new-message")

    // Define que o retorno deste método será enviado para todos os assinantes do tópico "/topics/livechat"
    @SendTo("/topics/livechat")
    public MensagemVO newMessage(Mensagem mensagem) {
        // Cria um novo objeto MensagemVO com os dados recebidos
        // Esse objeto será retornado aos clientes conectados
        return new MensagemVO(mensagem.getUsuario(), mensagem.getConteudo(), mensagem.getData());
    }
}
