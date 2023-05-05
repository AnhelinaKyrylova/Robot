import javax.swing.*;

public class RobotTrajectory extends JPanel {

    // vstupne udaje
    private static final double L = 200.0; // rozchod kolies [mm]
    private static final double r = 50.0; // polomer kolesa [mm]
    private static final double d = 100.0; // velkost stvorca [mm]

    // vypocitane konstanty
    private static final double v = d / 4.0; // rychlost robota [mm/s]
    private static final double omega = Math.PI / 2.0 * v / r; // uhlova rychlost otacania robota [rad/s]
    private static final double t_total = 16.0 * d / v / r;
}