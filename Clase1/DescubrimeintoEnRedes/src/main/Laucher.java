package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class Laucher {

	public static void main(String[] args) {
		
		try {
			Enumeration<NetworkInterface> interfaces =  NetworkInterface.getNetworkInterfaces();
			
			while(interfaces.hasMoreElements()) {
				NetworkInterface interN =  interfaces.nextElement();
				
				if(  interN.isUp()  ) {
					System.out.println( interN.getName() );
					
					List<InterfaceAddress> addresses =  interN.getInterfaceAddresses();
					for(int i=0 ; i<addresses.size() ; i++) {
						System.out.println(   addresses.get(i).getAddress().getHostAddress() ) ;
					}
					
					
					
				}
				
			}
			
			InetAddress miDireccion = InetAddress.getLocalHost();
			System.out.println( miDireccion.getHostAddress() );
			
			NetworkInterface net = NetworkInterface.getByInetAddress(miDireccion);
			System.out.println("Prefijo: "+net.getInterfaceAddresses().get(0).getNetworkPrefixLength() );
			
			
			InetAddress nelson = InetAddress.getByName("172.30.172.31");
			System.out.println( nelson.isReachable(1500) ); //IMCP
			
			
			
			
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
