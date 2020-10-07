import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Eliezer Meth
 * Start Date: 2020-10-07
 *
 * Class to implement interface for RMI for network commands.
 */
public class NetworkTesting extends UnicastRemoteObject implements NetworkCommands
{
    /**
     * Constructor for RMI for network commands.
     * @throws RemoteException Exception
     */
    public NetworkTesting() throws RemoteException
    {
        super();
    }

    /**
     * Method to return long of system time.
     * @return long of system time of nanoseconds.
     * @throws RemoteException Exception
     */
    @Override
    public long ping() throws RemoteException
    {
        return System.nanoTime();
    }
}
