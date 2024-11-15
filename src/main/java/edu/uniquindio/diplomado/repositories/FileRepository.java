package edu.uniquindio.diplomado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uniquindio.diplomado.entities.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long>{

}
