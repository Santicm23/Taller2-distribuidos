package javahaters;

// **************************************
// Servidor de Hora.
// Los clientes se conectan y ejecutan el método getTime()
//
// ****************************************************************

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainServerImpl extends UnicastRemoteObject implements MainServer {

    protected MainServerImpl() throws RemoteException {
        System.out.println("Arrancando Servidor de hora: timeserver");
    }

    public Float requestOperation(Float value1, Float value2, String operation) throws RemoteException, NotBoundException {
        Registry registry;
        OpServer OS;
        switch (operation) {
            case "1":
                try {
                    registry = LocateRegistry.getRegistry(Constants.IP, 8081);
                    OS = (OpServer)registry.lookup("OpServer1");
                } catch (RemoteException e) {
                    registry = LocateRegistry.getRegistry(Constants.IP, 8082);
                    OS = (OpServer)registry.lookup("OpServer2");
                }
                break;
            case "2":
                try {
                    registry = LocateRegistry.getRegistry(Constants.IP, 8082);
                    OS = (OpServer)registry.lookup("OpServer2");
                } catch (RemoteException e) {
                    registry = LocateRegistry.getRegistry(Constants.IP, 8081);
                    OS = (OpServer)registry.lookup("OpServer1");
                }
                break;
            default:
                return null;
        }
        return OS.operate(value1, value2, operation);
    }


    // Arranque del Servidor de Hora

    public static void main(String[] args) {


        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            MainServerImpl MSI = new MainServerImpl();

            // Incluir el objeto en el registro del RMI en el puerto 8080,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind("MainServer", MSI);
            System.out.println("Objeto -MainServer- Registrado en el RMI");

        } catch (RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
}
