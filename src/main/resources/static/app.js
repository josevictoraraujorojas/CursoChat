// Criação do cliente STOMP com a URL do WebSocket
const stompClient = new StompJs.Client({
    brokerURL: 'ws://' + window.location.host + '/ChatTempoReal'
});

// Função chamada quando a conexão com o WebSocket for estabelecida com sucesso
stompClient.onConnect = (frame) => {
    determinaConexao(true); // Atualiza a interface para mostrar que está conectado
    console.log('Conectado: ' + frame);

    // Inscreve-se no tópico '/topics/livechat' para receber mensagens em tempo real
    stompClient.subscribe('/topicos/estudantes', (mensagemJSON) => {
        // Atualiza o chat com a nova mensagem recebida
        atalizarChat(JSON.parse(mensagemJSON.body));
    });
};

// Função chamada quando ocorre um erro no WebSocket (ex: conexão falhou)
stompClient.onWebSocketError = (error) => {
    console.error('Erro no websocket', error);
};

// Função chamada quando ocorre um erro específico no protocolo STOMP
stompClient.onStompError = (frame) => {
    console.error('Erro no broker do stomp: ' + frame.headers['mensagem']);
    console.error('Detalhes adicionais: ' + frame.body);
};

// Atualiza os botões e a interface de conexão
function determinaConexao(connected) {
    $("#conectar").prop("disabled", connected);       // Desabilita botão 'conectar' se estiver conectado
    $("#desconectar").prop("disabled", !connected);   // Habilita botão 'desconectar' se estiver conectado

    if (connected) {
        $("#conversas").show(); // Mostra a área de conversas
    } else {
        $("#conversas").hide(); // Aqui deveria ser .hide() para ocultar quando desconectado
    }
}

// Ativa (conecta) o cliente STOMP
function conectar() {
    stompClient.activate();
}

// Desativa (desconecta) o cliente STOMP
function desconectar() {
    stompClient.deactivate();
    determinaConexao(false); // Atualiza a interface como desconectado
    console.log("Desconectado");
}

// Envia uma nova mensagem para o servidor
function enviarMensagem() {
    const agora = new Date(); // Pega a data/hora atual

    // Publica a mensagem no destino "/app/new-message"
    stompClient.publish({
        destination: "/estudantes/novaMensagem",
        body: JSON.stringify({
            'usuario': $("#usuario").val(),
            'conteudo': $("#mensagem").val(),
            'data': agora.toISOString().split('.')[0] // Data no formato ISO sem milissegundos
        })
    });

    // Log da mensagem enviada no console
    console.log({
        'usuario': $("#usuario").val(),
        'conteudo': $("#mensagem").val(),
        'data': agora.toISOString().split('.')[0]
    });

    // Limpa o campo de mensagem
    $("#message").val("");
}

// Atualiza o chat na tela com a nova mensagem recebida
function atalizarChat(mensagem) {
    $("#chatTempoReal").append(
        "<tr><td>" + mensagem.dat + " - " + mensagem.usuario + ": " + mensagem.conteudo + "</td></tr>"
    );
}

// Quando o DOM estiver carregado, configura os eventos dos botões
$(function () {
    $("form").on('submit', (e) => e.preventDefault()); // Impede envio padrão do formulário
    $("#conectar").click(() => conectar());           // Botão conectar
    $("#desconectar").click(() => desconectar());     // Botão desconectar
    $("#enviar").click(() => enviarMensagem());       // Botão enviar mensagem
});
