package javahaters;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OpServer extends Remote {
    public Float operate(Float value1, Float value2, String operation) throws RemoteException;
}
