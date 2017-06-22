package org.firstglobal.FgCommon;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Component for performing actions on a servoSorter
 */
public class ServoComponent extends OpModeComponent {


    private Servo servo;
    // position of the servoSorter
    private double servoPosition;
    private boolean reverse;

    /**
     * Constructor for component
     * Telemetry enabled by default.
     *
     * @param opMode
     * @param servo
     * @param initialPosition
     */
    public ServoComponent(FGOpMode opMode, Servo servo, double initialPosition) {

        this(opMode, servo, initialPosition, false);

    }

    /**
     * Constructor for component
     * @param opMode
     * @param servo
     * @param initialPosition
     * @param reverseOrientation true if the servoSorter is install in the reverse orientation
     */
    public ServoComponent(FGOpMode opMode, Servo servo, double initialPosition, boolean reverseOrientation) {

        super(opMode);

        this.servo = servo;

        this.reverse = reverseOrientation;

        // set the starting position of the servoSorter
        updateServoTargetPosition(initialPosition);

        opMode.telemetry.addData("adding servoSorter component pos: " , initialPosition);

    }

    /**
     * Assign a new value to the servoSorter's position
     *
     * @param newServoPosition
     */
    public void updateServoTargetPosition(double newServoPosition) {

        // clip the position values so that they never exceed 0..1
        servoPosition = Range.clip(newServoPosition, Servo.MIN_POSITION, Servo.MAX_POSITION);

        if (!reverse) {
            servo.setPosition(servoPosition);
        } else {
            servo.setPosition(1 - servoPosition);
        }

        getOpMode().telemetry.addData("servoPosition", servoPosition); ;

    }

    /**
     * Increment the value of the servoSorter with the new value passed in
     *
     * @param newServoPosition
     */
    public void incrementServoTargetPosition(double newServoPosition) {

        updateServoTargetPosition(getServoPosition() + newServoPosition);

    }

    /**
     * Accessor method for servoSorter position
     *
     * @return
     */
    public double getServoPosition() {
        return servoPosition;
    }


}
