package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 5000);
			System.out.println("Conexion Aceptada");
			
			File f = new File("D:/Usuarios/1143848922/Pictures/delta.png");
			InputStream is = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream(f);
			
			
			int bytesLeidos = 0;
			byte[] buffer = new byte[100];
			
			while(  (bytesLeidos = is.read(buffer)) != -1  ) {
				fos.write(buffer, 0, bytesLeidos);
			}
			fos.close();
			
			while(true) {}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
