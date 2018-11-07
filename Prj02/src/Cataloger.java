import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Cataloger {

	public static void main(String[] args) {
		// проверить что есть два элемента в массиве
		//String[] args1 = { "D:\\dir1", ".mp3" };
		File file = new File(args[0]);
		String extension = args[1].toLowerCase();
		

		if (file.exists() && file.isDirectory()) {
			List<File> listOfMP3 = new ArrayList<File>();
// создание списка файлов с указанным расширением
			chechingCatalog(file, listOfMP3, extension);

			System.out.println("Findig mp3 files is done!");
// создаем и наполняем список описаний файлов			
			List<MP3FileDescription> listMP3FilesDescription = new ArrayList<MP3FileDescription>();

			try {
				createListMP3FilesDescription(listOfMP3, listMP3FilesDescription);
			} catch (CannotReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
// сортировка списка по исполнителю а затем по альбому			
			sortMP3FileDescription(listMP3FilesDescription);
			
// содание html файла
			
			File outHTML =  new File(args[0]);
			
			String HTM_DOC_STARTING = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"ru\">\r\n" + 
					"<head>\r\n" + 
					"    <title>List of music</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n";
			String HTM_DOC_ENDING = "</body>\r\n" + 
					"</html>";
			
			FileWriter writer;
			try {
				writer = new FileWriter((outHTML+"\\myMusic.html"), false);
				writer.write(HTM_DOC_STARTING);
				writer.write("<ul>");
				for(int i = 0; i < listMP3FilesDescription.size(); i++)
				{
					writer.write("<li>" + listMP3FilesDescription.get(i).getArtist() + "\r\n" + 
							"				<ul>\r\n" + 
							"					<li>" + listMP3FilesDescription.get(i).getAlbum() +"\r\n" + 
							"						<ul>\r\n" + 
							"							<li>" + listMP3FilesDescription.get(i).getName() + "_" + listMP3FilesDescription.get(i).getDuration() + "_(<a href=\"file:///" + listMP3FilesDescription.get(i).getAbsoluteLocaition() + "\">" + listMP3FilesDescription.get(i).getName() + "</a>)</li>\r\n" + 
							"						</ul>\r\n" + 
							"					</li>\r\n" + 
							"				</ul>\r\n" + 
							"			</li>");
				}
				writer.write("</ul>");
				writer.write(HTM_DOC_ENDING);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
				
				
			
			
			
			
			for(int i=0; i<listMP3FilesDescription.size(); i++) {
				System.out.println(listMP3FilesDescription.get(i));
			}

		} else {
			System.out.println("Catalog " + args[0] + " does not exist. \n "
					+ "Make sure, that you entered right directory and try again, please");
		}

	}

	// --------------------------------------------------------------------
	private static void chechingCatalog(File file, List<File> listOfMP3, String extention) {
		File[] listFilesOfDir = file.listFiles();

		for (int i = 0; i < listFilesOfDir.length; i++) {
			if (listFilesOfDir[i].isDirectory()) {
				file = new File(listFilesOfDir[i].getPath());
				chechingCatalog(file, listOfMP3, extention);
			} else if (listFilesOfDir[i].isFile()) {
				if (((listFilesOfDir[i].getName().toLowerCase().lastIndexOf(extention) != -1)
						&& listFilesOfDir[i].getName().toLowerCase().contains(extention))) {
					listOfMP3.add(listFilesOfDir[i]);

				}

			} else {
				System.out.println("something wrong with file/directory");
			}
		}

	}

	private static void createListMP3FilesDescription
			(List<File> listOfMP3, List<MP3FileDescription> listMP3FilesDescription )
			throws CannotReadException, IOException, TagException, ReadOnlyFileException,
			InvalidAudioFrameException {
	
		for(int i=0 ; i < listOfMP3.size(); i++ ) {
				
		AudioFile f = AudioFileIO.read(listOfMP3.get(i));
		Tag tag = f.getTag();
		MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
		
		MP3FileDescription mp3FileDescription = new MP3FileDescription();
		
		mp3FileDescription.setArtist(tag.getFirst(FieldKey.ARTIST));
		mp3FileDescription.setAlbum(tag.getFirst(FieldKey.ALBUM));
		mp3FileDescription.setName(listOfMP3.get(i).getName());
		mp3FileDescription.setDuration(audioHeader.getTrackLength());
		mp3FileDescription.setSize(listOfMP3.get(i).length());
		mp3FileDescription.setAbsoluteLocaition(listOfMP3.get(i));
		
		listMP3FilesDescription.add(mp3FileDescription);
	
		}
		
	}

	private static void sortMP3FileDescription (List<MP3FileDescription> listMP3FilesDescription) {
		Collections.sort(listMP3FilesDescription, new Comparator<MP3FileDescription>() {
			public int compare(MP3FileDescription listMP3FilesDescription1,
					MP3FileDescription listMP3FilesDescription2) {
				int result =String.valueOf(listMP3FilesDescription1.getArtist()).compareTo(listMP3FilesDescription2.getArtist());
				if(result == 0) {
					result =String.valueOf(listMP3FilesDescription1.getAlbum()).compareTo(listMP3FilesDescription2.getAlbum());
				}
				return result;
				}
				
			});
	}
	
	
}
