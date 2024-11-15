package edu.uniquindio.diplomado.util;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uniquindio.diplomado.entities.FileEntity;
import edu.uniquindio.diplomado.repositories.FileRepository;

@Service
public class Signer {
	@Autowired
    private FileRepository repository;
    @Autowired
    private FileManager fileManager;

	public byte[] signFileWithPrivateKey(byte[] fileBytes, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(fileBytes);
        return signature.sign();
    }
	

	public FileEntity signFileAndSaveWithPrivateKey(byte[] fileBytes, PrivateKey privateKey, String fileName) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(fileBytes);
        
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setFileSign(Base64.getEncoder().encodeToString(signature.sign()));
        fileEntity.setFileHash(fileManager.getFileHash(fileBytes));
        
        repository.save(fileEntity);

        return fileEntity;
    }
}
