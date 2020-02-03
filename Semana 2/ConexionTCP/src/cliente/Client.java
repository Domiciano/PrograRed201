package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 5000);
			System.out.println("Cliente conextado!");
			
			
			String mensaje = "Hola desde el cliente";
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write(mensaje + "\n");
			bw.flush();
			
			
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			mensaje = br.readLine();
			System.out.println(mensaje);
			
			
			while(true) {}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
