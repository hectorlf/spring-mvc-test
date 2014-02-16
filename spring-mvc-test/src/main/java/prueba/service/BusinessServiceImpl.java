package prueba.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prueba.dao.Dao;
import prueba.model.Message;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

	private Dao dao;

	@Inject
	public BusinessServiceImpl(Dao dao) {
		this.dao = dao;
	}

	public List<Message> getAllMessages() {
		return dao.getAllMessages();
	}

}
