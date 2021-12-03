record Coordinate(int x, int y) {

    Coordinate withX(int x) {
        return new Coordinate(x, this.y);
    }

    Coordinate withY(int y) {
        return new Coordinate(this.x, y);
    }

}
