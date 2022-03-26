package frc.robot;

// Helper class to print values so that
// we don't have to type System.out every time and
// we can turn on and off prints easily w/o just commenting & uncommenting and 
// we will know where every print statement is coming from
// (it checks what class & method called this method & prints it out)

public class PHrint {

    //Prints the class's name and the method's name
    public static void p () {
        if (RobotMap.PRINTSENABLED) {
            String[] fullClassName = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " in " + className);
        }
    }

    //Prints the class's name and the method's name and a given String s
    public static void p (String s) {
        if (RobotMap.PRINTSENABLED) {
            String[] fullClassName = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " in " + className + ": " + s);
        }
    }

    //Prints the class's name and the method's name and a given double s (so you don't have to change types before calling)
    public static void p (double s) {
        if (RobotMap.PRINTSENABLED) {
            String[] fullClassName = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " in " + className + ": " + s);
        }
    }

    //Prints the class's name and the method's name and a given integer s (so you don't have to change types before calling)
    public static void p (int s) {
        if (RobotMap.PRINTSENABLED) {
            String[] fullClassName = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " in " + className + ": " + s);
        }
    }
}
