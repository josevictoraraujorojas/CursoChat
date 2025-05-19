package br.gov.ifgoiano.cursochat.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Informa ao Spring que esta classe contém configurações do aplicativo
@Configuration

// Habilita o suporte a WebSockets com STOMP no projeto
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Configura o broker de mensagens (responsável por enviar mensagens entre cliente e servidor)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Define um broker simples que usará o prefixo /topics para envio de mensagens aos assinantes
        registry.enableSimpleBroker("/topics");

        // Define o prefixo /app como destino das mensagens enviadas pelos clientes
        // As mensagens enviadas para /app serão roteadas para os métodos @MessageMapping do servidor
        registry.setApplicationDestinationPrefixes("/app");
    }

    // Registra os endpoints STOMP acessíveis via WebSocket pelos clientes
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Define o endpoint onde os clientes irão se conectar com WebSocket
        // Exemplo: ws://localhost:8080/buildrun-livechat-websocket
        registry.addEndpoint("/buildrun-livechat-websocket");
    }
}

