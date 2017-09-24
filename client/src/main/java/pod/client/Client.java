package pod.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pod.ExampleService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws RemoteException, InterruptedException, NotBoundException {
        logger.info("pod Client Starting ...");

        final Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        final ExampleService service = (ExampleService) registry.lookup("service");
        while (true) {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(400);
                service.visit();
            }
            System.out.println(service.getVisits());
        }
    }
}
