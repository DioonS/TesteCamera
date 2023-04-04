package com.example.testecamera;

import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class Camera {

    @GetMapping("/capture")
    public byte[] captureImage() throws FrameGrabber.Exception, IOException {
        // Cria um objeto FrameGrabber para a câmera padrão
        FrameGrabber grabber = FrameGrabber.createDefault(0);
        grabber.start();

        // Captura um frame da câmera
        var frame = grabber.grab();
        if (frame == null) {
            throw new RuntimeException("Não foi possível capturar a imagem");
        }

        // Converte o frame para BufferedImage
        var converter = new Java2DFrameConverter();
        var imagem = converter.getBufferedImage(frame);

        // Fecha o objeto FrameGrabber
        grabber.stop();
        grabber.release();

        // Converte a imagem para um array de bytes
        var outputStream = new ByteArrayOutputStream();
        ImageIO.write(imagem, "jpg", outputStream);

        return outputStream.toByteArray();
    }
}