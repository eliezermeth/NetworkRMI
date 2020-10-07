import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Eliezer Meth
 * Start Date: 2020-10-07
 *
 * Interface for RMI to test network conditions.
 */
public interface NetworkCommands extends Remote
{
    /**
     * Method to get System time in long of milliseconds.
     * @return long from System.nanoTime().
     * @throws RemoteException Exception
     */
    long ping() throws RemoteException;
}
