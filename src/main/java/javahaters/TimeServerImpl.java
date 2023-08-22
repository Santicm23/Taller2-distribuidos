package javahaters;

// **************************************
// Servidor de Hora.
// Los clientes se conectan y ejecutan el método getTime()
//
// ****************************************************************

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TimeServerImpl extends UnicastRemoteObject implements TimeServer {

    protected TimeServerImpl() throws RemoteException {
        System.out.println("Arrancando Servidor de hora: timeserver");
    }

    public String getTime() throws RemoteException {
        System.out.println("Hora Enviada...");
        return new java.util.Date().toString();
    }


    // Arranque del Servidor de Hora

    public static void main(String[] args) {


        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            TimeServerImpl TSI = new TimeServerImpl();

            // Incluir el objeto en el registro del RMI en el puerto 1099,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("timeServer", TSI);
            System.out.println("Objeto -timeServer- Registrado en el RMI");

        } catch (RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
}
