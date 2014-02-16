package prueba.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import prueba.model.Message;

public class BaseDao implements Dao {

	@PersistenceContext
	private EntityManager em;

	public List<Message> getAllMessages() {
		return em.createQuery("select m from Message m", Message.class).getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
