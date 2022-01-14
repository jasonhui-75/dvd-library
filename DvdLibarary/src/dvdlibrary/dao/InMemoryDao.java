package dvdlibrary.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import dvdlibrary.model.Dvd;


@Repository
public class InMemoryDao  {
	private static List<Dvd> dvdList = new ArrayList<Dvd>();
	private static Map<String, Dvd> dvdMap = new TreeMap<String,Dvd>();
	static {
//		users.add(new User(10, "Amit", new Date()));
	}
	public	Map<String, Dvd> findAll() {
//		System.out.println("Inside findAll() of UserInMemoryDao");
		return dvdMap;
	}
	public Optional<Dvd> findById(String name) {
//		System.out.println("Inside findById(String) of UserInMemoryDao");
		Dvd dvd = dvdMap.get(name);
		Optional<Dvd> opt = Optional.ofNullable(dvd);
		return opt;

	}
	public void save(Dvd dvd) {
//		System.out.println("Save(Dvd) of UserInMemoryDao");
		dvdMap.put(dvd.getTitle(), dvd);
	}
	public void deleteByName(String name){
//		System.out.println("deleteByName(String) of Dao");
		dvdMap.remove(name);
	}
	public void update(String name, Dvd dvd) {
//		System.out.println("update(String, Dvd) of Dao");
//		System.out.println(name);
		if(dvdMap.containsKey(name)) {
			dvdMap.remove(name);
//			System.out.println("putting "+dvd);
			dvdMap.put(dvd.getTitle(), dvd);
			
		}
		
	}
	
	public void marshall(String name)
	{
//		System.out.println("marshalling in dao");
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(name);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			for(Dvd d: dvdMap.values()) {
				oos.writeObject(d);;
				
			}
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}catch(IOException e) {
			System.out.println("IO exception");
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				System.out.println("IO exception while closing file");
			}
		}
	}
	public void unmarshall(String name) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(name);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			for(;;) {
				Object obj = null;
				try{
					obj = ois.readObject();
					if (obj instanceof Dvd) {
						Dvd dvd = (Dvd) obj;
						dvdMap.put(dvd.getTitle(), dvd);
					}
				}catch(IOException e) {
					break;
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}catch(IOException e) {
			System.out.println("IO exception");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				System.out.println("IO exception while closing file");
			}
		}
	}
//	public List<Dvd> findAll() {
//		System.out.println("Inside findAll() of UserInMemoryDao");
//		return dvdList;
//	}


//	public User save(User user) {
//		System.out.println("Save() of UserInMemoryDao");
//		// user.setId(++userCount);
//		users.add(user);
//		return user;
//	}

//	public User deleteById(int id) {
//		System.out.println("deleteById of Dao");
//		Iterator<User> itr = users.iterator();
//		while (itr.hasNext()) {
//			User u = itr.next();
//			if (u.getId() == id) {
//
//				users.remove(u);
//				return u;
//			}
//		}
//		return null;
//	}

	

	

}
