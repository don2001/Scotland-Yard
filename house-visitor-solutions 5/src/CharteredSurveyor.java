public class CharteredSurveyor implements House.VisitorInt {
    public Integer visit(StrawHouse house) {
        return 400;
    }

    public Integer visit(StickHouse house) {
        return 600;
    }

    public Integer visit(BrickHouse house) {
        return 800;
    }
}
