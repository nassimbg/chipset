import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Finds all the machine combinations that can produce K chip sets per minute with the minimum waste
 */
public final class KChipsetsProducerProblem {

  /**
   * A method that read input from {@code inputFile}, calculates the best combinations of machines that can produce the needed chipsets
   * with the least waste produced and then outputs the result into {@code outFile}
   * @param inputFile is needed to read the input from this file
   * @param outFile the file that is needed to output the data to
   */
  public static void findMachinesWithMinimumWaste(String inputFile, String outFile) {
    final InputInfo inputInfo = FileUtils.extractInfo(inputFile);

    final MachineCombinations machineCombinations = findMachinesWithMinimumWaste(inputInfo.getFrequencyPerMachine(), inputInfo.getChipsetsNeededPerMinute());

    FileUtils.outputToFile(outFile, machineCombinations);
  }

  /**
   * A recursive implementation to find the best combinations of machines that can produce {@code chipsetsNeededPerMinute} chip sets per minute
   * with the minimum waste produced
   *
   * @param frequencyPerMachine is the list of chipsets per minute for every machine
   * @param chipsetsNeededPerMinute is the number of chipsets needed to be produced
   * @return MachineCombinations which contains the best machine combinations with the least waste produced
   */
  public static MachineCombinations findMachinesWithMinimumWaste(int[] frequencyPerMachine, int chipsetsNeededPerMinute) {
    final MachineCombinations.MachineCombinationsBuilder builder = new MachineCombinations.MachineCombinationsBuilder();

    findMachinesWithMinimumWasteRec(frequencyPerMachine, chipsetsNeededPerMinute, 0, builder, new ArrayDeque<>(), 0);

    return builder.build();
  }

  private static void findMachinesWithMinimumWasteRec(int[] frequencyPerMachine, int chipsetsNeededPerMinute,
      int startMachineIndex, MachineCombinations.MachineCombinationsBuilder builder, Deque<Integer> currentMachinesTaken, int currentProducibleChipsets) {

    // if the currentProducibleChipsets reached is now > chipsetsNeededPerMinute,
    // then no need to continue withe the combinations because adding more combinations will result in more waste
    if (currentProducibleChipsets >= chipsetsNeededPerMinute) {
      int currentWasteReached = currentProducibleChipsets - chipsetsNeededPerMinute;

      if (builder.getWaste() >= currentWasteReached) {
        //if currentWasteReached is > than the saved waste, then we have a better solution
        // so we need to override the current solution to have to currentWasteReached
        if (builder.getWaste() > currentWasteReached) {
          builder.overrideWaste(currentWasteReached);
        }
        builder.addCombination(new MachineCombinations.MachineCombination(new ArrayList<>(currentMachinesTaken)));
      }
    } else {
      //picking the current machine to add it to the current Machines Taken
      for (int currentMachineIndex = startMachineIndex; currentMachineIndex < frequencyPerMachine.length; currentMachineIndex++) {
        currentMachinesTaken.addLast(frequencyPerMachine[currentMachineIndex]);

        findMachinesWithMinimumWasteRec(frequencyPerMachine, chipsetsNeededPerMinute, currentMachineIndex + 1,
            builder, currentMachinesTaken, currentProducibleChipsets + frequencyPerMachine[currentMachineIndex]);

        currentMachinesTaken.pollLast();
      }
    }
  }
}
