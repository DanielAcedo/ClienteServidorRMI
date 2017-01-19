package dam.psp;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CirculoCLI {
	public static String SRV = "192.168.3.57";
	public static int PORT = 8888;
	
	public CirculoCLI() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		ICirculo circulo = null;
		
		System.out.println("Localizando en la red el registro de objetos remotos...");
		
		Registry registro;
		try {
			registro = LocateRegistry.getRegistry(SRV, PORT);
			circulo = (ICirculo)registro.lookup("Circulo");
			
			if(circulo != null){
				circulo.set_radio(20);
				System.out.println("Longitud: "+circulo.longitud()+"\nArea: "+circulo.area());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
}
