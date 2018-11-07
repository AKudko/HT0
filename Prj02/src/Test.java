import java.io.File;
import java.io.IOException;



import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Test {

	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		File testFile = new File("d:\\Maks_Korzh-Letay_so_mnoy_v_temnote.mp3");
		
		File testFile2 = new  File ("d:\\Forever Young.mp3.mp3");
	
		
		AudioFile f = AudioFileIO.read(testFile2);
		Tag tag = f.getTag();
		MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
		

	System.out.println(tag.getFirst(FieldKey.ARTIST));
	System.out.println(tag.getFirst(FieldKey.ALBUM));

	System.out.println(audioHeader.getTrackLength());


	}

}
