package com.utez.encryption.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@Slf4j
public class TextDecrypt {
    @Value("${encryption.secret-key}")
    private String secretKey;
    public String decrypt(String data) {
        try {
            byte[] dataEncrypted = Base64.getDecoder().decode(data);

            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] dataDecrypted = cipher.doFinal(dataEncrypted);
            return new String(dataDecrypted);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }
}

