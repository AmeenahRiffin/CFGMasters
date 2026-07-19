package TreePlanting;

import java.util.Scanner;

enum PlantType {
    TREE,
    FLOWER,
    NONE
}

// assignment 1 for CFGMasters 

// Here, I'm defining the main TreePlanting class, I find that it has to have the same name as the folder it is in otherwise
// it gives errors and doesn't work. This is unlike JS and DM where the filename has no bearing on the code itself.


public class TreePlanting {

    public static void main(String[] args) {

        // Scanner is used to get input from our users, I think the closest thing to javascript would be using prompt()
        // It doesn't come with basic java, it has to be imported, which I've done in the top of the file.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, gardener!");

        System.out.print("How many beds would you like to create? (Default is 9) ");
        while (!scanner.hasNextInt()) {
            System.out.print("Hey, enter a valid number of beds: ");
            scanner.next();
        }
        int soil_bed_count = scanner.nextInt();
        while (soil_bed_count <= 0) {
            System.out.print("Hey, no beds under 0 - that's impossible! Try again:");
            while (!scanner.hasNextInt()) {
                System.out.print("Hey, enter a valid number: ");
                scanner.next();
            }
            soil_bed_count = scanner.nextInt();
        }

        int max_columns = 3;

        PlantBed[] soil_beds = new PlantBed[soil_bed_count];
        for (int i = 0; i < soil_bed_count; i++) {
            soil_beds[i] = new TreeBed(); // All beds start as empty TreeBeds
        }

        System.out.println(
                "This is a tree planting app designed by myself, Ameenah, for the CFGMasters course, the idea is to demonstrate my understanding of outputs (printing), control flows (for example, conditionals), objects, inheritance and other concepts we have learned in our first week.");

        System.out.println(
                "_________________________________________________________________________________________");

        System.out.println("This is our flower bed, here we can plant various types of trees and help them grow.");

        displayBed(soil_beds, max_columns);

        // We're using a while() loop because as long as we haven't exited the app, we want it to return to this menu.
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. (Plant a seed)");
            System.out.println("2. (Water a bed)");
            System.out.println("3. (View Garden)");
            System.out.println("0. (Exit)");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Error: Invalid action. Please select 0, 1, 2, or 3.");
                scanner.next();
                continue;
            }

            int action = scanner.nextInt();

            if (action == 0) {
                printSummary(soil_beds);
                break;
            }

            if (action == 3) {
                printSummary(soil_beds);
                continue;
            }

            // checking for sanity so we're not putting in options that don't exist.
            if (action < 0 || action > 3) {
                System.out.println("Error: Invalid action. Please select 0, 1, 2, or 3.");
                continue;
            }

            System.out.println("\nWhich bed would you like to select?");
            System.out.print("(Options are from 1 to " + soil_bed_count + "): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Error: Invalid option. Please select 1 to " + soil_bed_count + ".");
                scanner.next(); 
                continue;
            }
            int choice = scanner.nextInt();

            if (choice < 1 || choice > soil_bed_count) {
                System.out.println("Error: Invalid option. Please select 1 to " + soil_bed_count + ".");
                continue;
            }

            PlantBed targetBed = soil_beds[choice - 1];
            System.out.println("You selected a " + targetBed.getBedType() + "!");

            // Planting
            if (action == 1) {
                if (!targetBed.getState().equals(PlantBed.empty)) {
                    System.out.println("We can only plant on empty plots of soil.");
                } else {
                    System.out.println("What would you like to plant?");
                    System.out.println("1. Tree");
                    System.out.println("2. Flower");
                    System.out.print("Enter choice: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Error: Invalid planting choice.");
                        scanner.next(); 
                        continue;
                    }
                    int plantChoice = scanner.nextInt();
                    
                    // Planting a tree
                    if (plantChoice == 1) {
                        targetBed.plant(PlantType.TREE);
                    
                    // Planting a flower
                    } else if (plantChoice == 2) {
                        FlowerBed flowerBed = new FlowerBed();
                        soil_beds[choice - 1] = flowerBed;
                        flowerBed.plant(PlantType.FLOWER);
                    } else {
                        System.out.println("Error: Invalid planting choice.");
                    }
                }
            } else if (action == 2) {
                // The bed is a class and both treebed and flowerbeds can be watered as they borrow from the plantbed class. :)
                targetBed.water();
            }

            System.out.println("\nUpdated flower bed:");
            displayBed(soil_beds, max_columns);
        }
        scanner.close();
    }


    private static int countTotalPlants(PlantBed[] soil_beds) {
        int count = 0;
        for (PlantBed bed : soil_beds) {
            if (!bed.getState().equals(PlantBed.empty)) {
                count++;
            }
        }
        return count;
    }

    // This summarizes what we're making in our garden, doesn't return anything. It will print to console only
    private static void printSummary(PlantBed[] soil_beds) {
        System.out.println("\n[Your Garden!]");
        System.out.println("Total beds created: " + soil_beds.length);
        System.out.println("Total active plants: " + countTotalPlants(soil_beds));
        int trees = 0;
        int flowers = 0;
        for (int i = 0; i < soil_beds.length; i++) {
            PlantBed bed = soil_beds[i];
            if (bed.getPlantType() == PlantType.TREE && !bed.getState().equals(PlantBed.empty)) {
                trees++;
            } else if (bed.getPlantType() == PlantType.FLOWER && !bed.getState().equals(PlantBed.empty)) {
                flowers++;
            }
        }
        System.out.println("You ended up planting " + trees + " tree(s) and " + flowers + " flower(s).");
        System.out.println("Thank you for gardening!");
    }

    public static void displayBed(PlantBed[] soil_beds, int max_columns) {
        for (int i = 0; i < soil_beds.length; i++) {
            System.out.print(soil_beds[i].getState() + " ");

            if ((i + 1) % max_columns == 0) {
                System.out.println();
            }
        }
        if (soil_beds.length % max_columns != 0) {
            System.out.println();
        }
    }
}


abstract class PlantBed {
    static String seed = "[o]";
    static String sapling = "[|]";
    static String empty = "[ ]";

    protected String state; 
    protected PlantType plantType; 

    public PlantBed() {
        this.state = empty;
        this.plantType = PlantType.NONE;
    }


    public abstract void water();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public String getBedType() {
        if (state.equals(empty)) {
            return "Empty Bed";
        }
        return plantType.toString().substring(0, 1) + plantType.toString().substring(1).toLowerCase() + " Bed";
    }

    public void plant(PlantType type) {
        if (state.equals(empty)) {
            this.state = seed;
            this.plantType = type;
            System.out.println("You've planted a " + type.toString().toLowerCase() + " seed! It needs water to grow.");
        } else {
            System.out.println("We can only plant on empty plots of soil.");
        }
    }
}

// TreeBed - inherits from plant bed
class TreeBed extends PlantBed {
    static String tree = "[T]";

    public TreeBed() {
        super();
    }

    @Override
    public void water() {
        if (state.equals(empty)) {
            System.out.println("You watered the soil, but there is no seed to grow!");
        } else if (state.equals(seed)) {
            setState(sapling); 
            System.out.println("You watered the seed, and it sprouted into a sapling!");
        } else if (state.equals(sapling)) {
            setState(tree); 
            System.out.println("You watered the sapling, and it grew into a full tree!");
        } else if (state.equals(tree)) {
            System.out.println("The tree is already fully grown! It looks happy.");
        }
    }
}

// FlowerBed - inherits from plant bed just like the other class
class FlowerBed extends PlantBed {
    static String flower = "[*]";

    public FlowerBed() {
        super();
    }

    @Override
    public String getBedType() {
        if (state.equals(empty)) {
            return "Empty Bed";
        }
        return "Flower Bed";
    }

    @Override
    public void water() {
        if (state.equals(empty)) {
            System.out.println("You watered the soil, but there is no seed to grow!");
        } else if (state.equals(seed)) {
            setState(sapling); 
            System.out.println("You watered the seed, and it sprouted into a sapling!");
        } else if (state.equals(sapling)) {
            setState(flower); 
            System.out.println("You watered the sapling, and it bloomed into a beautiful flower!");
        } else if (state.equals(flower)) {
            System.out.println("The flower is already fully bloomed! It smells lovely.");
        }
    }
}
