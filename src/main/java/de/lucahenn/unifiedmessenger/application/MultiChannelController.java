package de.lucahenn.unifiedmessenger.application;

import de.lucahenn.unifiedmessenger.domain.auth.WhatsappService;
import it.auties.whatsapp.api.*;
import it.auties.whatsapp.model.chat.Chat;
import it.auties.whatsapp.model.info.MessageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.util.NoSuchElementException;

@RestController
@Data
@RequestMapping("/api")
@Slf4j
public class MultiChannelController {

    @Value("classpath:qrCode.jpg")
    private Resource qrCodeResource;
    @GetMapping(value="/code", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<ByteArrayResource> getQrCode() throws IOException {
        if(!qrCodeResource.exists()) {
            // Return a not found status if QR code does not exist
            return ResponseEntity.notFound().build();
        }

        byte[] content = Files.readAllBytes(qrCodeResource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(content));
    }


    @PostMapping("/send")
    public ResponseEntity<MessageInfo> sendMessage(@RequestBody String message){
       Whatsapp api = WhatsappService.getInstance().getWhatsapp();
       log.info("WhatsApp api init {}",api);
       Chat chat = api.store().findChatByName("Me")
               .orElseThrow(() -> new NoSuchElementException("No chat found"));
       return ResponseEntity.ok(api.sendMessage(chat,message).join());
    }

}
