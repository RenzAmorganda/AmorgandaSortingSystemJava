import java.util.*;

class Thing {
    String name;
    String category; 
    int number;      
    Date date;

    Thing(String name, String category, int number, Date date) {
        this.name = name;
        this.category = category;
        this.number = number;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%d. %s (%s) - %tF", number, name, category, date);
    }
}

public class AmorgandaSortingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Thing[] livingThings = new Thing[5];
        Thing[] nonLivingThings = new Thing[5];
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JUNE, 5);
        System.out.println("Enter 5 living things:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Living thing " + (i+1) + ": ");
            String name = sc.nextLine();
            livingThings[i] = new Thing(name, "Living", i+1, cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        cal.set(2025, Calendar.JUNE, 5);
        System.out.println("Enter 5 non-living things:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Non-living thing " + (i+1) + ": ");
            String name = sc.nextLine();
            nonLivingThings[i] = new Thing(name, "Non-Living", i+1, cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        Thing[] allThings = new Thing[10];
        System.arraycopy(livingThings, 0, allThings, 0, 5);
        System.arraycopy(nonLivingThings, 0, allThings, 5, 5);
        System.out.println("\nHow would you like to sort the items?");
        System.out.println("1. Number (entry order)");
        System.out.println("2. Letter (name)");
        System.out.println("3. Date");
        System.out.println("4. Category (Living/Non-Living)");
        System.out.print("Enter your choice (1-4): ");
        int criteria = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Sort order? (asc/desc): ");
        String order = sc.nextLine().trim().toLowerCase();
        Comparator<Thing> comparator = null;
        switch (criteria) {
            case 1:
                comparator = Comparator.comparingInt(t -> t.number);
                break;
            case 2:
                comparator = Comparator.comparing(t -> t.name.toLowerCase());
                break;
            case 3:
                comparator = Comparator.comparing(t -> t.date);
                break;
            case 4:
                comparator = Comparator.comparing(t -> t.category);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Number.");
                comparator = Comparator.comparingInt(t -> t.number);
        }
        if (order.equals("desc")) {
            comparator = comparator.reversed();
        }
        Arrays.sort(allThings, comparator);
        System.out.println("\nSorted List:");
        for (Thing t : allThings) {
            System.out.println(t);
        }
    }
}