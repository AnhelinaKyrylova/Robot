public class MobileRobot {
    // Parameters
    private static final double WHEEL_RADIUS = 0.05; // m
    private static final double WHEEL_DISTANCE = 0.2; // m
    private static final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS;

    public static void main(String[] args) {
        // Input vectors
        double[] time = {0, 5, 10, 15, 20}; // s
        double[] leftWheelSpeeds = {2, -1, 0, 2, 1}; // m/s
        double[] rightWheelSpeeds = {2, 1, 0, -2, 1}; // m/s

        // Calculate trajectories
        double[][] wheelTrajectory = calculateWheelTrajectory(time, leftWheelSpeeds, rightWheelSpeeds);
        double[][] centerOfMassTrajectory = calculateCenterOfMassTrajectory(time, leftWheelSpeeds, rightWheelSpeeds);

        // Print trajectories
        System.out.println("Wheel Trajectory:");
        printMatrix(wheelTrajectory);
        System.out.println("Center of Mass Trajectory:");
        printMatrix(centerOfMassTrajectory);
    }

    private static double[][] calculateWheelTrajectory(double[] time, double[] leftWheelSpeeds, double[] rightWheelSpeeds) {
        double[][] wheelTrajectory = new double[2][time.length];
        double leftWheelDistance = 0;
        double rightWheelDistance = 0;
        for (int i = 0; i < time.length; i++) {
            double deltaLeftWheelDistance = leftWheelSpeeds[i] * WHEEL_CIRCUMFERENCE * (time[i] - (i > 0 ? time[i-1] : 0));
            double deltaRightWheelDistance = rightWheelSpeeds[i] * WHEEL_CIRCUMFERENCE * (time[i] - (i > 0 ? time[i-1] : 0));
            leftWheelDistance += deltaLeftWheelDistance;
            rightWheelDistance += deltaRightWheelDistance;
            double x = (leftWheelDistance + rightWheelDistance) / 2;
            double y = (rightWheelDistance - leftWheelDistance) / WHEEL_DISTANCE;
            wheelTrajectory[0][i] = x;
            wheelTrajectory[1][i] = y;
        }
        return wheelTrajectory;
    }

    private static double[][] calculateCenterOfMassTrajectory(double[] time, double[] leftWheelSpeeds, double[] rightWheelSpeeds) {
        double[][] centerOfMassTrajectory = new double[2][time.length];
        double x = 0;
        double y = 0;
        double orientation = 0;
        for (int i = 0; i < time.length; i++) {
            double v = (leftWheelSpeeds[i] + rightWheelSpeeds[i]) / 2;
            double w = (rightWheelSpeeds[i] - leftWheelSpeeds[i]) / WHEEL_DISTANCE;
            double deltaOrientation = w * (time[i] - (i > 0 ? time[i-1] : 0));
            orientation += deltaOrientation;
            x += v * Math.cos(orientation) * (time[i] - (i > 0 ? time[i-1] : 0));
            y += v * Math.sin(orientation) * (time[i] - (i > 0 ? time[i-1] : 0));
            centerOfMassTrajectory[0][i] = x;
            centerOfMassTrajectory[1][i] = y;
        }
        return centerOfMassTrajectory;
    }

    private static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%.4f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
//Tento kód vypočíta trajektóriu kolies a trajektóriu ťažiska
//pre zadané vektory času a rýchlostí ľavého a pravého kolesa.
//Tieto trajektórie sú uložené v matici súradníc a môžu byť ďalej vizualizované
// pomocou externých knižníc na vykresľovanie grafov.