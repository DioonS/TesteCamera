// Seleciona o elemento img
var imagem = document.getElementById("imagem-capturada");

// Seleciona o botão que dispara a captura da imagem
var botaoCapturar = document.getElementById("capturar");

// Adiciona um listener para o evento "click" do botão
botaoCapturar.addEventListener("click", function() {
    // Define a largura e a altura da imagem capturada
    var largura = 640;
    var altura = 480;

    // Cria um objeto de captura de vídeo
    var captura = new cv.VideoCapture(0);

    // Define o tamanho da captura de vídeo
    captura.set(cv.CAP_PROP_FRAME_WIDTH, largura);
    captura.set(cv.CAP_PROP_FRAME_HEIGHT, altura);

    // Captura um quadro de vídeo
    var quadro = new cv.Mat(largura, altura, cv.CV_8UC4);
    captura.read(quadro);

    // Converte a imagem capturada em um blob
    var blob = new Blob([quadro.data], {type: 'image/jpeg'});

    // Define a URL da imagem capturada
    var url = URL.createObjectURL(blob);

    // Atualiza o elemento img com a imagem capturada
    imagem.src = url;
});
