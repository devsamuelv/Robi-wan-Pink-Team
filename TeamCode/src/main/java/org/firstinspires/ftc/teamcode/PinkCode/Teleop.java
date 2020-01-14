package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    public int press = 0;

    @Override
    public void init() {
        StartHardware(hardwareMap);
        grabber_right.setPosition(0.5);
        telemetry.addData("Servo Ground: ", ground_servo.getPosition());
        telemetry.addData("Servo Level 1: ", level_1_servo.getPosition());
        telemetry.addData("Servo Grabber Left: ", grabber_left.getPosition());
        telemetry.addData("Servo Grabber Right: ", grabber_right.getPosition());
        telemetry.update();
    }

    @Override
    public void loop() {
        RightFront.setPower(gamepad1.right_stick_y);
        RightBack.setPower(gamepad1.right_stick_y);
        LeftFront.setPower(gamepad1.left_stick_y);
        LeftBack.setPower(gamepad1.left_stick_y);

        while (gamepad1.y) {
            if (press == 0) {
                level_1_servo.setPosition(0.5);
                ground_servo.setPosition(0.8);
                telemetry.addData("Servo Ground", getServoGround());
                telemetry.addData("Servo Level 1", getLevel_1_servo());
                telemetry.update();
                press ++;
            } else if (press == 1) {
                // todo finish this preset
                level_1_servo.setPosition(0.5);
                ground_servo.setPosition(0.5)
                telemetry.addData("Servo Ground", getServoGround());
                telemetry.addData("Servo Level 1", getLevel_1_servo());
                telemetry.update();
                press ++;
            } else if (press == 2) {
                // todo finish this preset
                level_1_servo.setPosition(0.5);
                ground_servo.setPosition(0.8);
                telemetry.addData("Servo Ground", getServoGround());
                telemetry.addData("Servo Level 1", getLevel_1_servo());
                telemetry.update();
                press ++;
            } else if (press == 3) {
                // todo finish this preset
                level_1_servo.setPosition(0.5);
                ground_servo.setPosition(0.8);
                telemetry.addData("Servo Ground", getServoGround());
                telemetry.addData("Servo Level 1", getLevel_1_servo());
                telemetry.update();
                press ++;
            } else {
                telemetry.addData("press value is invalid: ", press);
                telemetry.update();
            }
        }

        while (gamepad1.x) {
            level_1_servo.setPosition(0);
            ground_servo.setPosition(0);
            telemetry.addData("Servo Level 1", getLevel_1_servo());
            telemetry.addData("Servo Ground", getServoGround());
        }

        while (gamepad1.a) {
            grabber_left.setPosition(getGrabberLeft() + 0.1);
            grabber_right.setPosition(getGrabberRight() + 0.1);
            telemetry.addData("Servo Left", grabber_left.getPosition());
            telemetry.addData("Servo Right", grabber_right.getPosition());
            telemetry.update();
        }

        while (gamepad1.b) {
            grabber_left.setPosition(getGrabberLeft() - 0.1);
            grabber_right.setPosition(getGrabberRight() - 0.1);
            telemetry.addData("Servo Left", grabber_left.getPosition());
            telemetry.addData("Servo Right", grabber_right.getPosition());
            telemetry.update();
        }
    }
// 176000
    private double getServoGround() {
        return ground_servo.getPosition();
    }

    private double getLevel_1_servo() {
        return level_1_servo.getPosition();
    }

    private double getGrabberLeft() {
        return grabber_left.getPosition();
    }

    private double getGrabberRight() {
        return grabber_right.getPosition();
    }
}
