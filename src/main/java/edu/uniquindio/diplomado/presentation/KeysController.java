package edu.uniquindio.diplomado.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.uniquindio.diplomado.entities.KeyInfo;
import edu.uniquindio.diplomado.repositories.KeysInfoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class KeysController {
	@Autowired
	private KeysInfoRepository keysInfoRepository;
	
	@PostMapping("/save")
	public String saveKeyInfo(@RequestBody String keyInfo) {
		KeyInfo kInfo = new KeyInfo();
        kInfo.setValue(keyInfo);
        keysInfoRepository.save(kInfo);
        return "Value saved: " + keyInfo;
	}
	
}
