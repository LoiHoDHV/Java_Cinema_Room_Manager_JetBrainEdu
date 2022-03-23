class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        // write your code here
        if(array == null){
            System.out.println("Exception!");
        }else{
            if(index < 0 ||index > array.length - 1){
                System.out.println("Exception!");
            }else {
                array[index] = array[index] * array[index];
                System.out.println(array[index]);
            }
        }


    }
}