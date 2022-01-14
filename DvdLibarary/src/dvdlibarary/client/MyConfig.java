package dvdlibarary.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dvdlibrary.controller.DvdController;
import dvdlibrary.dao.InMemoryDao;
import dvdlibrary.model.Dvd;
import dvdlibrary.service.DvdService;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "dvdlibarary")
public class MyConfig {
	@Bean("controller")
	public DvdController getController() {
		return new DvdController();
	}
	@Bean("service")
	public DvdService getService() {
		return new DvdService();
	}
	@Bean("dao")
	public InMemoryDao getDao() {
		return new InMemoryDao();
	}
	@Bean(name="d1")
	@Primary
	public Dvd getDvd1() {
		return new Dvd("Free Guy",LocalDate.of(2021, Month.AUGUST, 13), 9, "Shawn Levy", "20th Century Studios", "Family friendly comedy");
	}
	@Bean(name="d2")
	public Dvd getAddress2() {
		return new Dvd("Spider-Man: No Way Home",LocalDate.of(2021, Month.DECEMBER, 15), 5, "Jon Watts", "Marvel Studio", "The worst spider-man movie");
		
	}
}
