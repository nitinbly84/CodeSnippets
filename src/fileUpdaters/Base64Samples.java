package fileUpdaters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

/**
 * Tested below code for xlsx, txt & docx & working but not working image files.
 * @author nitin
 *
 */
public class Base64Samples {

	public static void main(String[] args) {
		Path originalPath = Paths.get("E:\\Base64_Test\\encoded");
		String sourceFileName = "Encoded.docx";
		String pathSeparator = "\\";
		originalPath = originalPath.resolve(originalPath.toString() + pathSeparator + sourceFileName);
		String targetFileName = "Decoded.docx";
		Path targetPath = Paths.get("E:\\Base64_Test\\decoded");
		if(targetFileName.isEmpty()) {
			targetPath = targetPath.resolve(targetPath.toString() + pathSeparator + sourceFileName);
		} else {
			targetPath = targetPath.resolve(targetPath.toString() + pathSeparator + targetFileName);
		}
		
		try {
			// copies the file to the target location 
			Files.copy(originalPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
			// moves the source file to the target location as an atomic operation
			// Files.move(originalPath, targetPath, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Using 1.7 try block to auto-close the opened streams, though you can also go in old try block way also
		try(//OutputStream output = Files.newOutputStream(targetPath); // If you leave this line uncommented while writing to same target
				//then you will get AccessDeniedException as stream is already opened for that target.
				InputStream input = Files.newInputStream(originalPath)){
			
			Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
			Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
		    //Copy the encoded file content to target file
			//Files.copy(originalPath, mimeEncoder.wrap(output));
			// Copy the decoded file to target
		    Files.copy(mimeDecoder.wrap(input), targetPath, StandardCopyOption.REPLACE_EXISTING);
		    //Or simply use the encoded output stream if want to work on stream only.
//		    OutputStream encodedStream = mimeEncoder.wrap(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
