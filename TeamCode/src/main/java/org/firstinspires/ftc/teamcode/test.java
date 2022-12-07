package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class test extends OpMode {
    //Hardware hardware;
    DcMotor backL;
    DcMotor backR;
    DcMotor frontL;
    DcMotor frontR;
    DcMotor arm1;
    DcMotor arm2;

    Servo torqueR;
    Servo torqueL;
    CRServo speedR;
    CRServo speedL;

    float armposition;

    @Override
    public void init() {
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        backL = hardwareMap.get(DcMotor.class, "backL");
        arm1 = hardwareMap.get(DcMotor.class, "arm1");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");

        torqueR = hardwareMap.get(Servo.class, "R torque");
        torqueL = hardwareMap.get(Servo.class, "L torque");
        speedR = hardwareMap.get(CRServo.class, "R speed");
        speedL = hardwareMap.get(CRServo.class, "L speed");

        //arm2.setDirection(DcMotorSimple.Direction.REVERSE);
        //arm1.setDirection(DcMotorSimple.Direction.REVERSE);

        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        torqueR.scaleRange(0.2, 0.8);
        torqueL.scaleRange(0.2, 0.8);
    }

    @Override
    public void init_loop() {
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {
        move();
        arm();
        claw();
        //arm2();
        //hardware.stop();
    }

    public void move() {
        double drive;
        double turn;
        double strafe;
        drive = gamepad1.left_stick_y;
        turn = gamepad1.left_stick_x;


        backL.setPower(((drive - turn) / 4) * 3);
        frontL.setPower(((drive - turn) / 4) * 3);
        backR.setPower((-(drive + turn) / 4) * 3);
        frontR.setPower((-(drive + turn) / 4) * 3);
    }

    public void arm() {
        if (gamepad1.dpad_up) {
            arm1.setTargetPosition(-1075);
            arm2.setTargetPosition(-1075);
            arm1.setPower(1);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setPower(1);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad1.dpad_left) {
            arm1.setTargetPosition(-700);
            arm2.setTargetPosition(-700);
            arm1.setPower(1);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setPower(1);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad1.dpad_down) {
            arm1.setTargetPosition(-420);
            arm2.setTargetPosition(-420);
            arm1.setPower(.5);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setPower(.5);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad1.dpad_right) {
            arm1.setTargetPosition(-40);
            arm1.setPower(1);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setTargetPosition(-40);
            arm2.setPower(1);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
    public void arm2(){
        armposition = (-1*(gamepad1.right_stick_y)/2 * (1075 - 10) + 10);
        arm1.setTargetPosition((int) armposition);
        arm1.setPower(1);
        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setTargetPosition((int) armposition);
        arm2.setPower(1);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void claw() {
        if (gamepad2.left_bumper) {
            // closed
            torqueL.setPosition(0);
            torqueR.setPosition(0.05);

            speedL.setPower(0);
            speedR.setPower(0);
        } else if (gamepad2.right_bumper) {
            // open
            torqueL.setPosition(0.05);
            torqueR.setPosition(0);
            speedL.setPower(-1);
            speedR.setPower(1);
        }
    }
}