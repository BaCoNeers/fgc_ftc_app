package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**

 * Created by nathan on 2/5/2017.

 */

@TeleOp(name="Tank Controls", group="Practice-Bot")

public class FirstOpMode extends Opmode {

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

        double red,green,blue;
        boolean boharvister, boreharvister;

        leftMotor1 = hardwareMap.dcMotor.get("left1_drive");
        leftMotor2 = hardwareMap.dcMotor.get("left2_drive");


        rightMotor1 = hardwareMap.dcMotor.get("right1_drive");
        rightMotor2 = hardwareMap.dcMotor.get("right2_drive");

        Harvister = hardwareMap.dcMotor.get("Harvister");
        Harvister2 = hardwareMap.dcMotor.get("harvister2");
        ColorSensor = hardwareMap.colorSensor.get("color");
        Servo = hardwareMap.servo.get("servo");

        rightMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor1.setDirection(DcMotorSimple.Direction.REVERSE);

// Set all motors to zero power

        leftMotor1.setPower(0);

        leftMotor2.setPower(0);

        rightMotor1.setPower(0);

        rightMotor2.setPower(0);

        Harvister.setPower(0);

        Servo.setPosition(0);

        ColorSensor.enableLed(true);
        boharvister = false;
        boreharvister = false;




// Send telemetry message to signify robot waiting;

        telemetry.addData("Say", "ready"); //

        telemetry.update();

// Wait for the game to start (driver presses PLAY)

        waitForStart();


        try {

// run until the end of the match (driver presses STOP)

            while (opModeIsActive()) {

                double rotation1 = -gamepad1.left_stick_x;
                double power1 = gamepad1.right_trigger - gamepad1.left_trigger;
                left = power1 + rotation1;
                right = power1 - rotation1;

                if (gamepad1.a)
                {
                    sleep(50);
                    boharvister ^= true;
                    if (boreharvister == true)
                    {
                        boreharvister = false;
                    }
                }

                if (gamepad1.left_bumper & gamepad1.a)
                {
                    sleep(50);
                    boreharvister ^= true;
                    if (boharvister == true)
                    {
                        boharvister = false;
                    }
                }


                if (boharvister == true)
                {
                    Harvister.setPower(1);
                    Harvister2.setPower(-1);
                }
                if (boharvister == false && boreharvister == false)
                {
                    Harvister.setPower(0);
                    Harvister2.setPower(0);
                }
                if (boreharvister == true)
                {
                    Harvister.setPower(-1);
                    Harvister2.setPower(1);
                }



                leftMotor1.setPower(left);
                rightMotor1.setPower(right);
                leftMotor2.setPower(left);
                rightMotor2.setPower(right);


                red = ColorSensor.red();
                blue = ColorSensor.blue();
                green = ColorSensor.green();

                if (40< red && red > blue)
                {
                    Servo.setPosition(0);
                }
                if (40 < blue && blue > red)
                {
                    Servo.setPosition(1);
                }

                telemetry.addData("left: ",left  );
                telemetry.addData("right: ", right);
                telemetry.addData("red: ", red);
                telemetry.addData("green: ", green);
                telemetry.addData("blue: ", blue);
                telemetry.addData("Harister: ", boharvister);
                telemetry.addData("reHarvister",boreharvister);
                telemetry.addData("Harvister", Harvister.getPower());
                telemetry.update();




// Pause for metronome tick. 40 mS each cycle = update 25 times a second.

                waitForTick(40);

            }

        }

        catch (java.lang.InterruptedException exc)

        {

            return;

        }

        finally {

            leftMotor1.setPower(0);

            rightMotor1.setPower(0);

            leftMotor2.setPower(0);

            rightMotor2.setPower(0);

            Harvister.setPower(0);

        }

    }

}






