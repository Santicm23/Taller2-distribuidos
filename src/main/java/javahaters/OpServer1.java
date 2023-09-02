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

public class OpServer1 extends UnicastRemoteObject implements OpServer {

    protected OpServer1() throws RemoteException {
        System.out.println("Arrancando Servidor de Cálculo: OpServer1");
    }

    public Float operate(Float value1, Float value2, String operation) throws RemoteException {
        return switch (operation) {
            case "1" -> Operations.operation1(value1, value2);
            case "2" -> Operations.operation2(value1, value2);
            default -> null;
        };
    }


    // Arranque del Servidor de Hora

    public static void main(String[] args) {
        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            OpServer1 TSI = new OpServer1();

            // Incluir el objeto en el registro del RMI en el puerto 8081,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry(8081);
            registry.rebind("OpServer1", TSI);
            System.out.println("Objeto -OpServer1- Registrado en el RMI");

        } catch (RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
}
