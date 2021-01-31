import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KChipsetsProducerProblemTest {

  @Test
  void testFindMachinesWithMinimumWaste() {
    final MachineCombinations machineCombinations = KChipsetsProducerProblem.findMachinesWithMinimumWaste(
        new int[]{1, 2, 4, 10, 5, 6}, 11);

    assertEquals(0, machineCombinations.getWaste());

    final List<MachineCombinations.MachineCombination> bestCombinations = machineCombinations.getBestCombinations();
    assertEquals(4, bestCombinations.size());

    final List<List<Integer>> expectedOutput = Arrays.asList(Arrays.asList(1, 4, 6), Arrays.asList(1, 10),
        Arrays.asList(2, 4, 5), Arrays.asList(5, 6));

    for (final MachineCombinations.MachineCombination combination : bestCombinations) {
      final List<Integer> sortedMachines = combination.getMachines().stream().sorted().collect(Collectors.toList());
      assertTrue(expectedOutput.contains(sortedMachines));
    }
  }

  @Test
  void testCantFindACombination() {
    final MachineCombinations machineCombinations = KChipsetsProducerProblem.findMachinesWithMinimumWaste(
        new int[]{1, 2, 4, 10, 5, 6}, 11000);

    assertEquals(0, machineCombinations.size());
    assertEquals(0, machineCombinations.getWaste());
  }
}
