import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

  private FileUtils() {
    //do nothing
  }

  public static InputInfo extractInfo(String filePath) {

    try {
      final List<String> lines = Files.readAllLines(Paths.get(filePath));

      final int numberOfMachines = Integer.parseInt(lines.get(0).trim());
      final String machinesString = lines.get(1).trim();
      final int chipsetsNeededPerMinute = Integer.parseInt(lines.get(2).trim());

      final int[] machines = Arrays.stream(machinesString.split("\\s+"))
          .mapToInt(Integer::parseInt)
          .toArray();

      return new InputInfo(machines, chipsetsNeededPerMinute);
    } catch (IOException e) {
      throw new ChipsetException("unable to close file with path: " + filePath, e);
    }
  }

  public static void outputToFile(String fileName, MachineCombinations machineCombinations) {

    List<String> lines = new ArrayList<>();

    lines.add("Nr solutions=" + machineCombinations.size());
    machineCombinations.getBestCombinations()
        .stream()
        .map(FileUtils::transformToString)
        .forEach(lines::add);
    lines.add("Waste=" + machineCombinations.getWaste());

    try {
      Files.write(Paths.get(fileName), lines);
    } catch (IOException e) {
      throw new ChipsetException("can't write to file " + fileName, e);
    }
  }

  private static String transformToString(final MachineCombinations.MachineCombination comb) {
    return comb.getMachines().stream().map(String::valueOf).collect(Collectors.joining(" "));
  }
}
