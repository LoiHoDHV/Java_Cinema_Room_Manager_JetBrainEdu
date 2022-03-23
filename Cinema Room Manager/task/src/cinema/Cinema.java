package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    public static int currentIncome = 0;
    public static int totalIncome = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) {
        // Write your code here
        int rows, seatInRows;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatInRows = scanner.nextInt();
        char[][] seating  = new char[rows][seatInRows];
        setTheSeat(seating);
        run(seating,scanner);








    }

//    public static int calculateIncome(int rows, int seatEachRow){
//        int totalIncome;
//        int seats = rows * seatEachRow;
//        if(seats < 60){
//            totalIncome = seats * 10;
//        }else{
//            if(rows % 2 == 1){
//                int firstHalf = (int)Math.round(rows / 2);
//                int secondHalf = rows - firstHalf;
//
//                totalIncome = firstHalf * seatEachRow * 10  + secondHalf * seatEachRow * 8;
//            }else{
//                totalIncome = rows / 2 * seatEachRow * 10 +  rows / 2 * seatEachRow * 8;
//            }
//        }
//        return totalIncome;
//    }



//    public static void printSeating(int rows, int column){
//        System.out.println("Cinema:");
//        System.out.println("  ");
//        for(int i = 0; i < column; i++){
//            System.out.print((i + 1 ) + " " );
//
//        }
//        System.out.println();
//        for(int i = 0; i < rows; i++){
//            System.out.print( (i+1) + " ");
//            for(int j = 0; j < column; j++){
//                System.out.print("S ");
//            }
//            System.out.println();
//        }
//
//    }
//    public static void printSeatChoice(int rows, int column, int rowChoice, int colChoice){
//        System.out.println("Cinema:");
//        System.out.print("  ");
//        for(int i = 0; i < column; i++){
//            System.out.print((i + 1 ) + " " );
//
//        }
//        System.out.println();
//        for(int i = 0; i < rows; i++){
//            System.out.print( (i+1) + " ");
//            for(int j = 0; j < column; j++){
//                if((i+1) == rowChoice && (j+1) == colChoice){
//                    System.out.print("B ");
//                }else{
//                    System.out.print("S ");
//                }
//            }
//            System.out.println();
//        }
//    }
    public static void setTheSeat(char[][] arr){
        for(int i = 0; i < arr.length ; i++){
            for(int j = 0 ; j < arr[i].length; j++){
                arr[i][j] = 'S';
            }
        }
    }
    public static void printTheSeating(char[][] arr){
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i = 0; i < arr[1].length; i++){
            System.out.print((i+1) + " ");
        }
        System.out.println();
        for(int i = 0; i < arr.length; i++){
            System.out.print((i+1) + " ");
            for(int j =0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void buyATicket(char[][] arr,Scanner scanner){
        boolean stop = false;
        int row = -1;
        int column = -1;
        while(!stop){
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            column = scanner.nextInt();
            if(row > arr.length || column > arr[0].length){
                System.out.println("Wrong input");
            }else {
                if(takenSeat(arr,row,column)){
                    System.out.println("That ticket has already been purchased");
                }else{
                    arr[row-1][column-1] = 'B';
                    stop = true;
                }
            }
        }

        int ticketPrice = ticketPrice(arr.length, arr[0].length , row, column);
        System.out.println("Ticket price: $" +ticketPrice);
        currentIncome += ticketPrice;

    }
    public static boolean takenSeat(char[][] arr, int row, int column){
        if(arr[row-1][column-1] == 'B'){
            return true;
        }else{
            return false;
        }
    }
    public static int Menu(Scanner scanner){
        boolean valid = false;
        int choice = -1;
        while(!valid) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3.Statistics\n0. Exit");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    choice = 1;
                    valid = true;
                    break;
                case 2:
                    choice = 2;
                    valid = true;
                    break;
                case 0:
                    choice = 0;
                    valid = true;
                    break;
                case 3:
                    choice = 3;
                    valid = true;
                    break;
                default:
                    break;
            }
        }
        return choice;
    }
    public static int ticketPurchased(char[][] arr){
        int count = 0;
        for(char[] k: arr){
            for(char e : k){
                if(e == 'B'){
                    ++count;
                }
            }
        }
        return count;
    }
    public static String percentPurchased(char[][] arr){
        int ticketPurchased = ticketPurchased(arr);
        double percentage = (double)ticketPurchased / (double)(arr.length * arr[0].length);
        String roundOff = df.format(percentage*100);
        return roundOff;

    }
    public static int totalIncomeCalculating(char[][] arr){
        int seats = arr.length * arr[0].length;
        if(seats < 60){
            int ticketPrice = 10;
            totalIncome = ticketPrice * seats;
        }else{

            int firstHalf;
            int firstHalfPrice = 10;
            int secondHalf;
            int secondHalfPrice = 8;
            if(arr.length % 2 == 1){
                firstHalf = arr.length / 2;
                secondHalf = arr.length - firstHalf;
            }else{
                firstHalf = secondHalf = arr.length / 2;
            }
            totalIncome = firstHalf *arr[0].length * firstHalfPrice + secondHalf * arr[0].length * secondHalfPrice;
        }
        return totalIncome;


    }
    public static void statistic(char[][] arr){
        System.out.println("Number of purchased tickets: " + ticketPurchased(arr));
        System.out.println("Percentage: "+ percentPurchased(arr) +"%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncomeCalculating(arr));
    }

    public static int ticketPrice(int rows, int column,int rowChoice, int colChoice){
        int tickPrice;


        int seats = rows * column;
        if(seats < 60){
            tickPrice = 10;
        }else{
            int firstHalf = rows / 2;
            if(rowChoice <= firstHalf ){
                tickPrice = 10;
            }else{
                tickPrice = 8;
            }
        }

        return tickPrice;
    }
    public static void run(char[][] arr,Scanner scanner){
        boolean stop = false;
        while(!stop){
            int choice = Menu(scanner);
            switch (choice){
                case 1:
                    // show the seats
                    printTheSeating(arr);
                    break;
                case 2:
                    buyATicket(arr,scanner);
                    // buy a ticket
                    break;
                case 3:
                    //statistic
                    statistic(arr);
                    break;
                case 0:
                    // exit
                    stop = true;
                    break;
                default:
                    stop = false;
                    break;
            }
        }
    }
}