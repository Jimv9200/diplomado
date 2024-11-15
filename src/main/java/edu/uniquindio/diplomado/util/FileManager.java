package edu.uniquindio.diplomado.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class FileManager {
	private static final String UPLOAD_DIR = "/uploads/";

	public String getFileHash(byte[] fileBytes) throws Exception {
		MessageDigest hashDigest = MessageDigest.getInstance("SHA-256");
		byte[] hash = hashDigest.digest(fileBytes);
		return Base64.getEncoder().encodeToString(hash);
	}

	public String saveFileToDisk(MultipartFile file) throws Exception {
		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) uploadDir.mkdirs();

		String filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename()).toString();
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			fos.write(file.getBytes());
		}
		return filePath;
	}
}
