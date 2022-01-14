package dvdlibrary.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import dvdlibrary.model.Dvd;
import dvdlibrary.service.DvdService;

@Controller
public class DvdController {
	
	@Autowired
	private DvdService service;
	
	public Dvd getByName(String name)
	{
//		System.out.println("getByName(String) of DvdController");
		return service.getDvdByName(name);
	}

	public String display(String name)
	{
//		System.out.println("display(String) of DvdController");
		Dvd d = this.getByName(name);
		return d.getTitle() +" directed by "+ d.getDirectorName() ;
	}
	public String getAll() {
//		System.out.println("service in controller: " + service);
//		System.out.println("getAll() of DvdController");
		Map map = service.getAllDvd();
		if(map == null)
		{
			return "Database is not initailzed yet";
		}
		else
		{
			String result = "Dvd collection:[\n";
			Set<Map.Entry<String, Dvd>> set = map.entrySet();
			for(Map.Entry<String, Dvd> e:set)
			{
				result += e.getKey() +" directed by " + e.getValue().getDirectorName() +"\n";
			}
			result+="]";
			return result;
		}
	}
	
	public void save(Dvd dvd)
	{
//		System.out.println("add(Dvd) of DvdController");
		service.saveDvd(dvd);
	}
	
	public void delete(String name)
	{
//		System.out.println("delete(String) of DvdController");
		service.deleteDvd(name);
	}
	public void update(String name, Dvd dvd)
	{
//		System.out.println("update(String, Dvd) of DvdController");
		service.updateDvd(name, dvd);
	}
	public void marshall(String name) {
		service.marshall(name);
	}
	public void unmarshall(String name) {
		service.unmarshall(name);
	}
}
