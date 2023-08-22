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

public class TimeClient {

    public static void main(String[] args) {

        // Variable que recibirá la hora del servidor
        String time = null;

        try {

            // conectarse al servidor y cargar registro de objetos RMI
            Registry registry = LocateRegistry.getRegistry("10.195.40.82", 1099);

            // buscar el objeto timeServer en el registro,
            // y si lo encuentra, crear el objeto local
            TimeServer TS = (TimeServer)registry.lookup("timeServer");

            // usar el método getTime del objeto conectado.
            time = TS.getTime();

        }
        catch (NotBoundException e) {
            System.out.println("Time Server no se encontró en el registro");
            System.exit(0);
        }
        catch (RemoteException e) {
            System.out.println("Time error: " + e);
            System.exit(0);
        }

        if (time != null) System.out.println("La hora es: " + time);

    }

}
