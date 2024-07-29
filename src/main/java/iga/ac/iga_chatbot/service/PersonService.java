package iga.ac.iga_chatbot.service;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.CrudRepositoryService;
import iga.ac.iga_chatbot.Repo.PersonRepo;
import iga.ac.iga_chatbot.entity.Person;

@BrowserCallable
@AnonymousAllowed
public class PersonService extends CrudRepositoryService<Person,Long, PersonRepo> {
}
