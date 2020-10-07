import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Eliezer Meth
 * Start Date: 2020-10-07
 *
 * Client for RMI network commands.
 */
public class Client
{
    public static void main(String[] args)
    {
        try
        {
            final int NUM_ATTEMPTS = 11; // WARNING: If too large; will cause overflow and erroneous numbers
            long internalAverage, networkAverage;

            // Test internal latency
            long[] internalLatency = new long[NUM_ATTEMPTS];
            for (int i = 0; i < NUM_ATTEMPTS; i++)
            {
                // Get two System.nanoTime()s, find difference, and get absolute value
                internalLatency[i] = Math.abs(System.nanoTime() - System.nanoTime());
            }
            System.out.println("Internal latency (ns):");
            internalAverage = getAverageAndPrintMinMax(internalLatency);
            System.out.println("Average: " + internalAverage);

            System.out.println();

            // Test network latency
            // lookup method to find reference of remote object
            NetworkCommands network = (NetworkCommands) Naming.lookup("rmi://localhost:1900/network");

            long[] networkLatency = new long[NUM_ATTEMPTS];
            for (int i = 0; i < NUM_ATTEMPTS; i++)
            {
                // Get one System.nanoTime() from this system and one from network, find difference, and get absolute value
                networkLatency[i] = Math.abs(System.nanoTime() - network.ping());
            }
            System.out.println("Network latency (ns):");
            networkAverage = getAverageAndPrintMinMax(networkLatency);
            System.out.println("Average: " + networkAverage);

            System.out.println();
            System.out.println("Difference between internal and network latency (ns): " +
                    (networkAverage - internalAverage));
        }
        catch (NotBoundException | MalformedURLException | RemoteException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to print min and max of long[], and return average
     * @param array long[]
     * @return Average of long[].
     */
    public static long getAverageAndPrintMinMax(long[] array)
    {
        long accumulating = 0; // accumulating total
        long max = Long.MIN_VALUE, min = Long.MAX_VALUE; // initialize to opposite side
        for (long num : array)
        {
            if (num > max)
                max = num;
            else if (num < min)
                min = num;

            accumulating += num;
        }

        // Print min and max
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

        return accumulating / array.length; // return average
    }
}
