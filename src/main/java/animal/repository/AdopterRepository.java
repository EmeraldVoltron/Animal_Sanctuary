package animal.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import animal.beans.Adopter;

//@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Long> {
 Optional<Adopter> findByUsername(String username);
 Optional<Adopter> findById(Long id);
}
