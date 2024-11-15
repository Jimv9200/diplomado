package edu.uniquindio.diplomado.presentation;

import java.security.PrivateKey;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.uniquindio.diplomado.util.PublicKeyCipher;
import edu.uniquindio.diplomado.util.Signer;

@RestController
@RequestMapping("/api/files")
public class FilesController {
	@Autowired
	private PublicKeyCipher publicKeyCipher;
	@Autowired
	private Signer signer;
	
	@GetMapping("/sign")
    public ResponseEntity<String> signFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("privateKey") MultipartFile privateKeyFile) {
        try {
            PrivateKey privateKey = publicKeyCipher.getPrivateKeyFromBytes(privateKeyFile.getBytes());

            byte[] signature = signer.signFileWithPrivateKey(file.getBytes(), privateKey);

            String signatureBase64 = Base64.getEncoder().encodeToString(signature);

            return ResponseEntity.ok(signatureBase64);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error trying signing the file: " + e.getMessage());
        }
    }
	
	@PostMapping("/sign")
    public ResponseEntity<String> signAndSaveFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("privateKey") MultipartFile privateKeyFile) {
        try {
            PrivateKey privateKey = publicKeyCipher.getPrivateKeyFromBytes(privateKeyFile.getBytes());

            signer.signFileAndSaveWithPrivateKey(file.getBytes(), privateKey, file.getName());

            return ResponseEntity.ok("File Sign saved ok");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error trying signing the file: " + e.getMessage());
        }
    }
}
