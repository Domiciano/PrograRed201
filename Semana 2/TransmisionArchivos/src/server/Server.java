package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(5000);
			Socket socket = server.accept();
			System.out.println("Conexion Aceptada");
			
			
			File f = new File("D:/Usuarios/1143848922/Pictures/gamma.png");
			FileInputStream fis = new FileInputStream(f);
			OutputStream os = socket.getOutputStream();
			
			
			int bytesLeidos = 0;
			byte[] buffer = new byte[100];
			
			while(  (bytesLeidos = fis.read(buffer)) != -1  ) {
				os.write(buffer, 0, bytesLeidos);
			}
			fis.close();
			
			while(true) {}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
