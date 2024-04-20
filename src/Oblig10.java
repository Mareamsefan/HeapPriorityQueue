
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Oblig10
{
    public static PriorityQueue<Process> read(String fileName)
    {
        /*** Skal programmeres i oppgave 1 ***/
        ProcessComparator pC = new ProcessComparator();
        PriorityQueue<Process> pQ = new PriorityQueue<Process>(pC);
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Filen " + fileName + " finnes ikke.");
            return null;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int priority = Integer.parseInt(parts[1].trim());
                    int runTime = Integer.parseInt(parts[2].trim());

                    pQ.add(new Process(name, priority, runTime));

                } else {
                    System.out.println("Ugyldig dataformat i filen.");
                }
            }

        } catch (IOException e) {
            System.out.println("Feil ved åpning av filen: " + e.getMessage());
        }
        return pQ;
    }

    public static void run(PriorityQueue<Process> pQ)
    {
        /*** Skal programmeres i oppgave 2 ***/
        int currentTime = 0;
        int totalTurnaroundTime = 0;
        int numProcesses = pQ.size();
        int startTime = currentTime;

        System.out.println("Pri.\tCPU\t\tStart\tEnd\t\tName");

        while (!pQ.isEmpty()) {
            Process process = pQ.poll();
            int endTime = currentTime + process.runTime;
            int turnaroundTime = endTime - startTime;

            System.out.println(process.priority + "\t\t" + process.runTime + "\t\t" +
                    startTime + "\t\t" + endTime + "\t\t" + process.name);

            currentTime = endTime;

            totalTurnaroundTime += turnaroundTime;
        }

        float avgTurnaround = (float) totalTurnaroundTime / numProcesses;

        System.out.println("\nAverage turnaround: " + avgTurnaround);
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("File? ");
        String fileName = scan.nextLine();

        PriorityQueue<Process> pQ = read(fileName);
        System.out.println("Read " + pQ.size() + " processes from " + fileName);

        run(pQ);

    }
}