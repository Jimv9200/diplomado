package edu.uniquindio.diplomado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uniquindio.diplomado.entities.PublicKeyEntity;

public interface PublicKeysRepository extends JpaRepository<PublicKeyEntity, Long>{

}
