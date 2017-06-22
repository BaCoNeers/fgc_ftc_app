package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**

 * Created by nathan on 2/5/2017.

 */

@TeleOp(name="H2O FLOW", group="Practice-Bot")

public class FirstOpMode extends TeamOpMode {

    private ElapsedTime period = new ElapsedTime();

    /***

     *

     * waitForTick implements a periodic delay. However, this acts like a metronome

     * with a regular periodic tick. This is used to compensate for varying

     * processing times for each cycle. The function looks at the elapsed cycle time,

     * and sleeps for the remaining time interval.

     *

     * @param periodMs Length of wait cycle in mSec.

    FIRST Global Java SDK Startup Guide - Rev 0 Copyright 2017 REV Robotics, LLC 18

     */

    private void waitForTick(long periodMs) throws java.lang.InterruptedException {

        long remaining = periodMs - (long)period.milliseconds();

// sleep for the remaining portion of the regular cycle period.

        if (remaining > 0) {

            Thread.sleep(remaining);

        }

// Reset the cycle clock for the next pass.

        period.reset();

    }

    @Override

    public void runOpMode() {

        double left;
        double right;
        double winchPower;

        double red,green,blue;

        boolean harvesterHorizontalOn, harvesterVerticalOn;

        double lastPressedA = time;
        double lastPressedB = time;

        leftMotor1 = hardwareMap.dcMotor.get("left1_drive");
        leftMotor2 = hardwareMap.dcMotor.get("left2_drive");


        rightMotor1 = hardwareMap.dcMotor.get("right1_drive");
        rightMotor2 = hardwareMap.dcMotor.get("right2_drive");

        harvesterHorizontal = hardwareMap.dcMotor.get("Harvister");
        harvesterVertical = hardwareMap.dcMotor.get("harvister2");
        winch = hardwareMap.dcMotor.get("Winch");
        winch2 = hardwareMap.dcMotor.get("winch2");
        colorSensor = hardwareMap.colorSensor.get("color");
        servoSorter = hardwareMap.servo.get("servo");
        leftServo = hardwareMap.servo.get("LeftServo");
        rightServo = hardwareMap.servo.get("RightServo");

        rightMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        winch2.setDirection(DcMotorSimple.Direction.REVERSE);

// Set all motors to zero power

        leftMotor1.setPower(0);

        leftMotor2.setPower(0);

        rightMotor1.setPower(0);

        rightMotor2.setPower(0);

        harvesterHorizontal.setPower(0);

        servoSorter.setPosition(0);

        leftServo.setPosition(0);

        rightServo.setPosition(1);

        colorSensor.enableLed(true);
        harvesterHorizontalOn = false;
        harvesterVerticalOn = false;




// Send telemetry message to signify robot waiting;

        telemetry.addData("Say", "ready"); //

        telemetry.update();

        telemetry.setAutoClear(false);

        Telemetry.Item leftItem = telemetry.addData("left","%5.1f", 0.0);
        Telemetry.Item rightItem = telemetry.addData("right","%5.1f", 0.0);
        Telemetry.Item redItem = telemetry.addData("red","%5.1f", 0.0);
        Telemetry.Item greenItem = telemetry.addData("green","%5.1f", 0.0);
        Telemetry.Item blueItem = telemetry.addData("blue","%5.1f", 0.0);
        Telemetry.Item harvesterHorizontalItem = telemetry.addData("harvesthorz","%5.1f", 0.0);
        Telemetry.Item harvesterVertItem = telemetry.addData("harvestervert","%5.1f", 0.0);
        Telemetry.Item harvesterPowerItem = telemetry.addData("harvesterHoripw","%5.1f", 0.0);
        Telemetry.Item winchItem = telemetry.addData("winch","%5.1f", 0.0);





// Wait for the game to start (driver presses PLAY)

        waitForStart();


        try {

// run until the end of the match (driver presses STOP)

            while (opModeIsActive()) {

                // Harvester functions

                if (gamepad1.a)
                {
                    // Allow 50ms of buffer between changing states
                    if (lastPressedA + 0.05 < time) {
                        harvesterHorizontalOn = !harvesterHorizontalOn;
                        lastPressedA = time;
                    }
                }

                if (gamepad1.b)
                {
                    // Allow 50ms of buffer between changing states
                    if (lastPressedB + 0.05 < time) {
                        harvesterVerticalOn = !harvesterVerticalOn;
                        lastPressedB = time;
                    }
                }

                // Turn the harvesters On or Off
                harvesterHorizontal.setPower(harvesterHorizontalOn ? 1.0 : 0.0);
                harvesterVertical.setPower(harvesterVerticalOn ? 1.0 : 0.0);

                // Drive functions
                double rotation1 = -gamepad1.left_stick_x /2;
                double power1 = gamepad1.right_trigger - gamepad1.left_trigger;
                left = power1 + rotation1;
                right = power1 - rotation1;

                if (gamepad1.x)
                {
                    left = left/2;
                    right = right/2;
                }

                leftMotor1.setPower(left);
                rightMotor1.setPower(right);
                leftMotor2.setPower(left);
                rightMotor2.setPower(right);

                // Winch
                winchPower = gamepad2.right_trigger;
                winch.setPower(winchPower);
                winch2.setPower(winchPower);


                red = colorSensor.red();
                blue = colorSensor.blue();
                green = colorSensor.green();

                if (40< red && red > blue)
                {
                    servoSorter.setPosition(0);
                }
                if (40 < blue && blue > red)
                {
                    servoSorter.setPosition(1);
                }

                if (gamepad2.dpad_left)
                {
                    leftServo.setPosition(1);
                }

                if (gamepad2.dpad_right)
                {
                    rightServo.setPosition(1);
                }
                if (gamepad2.dpad_up)
                {
                    leftServo.setPosition(0);
                    rightServo.setPosition(0);
                }


                leftItem.setValue("%5.1f",left);
                rightItem.setValue("%5.1f",right);
                redItem.setValue("%5.1f",red);
                greenItem.setValue("%5.1f",green);
                blueItem.setValue("%5.1f",blue);
                harvesterHorizontalItem.setValue("%s",harvesterHorizontalOn?"true":"false");
                harvesterVertItem.setValue("%s",harvesterVerticalOn?"true":"false");
                harvesterPowerItem.setValue("%5.1f", harvesterHorizontal.getPower());
                winchItem.setValue("%5.1f",winchPower);
                telemetry.update();




// Pause for metronome tick. 40 mS each cycle = update 25 times a second.

                idle();

            }

        }

        catch (Exception exc)

        {

            exc.printStackTrace();

        }

        finally {

            leftMotor1.setPower(0);

            rightMotor1.setPower(0);

            leftMotor2.setPower(0);

            rightMotor2.setPower(0);

            harvesterHorizontal.setPower(0);
            harvesterVertical.setPower(0);
            winch.setPower(0);
            winch2.setPower(0);

        }

    }

}






