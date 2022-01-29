package frc.robot;
import java.util.ArrayList;
public class LookupTable {
    double[][] map = new double[100][2];
    
    public LookupTable() {
        map[0][0] = 0;
        map[0][1] = 1;

    }
    public double findValue(double dist) {
        
        for (int i = 0; i < map.length; i++) {
            if (map[i][0] == dist) {
                return map[i][1];
            }
            else if (i != 0) {
                 if (map[i-1][0] < dist && map[i][0] > dist ) {
                    double slope = (map[i][1] - map[i-1][1]) / (map[i][0] - map[i-1][0]);
                    return ((slope * (dist - map[i][0])) + map[i-1][1]);
                }
            }   
    }
    return 0.0;
}
}
    
    

    
