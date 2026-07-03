package com.example.demo; // Vérifie que ce package est correct
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
public class QRCodeGenerator {
    public static void generateQRCode(String text, int width, int height, String filePath) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("QR Code généré avec succès à : " + filePath);
    }
}