import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Main class the runs the program to find the best combination of Machines to produce the minimum waste
 * given the input and output file names as arguments
 */
public class Main {

  public static void main(String[] args) {

    Args jArgs = new Args();
    JCommander helloCmd = JCommander.newBuilder()
        .addObject(jArgs)
        .build();
    helloCmd.parse(args);

    KChipsetsProducerProblem.findMachinesWithMinimumWaste(jArgs.inputFile, jArgs.outputFile);
  }

  private static final class Args {
    @Parameter(
        names = {"--input", "-i"},
        description = "input file",
        required = true
    )
    private String inputFile;

    @Parameter(
        names = {"--output", "-o"},
        description = "output file",
        required = true
    )
    private String outputFile;
  }
}
