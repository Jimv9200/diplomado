package edu.uniquindio.diplomado.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uniquindio.diplomado.entities.PublicKeyEntity;
import edu.uniquindio.diplomado.repositories.PublicKeysRepository;

@Service
public class PublicKeyCipher {
	@Autowired
    private PublicKeysRepository repository;

    public PrivateKey generateAndStoreKeyPair(String name) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        PublicKeyEntity publicKeyEntity = new PublicKeyEntity();
        publicKeyEntity.setName(name);
        publicKeyEntity.setPublicKey(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        repository.save(publicKeyEntity);

        return privateKey;
    }
    
    public PrivateKey getPrivateKeyFromBytes(byte[] privateKeyBytes) throws Exception {
    	PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
