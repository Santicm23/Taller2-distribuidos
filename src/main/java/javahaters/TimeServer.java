package javahaters;

import java.rmi.Remote;

public interface TimeServer extends Remote {
    public String getTime() throws java.rmi.RemoteException;
}

