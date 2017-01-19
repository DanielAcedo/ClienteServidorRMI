package dam.psp;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class CirculoSRV implements ICirculo {

	private final double PI = 3.14;
	private double _radio;
	
	public CirculoSRV(Registry registro) {
		System.out.println("Creando objeto circulo y su inscripción en el registro");
		try {
			registro.bind("Circulo", (ICirculo)UnicastRemoteObject.exportObject(this, 0));
			System.out.println("FInalizado el registro del objeto en remoto");
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void set_radio(double radio) throws RemoteException {
		this._radio = radio;
	}

	@Override
	public double area() throws RemoteException {
		return PI * Math.pow(_radio, 2);
	}

	@Override
	public double longitud() throws RemoteException {
		// TODO Auto-generated method stub
		return 2 * PI * _radio;
	}

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		final int puerto = 8888;
		System.setProperty("java.rni.server.hostname", "192.168.3.61");
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		Registry registro = LocateRegistry.createRegistry(puerto);
		
		new CirculoSRV(registro);
	}

}
