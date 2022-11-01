package me.seohuipark.noblewaybackend.business.image.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import me.seohuipark.noblewaybackend.business.image.service.ImageService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/image")
public class ImageApiController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/html-code/download")
    public ResponseEntity<byte[]> convertHtmlCodeToImageDownload() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        headers.set("Content-Disposition", "attachment; filename=test.png");

        String html = "<h1>Hello, world.</h1>";

        return new ResponseEntity<>(imageService.convertHtmlCodeToImage(html, 200, 100, "png"), headers, HttpStatus.OK);
    }

    @GetMapping("/html-file/download")
    public ResponseEntity<byte[]> convertHtmlFileToImageDownload() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        headers.set("Content-Disposition", "attachment; filename=test.png");

        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("templates/index.html")).getPath());

        if (file.exists()) {
            Document document = Jsoup.parse(file);
            Element element = document.body().getElementById("test");
            if (element != null) {
                element.text("Modified Text");
            }
            String html = document.outerHtml();
            return new ResponseEntity<>(imageService.convertHtmlCodeToImage(html, 200, 100, "png"), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/qr-code/generate")
    public Object createQrCode(@RequestParam String url) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(byteArrayOutputStream.toByteArray());
        }
    }
}
