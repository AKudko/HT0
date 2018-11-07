import java.io.File;

public class MP3FileDescription {
	private String artist;
	private String album;
	private String name;
	private int duration;
	private long size;
	private File absoluteLocaition;
	
	
	public String getArtist() {
		return artist;
	}
	public File getAbsoluteLocaition() {
		return absoluteLocaition;
	}
	public void setAbsoluteLocaition(File absoluteLocaition) {
		this.absoluteLocaition = absoluteLocaition;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "MP3FileDescription [artist=" + artist + ", album=" + album + ", name=" + name + ", duration=" + duration
				+ ", size=" + size + "]";
	}
	
	
	
	
}
