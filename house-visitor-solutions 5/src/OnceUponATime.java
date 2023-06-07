public class OnceUponATime {
    public static Integer estimateRentPCM(House house) {
        return house.accept(new CharteredSurveyor());
    }

    public static Integer estimateHeatingBillPCM(House house) {
        return house.accept(new House.Visitor<Integer>() { // Anonymous inner class
            public Integer visit(StrawHouse house) { return 600; }
            public Integer visit(StickHouse house) { return 300; }
            public Integer visit(BrickHouse house) { return 200; }
        });

        // It's almost like:

        // case house of
        //   StrawHouse h -> 600
        //   StickHouse h -> 300
        //   BrickHouse h -> 200

        // ...but of course with a heap of extra syntactic noise and indirection,
        // because that's The Java Way™
    }

    // Declared as a named inner class here, but could equivalently be put into another file like any other class
    static class BigBadWolf implements House.Visitor<String> {
        private void eat(LittlePig pig) {
            pig.hp = 0;
        }

        public String visit(StrawHouse house) {
            eat(house.occupant);
            return "In no time at all, the big bad wolf blew down "
                    + house.occupant.name
                    + "'s house of straw!";
        }

        public String visit(StickHouse house) {
            eat(house.occupant);
            return "It took a bit of effort, but the big bad wolf blew down "
                    + house.occupant.name
                    + "'s house of sticks!";
        }

        public String visit(BrickHouse house) {
            String story = "The big bad wolf huffed and puffed, but he could not blow down "
                    + house.occupant.name
                    + "'s house of bricks! "
                    + "So he climbed down the chimney... ";
            if (house.potBoiling) {
                return story + "and landed in a big pot of boiling stew.";
            } else {
                eat(house.occupant);
                return story + "and ate " + house.occupant.name + "!";
            }
        }
    }

    public static String letMeComeIn(House house) {
        return house.accept(new BigBadWolf());
        // Or as an annonymous inner class...
//        return house.accept(new House.Visitor<String>() {
//            public String visit(StrawHouse house) {
//                return "In no time at all, the big bad wolf blew down "
//                          + house.occupant.name + "'s house of straw!";
//            }
//
//            public String visit(StickHouse house) {
//                return "It took a bit of effort, but the big bad wolf blew down "
//                          + house.occupant.name + "'s house of sticks!";
//            }
//
//            public String visit(BrickHouse house) {
//                return "The big bad wolf huffed and puffed, but he could not blow down "
//                          + house.occupant.name + "'s house of bricks!";
//            }
//        });
    }

    public static void main(String[] args) {

        House[] houses = new House[]{new StrawHouse(), new StickHouse(), new BrickHouse()};

        for (House house : houses) {
            System.out.println("Calling functions on: " + house.toString());
            System.out.println( "Estimating rent: " + estimateRentPCM(house) );
            System.out.println( "Estimating heating bill: " + estimateHeatingBillPCM(house) );
            System.out.println( "Here comes the Big Bad Wolf: " + letMeComeIn(house) );
        }

    }
}
