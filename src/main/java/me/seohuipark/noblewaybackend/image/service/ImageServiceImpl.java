package me.seohuipark.noblewaybackend.image.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public byte[] convertHtmlCodeToImage(String html, int width, int height, String format) {
        System.setProperty("java.awt.headless", "false");

        BufferedImage bufferedImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height);
        Graphics graphics = bufferedImage.createGraphics();

        JEditorPane jEditorPane = new JEditorPane("text/html", html);
        jEditorPane.setSize(width, height);
        jEditorPane.print(graphics);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, format, byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
