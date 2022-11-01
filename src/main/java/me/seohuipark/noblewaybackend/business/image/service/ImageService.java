package me.seohuipark.noblewaybackend.business.image.service;

public interface ImageService {

    byte[] convertHtmlCodeToImage(String html, int width, int height, String format);
}
