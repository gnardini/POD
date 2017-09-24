package pod;

import java.io.*;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;

public class ExampleServiceImpl implements ExampleService {

    private int visits;
    private File storage;

    public ExampleServiceImpl(ActivationID id, MarshalledObject data) throws RemoteException {

        System.out.println("constructor");
        if (data != null) {
            try {
                storage = (File) data.get(); // Ej: storage es de tipo File
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (storage.exists()) {
                read(); // recuperar el estado interno
            } else {
                System.out.println("No habia store lo genero...");
                store();
            }
        }
        Activatable.exportObject(this, id, 0);
    }

    @Override
    public void visit() throws RemoteException {
        visits++;
        store();
    }

    @Override
    public int getVisits() throws RemoteException {
        return visits;
    }

    private void read() {
        if (storage != null) {
            System.out.println("Leyendo del storage");
            try (ObjectInputStream ois = new ObjectInputStream(new
                    FileInputStream(storage))) {
                visits = ois.readInt();
            } catch (final IOException e) {
                System.err.println("error al leer el storage " + e.toString());
            }
        }
    }

    private void store() {
        if (storage != null) {
            System.out.println("Guardando en el storage");
            try (ObjectOutputStream oos = new ObjectOutputStream(new
                    FileOutputStream(storage))) {
                oos.writeInt(visits);
                oos.flush();
            } catch (final IOException e) {
                System.out.println("error al escribir el storage " + e.toString());
            }
        }
    }
}
