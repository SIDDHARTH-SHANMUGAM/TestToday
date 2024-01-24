import java.util.*;

public class P {
    static class RequiredMaterial {
        String name;
        int kg;
        int price;

        public RequiredMaterial(String x, int y, int z) {
            name = x;
            kg = y;
            price = z;
        }

        public String toString() {
            return "name :" + name + " Kg :" + kg + " price :" + price;
        }
    }

    static class Consumables {
        String name;
        int unit;
        int price;

        public Consumables(String x, int y, int z) {
            name = x;
            unit = y;
            price = z;
        }

        public String toString() {
            return "name :" + name + " Units :" + unit + " pricePerUnit :" + price;
        }
    }

    static class Machine {
        String type;
        String name;
        String status;
        int price;

        public Machine(String w, String x, String y, int z) {
            type = w;
            name = x;
            status = y;
            price = z;
        }

        public String toString() {
            return "type :" + type + " name :" + name + " status :" + status + " pricePerHour :" + price;
        }
    }

    static class Operator {
        String name;
        String handle;
        int wages;

        public Operator(String x, String y, int z) {
            name = x;
            handle = y;
            wages = z;
        }

        public String toString() {
            return "name :" + name + " Handles :" + handle + " wages :" + wages;
        }
    }

    static class Order {
        int no;
        String process;
        int unit;

        public Order(int no, String x, int y) {
            this.no = no;
            process = x;
            unit = y;
        }

        public String toString() {
            return "no :" + no + " process :" + process + " unit :" + unit;
        }
    }

    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<RequiredMaterial> rm = new ArrayList<>();
        ArrayList<Consumables> c = new ArrayList<>();
        ArrayList<Machine> m = new ArrayList<>();
        ArrayList<String> p = new ArrayList<>();
        ArrayList<Operator> o = new ArrayList<>();
        ArrayList<Order> or = new ArrayList<>();

        while (sc.hasNext()) {
            String str = sc.nextLine();
            String s[] = str.split(":");
            if (s[0].equals("RM")) {
                rm.add(new RequiredMaterial(s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3])));
            } else if (s[0].equals("C")) {
                c.add(new Consumables(s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3])));
            } else if (s[0].equals("M")) {
                m.add(new Machine(s[1], s[2], s[3], Integer.parseInt(s[4])));
            } else if (s[0].equals("O")) {
                o.add(new Operator(s[1], s[2], Integer.parseInt(s[3])));
            } else if (s[0].equals("PROCESS")) {
                p.add(str);
            } else if (s[0].contains("Order")) {
                or.add(new Order(getInt(s[0]), s[1], Integer.parseInt(s[2])));
            }
        }

        System.out.println("Raw Materials: " + rm);
        System.out.println("Consumables: " + c);
        System.out.println("Machines: " + m);
        System.out.println("Operators: " + o);
        System.out.println("Orders: " + or);
        System.out.println("Processes: " + p);

        // Additional logic for processing orders and generating output
        processOrders(rm, c, m, o, or, p);
    }

    public static int getInt(String s) {
        s = s.replace("Order", "");
        return Integer.parseInt(s);
    }

    public static void processOrders(ArrayList<RequiredMaterial> rm, ArrayList<Consumables> c, ArrayList<Machine> m,
            ArrayList<Operator> o, ArrayList<Order> or, ArrayList<String> p) {
        // Implement logic for processing orders and generating output
        // You need to extend this logic based on your specific requirements
        // This is a basic example, and you may need to implement more detailed logic

        double totalCost = 0;
        int currentTime = 0;

        for (Order order : or) {
            System.out.println("Processing Order: " + order);

            // Additional logic for processing orders
            // ...

            // Update totalCost and currentTime based on processing
            totalCost += calculateOrderCost(order, rm, c, m, o, p);
            currentTime += calculateOrderDuration(order, rm, c, m, o, p);
        }

        System.out.println("Total Cost: $" + totalCost);
        System.out.println("Total Duration: " + currentTime + " minutes");
    }

    public static double calculateOrderCost(Order order, ArrayList<RequiredMaterial> rm, ArrayList<Consumables> c,
        ArrayList<Machine> m, ArrayList<Operator> o, ArrayList<String> p) {
    double orderCost = 0;

    // Get the process corresponding to the order
    String processId = order.process;
    String[] processSteps = p.stream()
            .filter(process -> process.startsWith("PROCESS:" + processId))
            .findFirst()
            .map(process -> process.substring(process.indexOf(":") + 1).trim().split(" "))
            .orElse(null);

    if (processSteps != null) {
        for (String step : processSteps) {
            String[] stepDetails = step.split(":");
            String machineId = stepDetails[1];
            int duration = Integer.parseInt(stepDetails[2]);
            String consumableName = stepDetails[3];
            int consumableQuantity = Integer.parseInt(stepDetails[4]);

            // Find the machine details
            Optional<Machine> machine = m.stream()
                    .filter(mach -> mach.name.equals(machineId))
                    .findFirst();

            // Find the consumable details
            Optional<Consumables> consumable = c.stream()
                    .filter(cons -> cons.name.equals(consumableName))
                    .findFirst();

            // Calculate the cost for this step
            if (machine.isPresent() && consumable.isPresent()) {
                double machineCost = machine.get().price * (duration / 60.0);
                double consumableCost = consumable.get().price * consumableQuantity;
                orderCost += machineCost + consumableCost;
            }
        }
    }

    return orderCost;
}

public static int calculateOrderDuration(Order order, ArrayList<RequiredMaterial> rm, ArrayList<Consumables> c,
        ArrayList<Machine> m, ArrayList<Operator> o, ArrayList<String> p) {
    int orderDuration = 0;

    // Get the process corresponding to the order
    String processId = order.process;
    String[] processSteps = p.stream()
            .filter(process -> process.startsWith("PROCESS:" + processId))
            .findFirst()
            .map(process -> process.substring(process.indexOf(":") + 1).trim().split(" "))
            .orElse(null);

    if (processSteps != null) {
        for (String step : processSteps) {
            String[] stepDetails = step.split(":");
            int duration = Integer.parseInt(stepDetails[2]);

            // Accumulate the duration for each step
            orderDuration += duration;
        }
    }

    return orderDuration;
}

}
