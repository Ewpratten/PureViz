package ca.retrylife.pureviz;

import PicoEngine.Window;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Graphics2D;

public class Chassis {

    private Pose2d position;

    private static final double SIZE = 3.0;

    public Chassis() {
        position = new Pose2d(4.0, 0.0, Rotation2d.fromDegrees(0.0));
    }

    public void reset() {
        position = new Pose2d(4.0, 0.0, Rotation2d.fromDegrees(0.0));
    }

    public void tankDrive(double l, double r) {

    }

    public void swerveDrive(Translation2d vec, Rotation2d rot) {

        // Find the new pose
        Translation2d newTrans = position.getTranslation().plus(vec);

        // Set pose
        position = new Pose2d(newTrans, rot);
    }

    public Pose2d getPose(){
        return position;
    }

    public void render(Window win) {        

        // Build a rect for the chassis
        Rectangle2D rect = new Rectangle2D.Double(-win.getGrid().getX(SIZE), -win.getGrid().getX(SIZE),win.getGrid().getX(SIZE), win.getGrid().getX(SIZE));

        // Transform rect
        AffineTransform transform = new AffineTransform();
        transform.rotate(position.getRotation().getRadians());
        transform.translate(win.getGrid().getX(position.getTranslation().getX()), win.getGrid().getY(10) + win.getGrid().getY(position.getTranslation().getY()));

        Shape rotRect = transform.createTransformedShape(rect);

        // Render the rect
        win.setColor(Color.black);
        ((Graphics2D) win.getGraphics()).draw(rotRect);

    }

}