package main;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			
			while(interfaces.hasMoreElements()) {
				NetworkInterface interfaceN = interfaces.nextElement();
				if(interfaceN.isUp()) {
					System.out.println(interfaceN.getName());
					
					List<InterfaceAddress> addresses = interfaceN.getInterfaceAddresses();
					for(int i=0 ; i<addresses.size() ; i++) {
						System.out.println("   "+addresses.get(i).getAddress().getHostAddress());
					}
					
				}
			}
			
			InetAddress myAddress = InetAddress.getLocalHost();
			System.out.println(myAddress.getHostAddress());
			
			InetAddress cesar = InetAddress.getByName("172.30.149.86");
			boolean cesarIsUp = cesar.isReachable(5000);
			System.out.println("Estado de César: alcanzable: "+cesarIsUp);
			
			InetAddress youtube = InetAddress.getByName("www.icesi.edu.co");
			System.out.println("Youtube: "+youtube.getHostAddress());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
