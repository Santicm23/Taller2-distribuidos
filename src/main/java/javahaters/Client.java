package javahaters;

// ************************************************************
// Cliente.
// Pide la ejecución del método getTime,
// del objeto TimeServer.
// Para el cliente una vez cargado el objeto, lo trata com local
// ************************************************************

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Variable que recibirá la hora del servidor
        Float result = null;

        System.out.println("Introduce el primer valor: ");
        Float value1 = Float.parseFloat(scanner.nextLine());
        System.out.println("Introduce el segundo valor: ");
        Float value2 = Float.parseFloat(scanner.nextLine());
        System.out.println("Introduce el número de operación: ");
        String operation = scanner.nextLine();

        try {

            // conectarse al servidor y cargar registro de objetos RMI
            Registry registry = LocateRegistry.getRegistry(Constants.IP, 8080);

            // buscar el objeto timeServer en el registro,
            // y si lo encuentra, crear el objeto local
            MainServer MS = (MainServer)registry.lookup("MainServer");

            // usar el método getTime del objeto conectado.
            result = MS.requestOperation(value1, value2, operation);

        }
        catch (NotBoundException e) {
            System.out.println("Time Server no se encontró en el registro");
            System.exit(0);
        }
        catch (RemoteException e) {
            System.out.println("Time error: " + e);
            System.exit(0);
        }

        if (result != null) System.out.println("El resultado es: " + result);

    }

}
