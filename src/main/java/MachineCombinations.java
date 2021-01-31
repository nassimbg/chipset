import java.util.ArrayList;
import java.util.List;

public class MachineCombinations {
  private final List<MachineCombination> bestCombinations;
  private final int waste;

  public MachineCombinations(final MachineCombinationsBuilder builder) {
    bestCombinations = builder.bestCombinations;
    waste = bestCombinations.size() == 0 ? 0 : builder.waste;
  }

  public int size() {
    return bestCombinations.size();
  }

  public List<MachineCombination> getBestCombinations() {
    return bestCombinations;
  }

  public int getWaste() {
    return waste;
  }

  @Override
  public String toString() {
    return "MachineCombinations{" +
        "bestCombinations=" + bestCombinations +
        ", waste=" + waste +
        '}';
  }

  public static final class MachineCombination {
    private final List<Integer> machines;

    public MachineCombination(final List<Integer> machines) {
      this.machines = machines;
    }

    public List<Integer> getMachines() {
      return machines;
    }

    @Override
    public String toString() {
      return "MachineCombination{" +
          "machines=" + machines +
          '}';
    }
  }

  static final class MachineCombinationsBuilder {
    private List<MachineCombination> bestCombinations;
    private int waste;

    MachineCombinationsBuilder() {
      this.waste = Integer.MAX_VALUE;
      this.bestCombinations = new ArrayList<>();
    }

    public int getWaste() {
      return waste;
    }

    MachineCombinationsBuilder overrideWaste(int waste) {
      this.waste = waste;
      bestCombinations = new ArrayList<>();

      return this;
    }

    MachineCombinationsBuilder addCombination(MachineCombination combination) {
      this.bestCombinations.add(combination);

      return this;
    }

    MachineCombinations build() {
      return new MachineCombinations(this);
    }
  }
}
