package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nathan on 5/05/2017.
 */

public abstract class Opmode extends LinearOpMode {

    protected DcMotor leftMotor1;

    protected DcMotor rightMotor1;

    protected DcMotor leftMotor2;

    protected DcMotor rightMotor2;

    protected DcMotor Harvester;

    protected DcMotor Harvester2;

    protected DcMotor winch;

    protected DcMotor winch2;

    protected ColorSensor ColorSensor;

    protected Servo Servo;

    protected Servo LeftServo;
    protected Servo RightServo;

}
