import java.util.Collection;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

record Coordinate(int x, int y) {

    Coordinate withX(int x) {
        return new Coordinate(x, this.y);
    }

    Coordinate withY(int y) {
        return new Coordinate(this.x, y);
    }

    public Collection<Coordinate> getNeighbours() {
        return IntStream.rangeClosed(-1, 1)
                .mapToObj(x -> IntStream.rangeClosed(-1, 1)
                        .mapToObj(y -> new Coordinate(this.x + x, this.y + y)))
                .flatMap(Function.identity())
                .filter(not(this::equals))
                .toList();
    }

}
