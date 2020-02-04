package org.firstinspires.ftc.teamcode.PinkCode.Systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.PinkCode.Teleop;

import java.util.Optional;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

public class Controls extends Teleop {
    public void setGroundServoPostion(double position) {
        Range.clip(position, 0.5, 0.7);
        ground_servo.setPosition(position);
    }
    public void drive(boolean strafeL, boolean strafeR, double right, double left){
        RightFront.setPower(right);
        RightBack.setPower(right);
        LeftBack.setPower(left);
        LeftFront.setPower(left);

        if (strafeL) {
            double power = 1.0;
            Range.clip(power, -1.0, 1.0);
            RightFront.setPower(-power);
            RightBack.setPower(power);
            LeftFront.setPower(power);
            LeftBack.setPower(-power);
            telemetry.addData("Strafe Power", "Power: " + power);
            telemetry.update();
        }

        if (strafeR) {
            double power = 1.0;
            Range.clip(power, -1.0, 1.0);
            RightFront.setPower(power);
            RightBack.setPower(-power);
            LeftFront.setPower(-power);
            LeftBack.setPower(power);
            telemetry.addData("Strafe Power", "Power: " + power);
            telemetry.update();
        }
    }
    public void pickBlock() {
        grabber_left.setPosition(0.5);
        grabber_right.setPosition(0.5);
        telemetry.addData("Servo Left", getGrabberLeft());
        telemetry.addData("Servo Right", getGrabberRight());
        telemetry.update();
    }
    public void relaseBlock() {
        grabber_left.setPosition(0);
        grabber_right.setPosition(0);
        telemetry.addData("Servo Left", getGrabberLeft());
        telemetry.addData("Servo Right", getGrabberRight());
        telemetry.update();
    }
    private double getGrabberLeft() {
        return grabber_left.getPosition();
    }
    private double getGrabberRight() {
        return grabber_right.getPosition();
    }
    public void init() {
        grabber_right.setPosition(0.5);
        telemetry.addData("Servo Ground: ", ground_servo.getPosition());
        telemetry.addData("Servo Grabber Left: ", grabber_left.getPosition());
        telemetry.addData("Servo Grabber Right: ", grabber_right.getPosition());
        telemetry.update();
    }
    //        double strafe = 1.0;
//
//            double power = Range.clip(strafe, -1.0, 1.0);
//
//            RightFront.setPower(power);
//            RightBack.setPower(-power);
//
//            LeftFront.setPower(-power);
//            LeftBack.setPower(power);
//            telemetry.addData("Motor Drive", "Right" + power);
//            telemetry.addData("System info", "Using the Range clip for power.");
//            telemetry.update();
}
