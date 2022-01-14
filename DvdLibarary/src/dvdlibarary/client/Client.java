package dvdlibarary.client;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dvdlibrary.controller.DvdController;
import dvdlibrary.model.Dvd;

public class Client {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);
		Dvd d1 = ac.getBean("d1", Dvd.class);
		Dvd d2 = ac.getBean("d2", Dvd.class);
		Dvd d3 = new Dvd("Encanto",LocalDate.of(2021, Month.NOVEMBER , 24), 7, "Byron Howard", "Disney", "Just another disney movie");	
		Dvd d4 = new Dvd("The Matrix Resurrections",LocalDate.of(2021, Month.DECEMBER, 22), 9, "Lana Wachowski", "20th Century Studios", "Bad director, bad movie");
		Dvd d5 = new Dvd("Ghostbusters: Afterlife ",LocalDate.of(2021, Month.NOVEMBER, 19), 2, "Jason Reitman", "20th Century Studios", "Best Ghostbusters ever!");
		
//		System.out.println(d1);
//		System.out.println(d2);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(".\\src\\vm.ser");
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			oos.writeObject(d3);
			oos.writeObject(d4);
			oos.writeObject(d5);
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
		System.out.println("Generated ser file in directory src");
		//vdController dc = new DvdController();
		DvdController dc = ac.getBean("controller", DvdController.class);
		//System.out.println("dc:  "+ dc);
		String unmarshall = ".\\src\\dvd.ser";
		System.out.println("\nUnmarshalling");
		System.out.println(dc.getAll());
		dc.unmarshall(unmarshall);
		System.out.println(dc.getAll());
		
		
		System.out.println("\nAdd");
		System.out.println(dc.getAll());
		dc.save(d1);
		dc.save(d2);
		System.out.println(dc.getAll());
		String oldTitle = d1.getTitle();
		Dvd edit = d1;
		edit.setTitle("New Guy");
		System.out.println("\nGet All");
		System.out.println(dc.getAll());
		
		System.out.println("\nGet by title");
		System.out.println(dc.getByName(d2.getTitle()));
	
		System.out.println("\nDisplay");
		System.out.println(dc.display(d2.getTitle()));
		
		System.out.println("\nUpdate Dvd");
//		System.out.println(edit);
		dc.update(oldTitle, edit);
		System.out.println(dc.getAll());
		System.out.println("\nDelete Dvd");
		dc.delete(d1.getTitle());
		System.out.println(dc.getAll());
		
		String marshall = ".\\src\\dvdmarshall.ser";
		System.out.println("\nMarshalling");
		dc.marshall(marshall);
	}

}
