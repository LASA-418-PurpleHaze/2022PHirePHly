package frc.robot;

//We (will) have data on given distances from the goal and the rpm to make the goal from that distance.
//We store this data in this lookup table
//Then we can use the findValue() method to use the distance we are from the goal to find what rpm we should shoot.
public class LookupTable {
    
    //We use a 2D array to store the lookup table so that it's indexable for easier searching.
    //Each value in the top array will have a [distance, rpm] pair.
    //So it'll look like [[d0, rpm0], [d1, rpm1], [d2, rpm2], ...]
    double[][] map = new double[100][2];
    
    public LookupTable(){
        //Here we (will) put the actual data we've recorded into the lookup table
        //This needs to be sorted ascending
        map[0][0] = 0;
        map[0][1] = 200;
    }

    public double[][] getTable(){
        return map;
    }

    //Searches through the lookup table until it finds the distance value or the nearest distance value
    //Then finds a corresponding rpm
    public double findValue(double dist){
        for (int i = 0; i < map.length; i++) {
            //Found an exact value
            if (map[i][0] == dist) {
                return map[i][1];
            }
            //Inbetween two values
            else if (i != 0 && map[i-1][0] < dist && map[i][0] > dist) {
                //Make a line between the points less & greater than the distance we want.
                //Then find where the distance we want lies on the line. 
                //This gives a estimate rpm that should be good enough (given we have enough data).
                double slope = (map[i][1] - map[i-1][1]) / (map[i][0] - map[i-1][0]);
                return ((slope * (dist - map[i][0])) + map[i][1]);
            }   
        }
    return 0.0;
    }
}