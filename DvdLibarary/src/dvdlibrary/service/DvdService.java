package dvdlibrary.service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dvdlibrary.dao.InMemoryDao;
import dvdlibrary.model.Dvd;

@Service
public class DvdService {
	@Autowired
	private InMemoryDao dao;
	
	public Map<String, Dvd> getAllDvd(){
//		System.out.println("getAllDvd() of DvdService");
		return dao.findAll();
	}
	public Dvd getDvdByName(String name) {
//		System.out.println("getDvdByName(String) of DvdService");
		Optional<Dvd> opt = dao.findById(name);
		Dvd dvd = null;
		try {
			return opt.get();
		}catch(NoSuchElementException e) {
			return null;
		}
	}
	public void saveDvd(Dvd dvd) {
//		System.out.println("saveDvd(Dvd) of DvdService");
		dao.save(dvd);
	}
	
	public void updateDvd(String name, Dvd dvd) {
//		System.out.println("updateDvd(String, Dvd) of DvdService");
		dao.update(name, dvd);
	}
	public void deleteDvd(String name) {
//		System.out.println("deleteDvd(String) of DvdService");
		dao.deleteByName(name);
	}
	public void marshall(String name)
	{
		dao.marshall(name);
	}
	public void unmarshall(String name)
	{
		dao.unmarshall(name);
	}
}
