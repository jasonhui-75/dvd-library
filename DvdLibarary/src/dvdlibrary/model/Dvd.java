package dvdlibrary.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Dvd implements Serializable {
	private String title;
	private LocalDate releaseDate;
	private int rating;
	private String directorName;
	private String studio;
	private String userNote;
	
	
	public Dvd() {
		super();
	}
	public Dvd(String title, LocalDate releaseDate, int rating, String directorName, String studio, String userNote) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.directorName = directorName;
		this.studio = studio;
		this.userNote = userNote;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getUserNote() {
		return userNote;
	}
	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}
	@Override
	public String toString() {
		return "Dvd [title=" + title + ", releaseDate=" + releaseDate + ", rating=" + rating + ", directorName="
				+ directorName + ", studio=" + studio + ", userNote=" + userNote + "]";
	}
	
}
