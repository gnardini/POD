package pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ExampleService extends Remote {

    void visit() throws RemoteException;

    int getVisits() throws RemoteException;
}
