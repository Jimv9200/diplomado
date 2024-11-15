package edu.uniquindio.diplomado.presentation;

import java.security.PrivateKey;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uniquindio.diplomado.util.PublicKeyCipher;

@RestController
@RequestMapping("/api/keys")
public class KeysController {
	@Autowired
	private PublicKeyCipher publicKeyCipher;
	
	@PostMapping("/generate")
	public ResponseEntity<byte[]> generateKeyPair(@RequestBody String name) {
		try {
            PrivateKey privateKey = publicKeyCipher.generateAndStoreKeyPair(name);

            String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=private_key.pem")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(privateKeyString.getBytes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
	}
	
}
