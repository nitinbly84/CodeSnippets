package fileUpdaters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Below class is an example to open a file for both reading and writing.
 * In one thread we are reading the contents of the file & printing it to console.
 * In second thread we are writing the contents to the same file.
 * This one is also an example about how 2 threads communicate via wait() & notifyAll()
 * @author nitin
 *
 */
public class ReaderWriter {

	public String filePath = "D:\\test.txt";

	public static void main(String[] args) {
		ReaderWriter rw = new ReaderWriter();
		File file = new File(rw.filePath);
		Reader reader = new Reader(file);
		Writer writer = new Writer(file);
		Thread th1 = new Thread(reader);
		Thread th2 = new Thread(writer);
		th1.start();
		th2.start();
		
	}
}

class Reader implements Runnable {
	File file;
	public Reader(File file) {
		this.file = file;
	}
	public void run()
	{
		synchronized(file) {
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile(file, "rwd");
				String line = "";
				while(true) {
					while(line != null) {
						line = raf.readLine();
						if(line != null && line.length() != 0)
							System.out.println(line);
					}
					while((line = raf.readLine()) == null) {
						file.notifyAll();
						file.wait();
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

class Writer implements Runnable {
	File file;
	public Writer(File file) {
		this.file = file;
	}
	public void run()
	{
		synchronized(file) {
			int i = 0;
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile(file, "rwd");
				while(true) {
					raf.seek(file.length());
					raf.writeBytes("\nNew Line " + i++);
					for(int j = 0; j < 1000; j++) {
						file.notifyAll();
						file.wait();
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
