package fileUpdaters;

/**
 * Below code is taken from https://howtodoinjava.com/java-8/java-8-watchservice-api-tutorial/
 * And I have added a few changes & some comments in that code.
 */
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class CustomWatchService {

	private final WatchService watcher;
	private final Map<WatchKey, Path> keys;

	/**
	 * Creates a WatchService and registers the given directory
	 */
	CustomWatchService(Path dir) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();

		walkAndRegisterDirectories(dir);
	}

	/**
	 * Register the given directory with the WatchService; This function will be called by FileVisitor
	 */
	private void registerDirectory(Path dir) throws IOException 
	{
		// While registering, one can give the list of events to be monitored, like below 3 kind of events will be monitored.
		// These events are the static members of class StandardWatchEventKinds.java which are imported above using import static.
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		keys.put(key, dir);
	}

	/**
	 * Register the given directory, and all its sub-directories, with the WatchService.
	 */
	private void walkAndRegisterDirectories(final Path start) throws IOException {
		// register directory and sub-directories
		SimpleFileVisitor<Path> sfv  = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				registerDirectory(dir);
				// Value of FileVisitResult as used below will decide on the next action to be taken, whether to look
				// into the children of this path or exit.
				return FileVisitResult.CONTINUE;
			}
		};
		// Have enclosed in try-catch block to avoid the failure of the code in case it some directory
		// for which it doesn't have access. In that case it will throw AccessDeniedException & will terminate
		// the program. So to avoid such termination due to such exception, using try-catch, now such issue
		// causing directories will be ignored for any monitoring.
		try {
			// Walks a file tree.
			// It will go through the file tree as per the behavior mentioned in the provided visitor. 
			Files.walkFileTree(start, sfv);
		} catch (Exception e) {

			// Checking if the provided path to monitor is same as given in exception message
			// then exit, as it means that provided path doesn't exist.
			if(e.getMessage().equalsIgnoreCase(start.toString())) {
				System.out.println("Exiting as the provided path doesn't exist");
				System.exit(1);
			}
			// Printing the name of directory which caused this exception.
			// Though you can do other stuff as per the requirements.
			System.out.println("Got one directory which will not be watched : " + e.getMessage());
			// return;
		}
	}

	/**
	 * Process all events for keys queued to the watcher
	 */
	void processEvents() {
		while(true) {

			// wait for key to be signaled
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			Path dir = keys.get(key);
			if (dir == null) {
				System.err.println("WatchKey not recognized!!");
				continue;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				@SuppressWarnings("rawtypes")
				WatchEvent.Kind kind = event.kind();

				// Context for directory entry event is the file name of entry
				@SuppressWarnings("unchecked")
				Path name = ((WatchEvent<Path>)event).context();
				Path child = dir.resolve(name);

				// print out event
				System.out.format("%s: %s\n", event.kind().name(), child);

				// if directory is created, and watching recursively, then register it and its sub-directories
				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(child)) {
							walkAndRegisterDirectories(child);
						}
					} catch (IOException x) {
						// do something useful
					}
				}
			}

			// reset key and remove from set if directory no longer accessible
			boolean valid = key.reset();
			if (!valid) {
				keys.remove(key);

				// all directories are inaccessible
				// Means all the paths being monitored don't exist now.
				if (keys.isEmpty()) {
					System.out.println("Terminating the program as no directory to monitor now");
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Path dir = Paths.get("E:\\Test\\");
		new CustomWatchService(dir).processEvents();
	}
}