package de.lucahenn.multichannel.domain.auth;

import it.auties.whatsapp.api.PairingCodeHandler;
import it.auties.whatsapp.api.QrHandler;
import it.auties.whatsapp.api.Whatsapp;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;


public class WhatsappService {

    private static WhatsappService whatsappServiceInstance;
    @Setter
    @Getter
    private long phoneNumber;
    @Getter
    private Whatsapp whatsapp;
    @Getter
    private File qrCode;

    private WhatsappService() {
        this.whatsapp = Whatsapp.webBuilder()
                .newConnection()
                .unregistered(QrHandler.toFile(path -> {
                    try {
                        // Define the target path in the resources folder
                        String resourcesPath = "src/main/resources";
                        Path targetPath = Paths.get(resourcesPath, "qrCode.jpg");

                        // Copy the file to the target path
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        this.qrCode = targetPath.toFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));

    }

    public static WhatsappService getInstance() {
        if (whatsappServiceInstance == null) {
            whatsappServiceInstance = new WhatsappService();
        }
        return whatsappServiceInstance;
    }
}
