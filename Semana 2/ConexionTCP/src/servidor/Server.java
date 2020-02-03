package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("...Esperando...");
			Socket socket = server.accept();
			System.out.println("...Conexion Aceptada!");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String mensaje = br.readLine();
			System.out.println(mensaje);
			
			
			
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("Hola desde el servidor" + "\n");
			bw.flush();
			
			
			while(true) {}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
