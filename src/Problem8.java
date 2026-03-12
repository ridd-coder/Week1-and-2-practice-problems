public class Problem8 {

    static String[] spots = new String[500];

    static int hash(String plate){
        return Math.abs(plate.hashCode()) % spots.length;
    }

    static int parkVehicle(String plate){

        int index = hash(plate);

        int probes = 0;

        while(spots[index]!=null){
            index=(index+1)%spots.length;
            probes++;
        }

        spots[index]=plate;

        System.out.println("Assigned spot "+index+" ("+probes+" probes)");
        return index;
    }

    static void exitVehicle(String plate){

        for(int i=0;i<spots.length;i++){

            if(plate.equals(spots[i])){
                spots[i]=null;
                System.out.println("Spot "+i+" freed");
                return;
            }
        }
    }

    public static void main(String[] args) {

        parkVehicle("ABC1234");
        parkVehicle("ABC1235");
        parkVehicle("XYZ9999");

        exitVehicle("ABC1234");
    }
}

