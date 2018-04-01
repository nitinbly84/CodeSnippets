package fileUpdaters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

/**
 * Below file is tested for image files but it should work for all the file which are in byte format.
 * So it should work for any file in byte format like audio or video files also.
 * @author nitin
 *
 */
public class Base64Image {
	
	public static void main(String[] args) throws IOException {
	      Base64.Encoder enc = Base64.getEncoder();
	      Base64.Decoder dec = Base64.getDecoder();
	      FileOutputStream fos = new FileOutputStream("E:\\Base64_Test\\encoded\\Video.mp4");
	      Path encodedPath = Paths.get("E:\\Base64_Test\\encoded\\Video.mp4");
	      Path decodedPath = Paths.get("E:\\Base64_Test\\decoded\\Video.mp4");
	      Path originalPath = Paths.get("E:\\Base64_Test\\Video.mp4");
	      String encodedString = enc.encodeToString(Files.readAllBytes(originalPath));
	      fos.write(encodedString.getBytes());
	      fos.close();
//	      System.out.println(encodedString);
	      FileInputStream fis = new FileInputStream("E:\\Base64_Test\\encoded\\Video.mp4");
	      Files.copy(dec.wrap(fis), decodedPath, StandardCopyOption.REPLACE_EXISTING);
	      fis.close();

	}

}
