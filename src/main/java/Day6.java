import java.util.Arrays;

class Day6 extends AocDay<int[]> {

//        private static final String INPUT = "3,4,3,1,2";
    private static final String INPUT = "2,1,2,1,5,1,5,1,2,2,1,1,5,1,4,4,4,3,1,2,2,3,4,1,1,5,1,1,4,2,5,5,5,1,1,4,5,4,1,1,4,2,1,4,1,2,2,5,1,1,5,1,1,3,4,4,1,2,3,1,5,5,4,1,4,1,2,1,5,1,1,1,3,4,1,1,5,1,5,1,1,5,1,1,4,3,2,4,1,4,1,5,3,3,1,5,1,3,1,1,4,1,4,5,2,3,1,1,1,1,3,1,2,1,5,1,1,5,1,1,1,1,4,1,4,3,1,5,1,1,5,4,4,2,1,4,5,1,1,3,3,1,1,4,2,5,5,2,4,1,4,5,4,5,3,1,4,1,5,2,4,5,3,1,3,2,4,5,4,4,1,5,1,5,1,2,2,1,4,1,1,4,2,2,2,4,1,1,5,3,1,1,5,4,4,1,5,1,3,1,3,2,2,1,1,4,1,4,1,2,2,1,1,3,5,1,2,1,3,1,4,5,1,3,4,1,1,1,1,4,3,3,4,5,1,1,1,1,1,2,4,5,3,4,2,1,1,1,3,3,1,4,1,1,4,2,1,5,1,1,2,3,4,2,5,1,1,1,5,1,1,4,1,2,4,1,1,2,4,3,4,2,3,1,1,2,1,5,4,2,3,5,1,2,3,1,2,2,1,4";

    public static void main(String[] args) {
        new Day6().solve();
    }

    @Override
    int[] prepareInput() {
        return Arrays.stream(INPUT.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @Override
    String part1(int[] input) {
        return solve(input, 80) + "";
    }

    @Override
    String part2(int[] input) {
        return solve(input, 256) + "";
    }

    private long solve(int[] input, int numberOfDays) {
        var spawnTimeCounts = new long[9];

        for (int i : input) {
            spawnTimeCounts[i]++;
        }
        for (int i = 0; i < numberOfDays; i++) {
            var newSpawnTimeCounts = new long[9];
            for (int spawnTime = 0; spawnTime < spawnTimeCounts.length; spawnTime++) {
                if (spawnTime == 0) {
                    newSpawnTimeCounts[6] += spawnTimeCounts[spawnTime];
                    newSpawnTimeCounts[8] += spawnTimeCounts[spawnTime];
                } else {
                    newSpawnTimeCounts[(spawnTime + 9 - 1) % 9] += spawnTimeCounts[spawnTime];
                }
            }
            spawnTimeCounts = newSpawnTimeCounts;
        }
        return Arrays.stream(spawnTimeCounts).sum();
    }

}
