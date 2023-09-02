package javahaters;

import java.rmi.NotBoundException;
import java.rmi.Remote;

public interface MainServer extends Remote {
    public Float requestOperation(Float value1, Float value2, String operation) throws java.rmi.RemoteException, NotBoundException;
}

