import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Day11 extends AocDay<Void> {

//    private static final String INPUT = """
//            5483143223
//            2745854711
//            5264556173
//            6141336146
//            6357385478
//            4167524645
//            2176841721
//            6882881134
//            4846848554
//            5283751526
//            """;

    private static final String INPUT = """
            1443582148
            6553734851
            1451741246
            8835218864
            1662317262
            1731656623
            1128178367
            5842351665
            6677326843
            7381433267
            """;

    public static void main(String[] args) {
        new Day11().solve(true);
    }

    private static class Octopus {

        private int value;
        private Collection<Octopus> neighbours;

        private Octopus(int value) {
            this.value = value;
        }

        public void increaseValue() {
            value++;
            if (value == 10) {
                neighbours.forEach(Octopus::increaseValue);
            }
        }

        public void finishStep() {
            if (value > 9) {
                value = 0;
            }
        }

        public int getValue() {
            return value;
        }

        public void setNeighbours(Collection<Octopus> neighbours) {
            this.neighbours = neighbours;
        }

    }

    @Override
    Void prepareInput() throws Exception {
        return null;
    }

    private Map<Coordinate, Octopus> getInput() {
        Map<Coordinate, Octopus> octopodes = new HashMap<>();
        var lines = INPUT.lines().toList();
        for (int y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            for (int x = 0; x < line.toCharArray().length; x++) {
                octopodes.put(new Coordinate(x, y), new Octopus(Character.getNumericValue(line.charAt(x))));
            }
        }
        octopodes.forEach((k, v) -> v.setNeighbours(
                k.getNeighbours().stream()
                        .map(octopodes::get)
                        .filter(Objects::nonNull)
                        .toList()
        ));
        return octopodes;
    }

    @Override
    Object part1(Void v) throws Exception {
        var input = getInput();
        var flashes = 0;
        for (int i = 0; i < 100; i++) {
            input.values().forEach(Octopus::increaseValue);
            flashes += input.values().stream()
                    .peek(Octopus::finishStep)
                    .filter(o -> o.getValue() == 0)
                    .count();
        }
        return flashes;
    }

    @Override
    Object part2(Void v) throws Exception {
        var input = getInput();
        var step = 1;
        while (true) {
            input.values().forEach(Octopus::increaseValue);
            var flashes = input.values().stream()
                    .peek(Octopus::finishStep)
                    .filter(o -> o.getValue() == 0)
                    .count();
            if (flashes == 100) {
                return step;
            }
            step++;
        }
    }

}
