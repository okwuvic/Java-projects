package assignmentUnit7;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class FileServer {

	private static ServerSocket servSocks;

	public static void main(String[] args) throws IOException {
		servSocks = new ServerSocket(80);
		while (true) {
			try {
				Socket soc = servSocks.accept();
				new clientHand(soc);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

class clientHand extends Thread {
	private Socket socket;

	public clientHand(Socket soc) {
		socket = soc;
		start();
	}

	public void run() {
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream pst = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			String soc = buff.readLine();
			System.out.println(soc);
			String fName = "";
			StringTokenizer st = new StringTokenizer(soc);
			try {
				if (st.hasMoreElements() && st.nextToken().equalsIgnoreCase("GET") && st.hasMoreElements())

					fName = st.nextToken();
				else
					throw new FileNotFoundException();
				if (fName.endsWith("/"))
					fName += "index.html";
				while (fName.indexOf("/") == 0)
					fName = fName.substring(1);
				fName = fName.replace('/', File.separator.charAt(0));
				if (fName.indexOf("..") >= 0 || fName.indexOf(':') >= 0 || fName.indexOf('|') >= 0)

					throw new FileNotFoundException();
				if (new File(fName).isDirectory()) {
					fName = fName.replace('\\', '/');
					pst.print("HTTP/1.0 301 Moved Permanently\r\n" + "Location: /" + fName + "/\r\n\r\n");
					pst.close();
					return;
				}

				InputStream ist = new FileInputStream(fName);
				String mmTyp = "text/plain";
				if (fName.endsWith(".html") || fName.endsWith(".htm"))
					mmTyp = "text/html";
				else if (fName.endsWith(".jpg") || fName.endsWith(".jpeg"))
					mmTyp = "image/jpeg";
				else if (fName.endsWith(".gif"))
					mmTyp = "image/gif";
				else if (fName.endsWith(".class"))
					mmTyp = "application/octet-stream";
				pst.print("HTTP/1.0 200 OK\r\nob" + "Content-type: " + mmTyp + "\r\nob\r\n");
				byte[] b = new byte[4096];
				int nob;
				while ((nob = ist.read(b)) > 0)
					pst.write(b, 0, nob);
				pst.close();
			} catch (FileNotFoundException e) {
				pst.println("HTTP/1.0 404 Not Found\r\n" + "Content-type: text/html\r\n\r\n"
						+ "<html><head></head><body>" + fName + " not found</body></html>\n");
				pst.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
