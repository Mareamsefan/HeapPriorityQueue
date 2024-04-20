public class Process
{
    String name;   // Prosessnavn
    int priority;  // Prosesser med hÃ¸y prioritet skal kjÃ¸res fÃ¸rst
    int runTime;   // CPU-tiden som prosessen trenger fÃ¸r den er ferdig

    public Process(String name, int priority, int runTime)
    {
        this.name = name;
        this.priority = priority;
        this.runTime = runTime;
    }
}