package iga.ac.iga_chatbot.Repo;

import iga.ac.iga_chatbot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepo extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}
