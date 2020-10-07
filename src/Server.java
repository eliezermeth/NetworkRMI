import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author Eliezer Meth
 * Start Date: 2020-10-07
 *
 * Server for RMI network commands.
 */
public class Server
{
    public static void main(String[] args)
    {
        try
        {
            // Create object
            NetworkCommands server = new NetworkTesting();

            // rmiregistry within the server JVM with port number 1900
            LocateRegistry.createRegistry(1900);

            // Binds the remote object with the name network
            Naming.rebind("rmi://localhost:1900/network", server);

            // Confirm server started.
            System.out.println("Server started.");
        }
        catch (RemoteException | MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
}
