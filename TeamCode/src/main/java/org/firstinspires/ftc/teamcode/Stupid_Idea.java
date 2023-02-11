package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Stupid_Idea extends LinearOpMode {
    DcMotor Lwheels;
    DcMotor Rwheels;
    DcMotor Barm;
    DcMotor Uarm;
    DcMotor Claw;
    CRServo Rclaw;
    CRServo Lclaw;

    @Override
    public void runOpMode() throws InterruptedException {
        Uarm = hardwareMap.get(DcMotor.class, "Uarm");
        Barm = hardwareMap.get(DcMotor.class, "Barm");
        Lwheels = hardwareMap.get(DcMotor.class, "Lwheels");
        Rwheels = hardwareMap.get(DcMotor.class, "Rwheels");
        Claw = hardwareMap.get(DcMotor.class, "Claw");
        Lclaw = hardwareMap.get(CRServo.class, "Lclaw");
        Rclaw = hardwareMap.get(CRServo.class, "Rclaw");

        Lwheels.setDirection(DcMotorSimple.Direction.REVERSE);
        Rwheels.setDirection(DcMotorSimple.Direction.FORWARD);
        Barm.setDirection(DcMotorSimple.Direction.REVERSE);
        Uarm.setDirection(DcMotorSimple.Direction.REVERSE);

        Uarm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Barm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int position;

        waitForStart();

        Uarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Barm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lwheels.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rwheels.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        position = Lwheels.getCurrentPosition();

        /*Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Uarm.setPower(0.75);
        Barm.setPower(0.75);
        while (Barm.isBusy() && Uarm.isBusy()) {
            sleep(15);
        }*/

        //                                                                             Cone 1

        Claw.setPower(-.6);
        Uarm.setTargetPosition(40);
        Barm.setTargetPosition(40);
        Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Uarm.setPower(0.75);
        Barm.setPower(0.75);

        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 3150);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 3150);
        Lwheels.setPower(.75);
        Rwheels.setPower(.75);
        Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Lwheels.setPower(.5);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 325);
 /*       while (Lwheels.isBusy()) {
            sleep(15);
        }*/
        Uarm.setTargetPosition(1440);
        Barm.setTargetPosition(1440);
        while (Uarm.isBusy() && Barm.isBusy()){
            sleep(15);
        }
        sleep(300);
        Lwheels.setPower(.5);
        Rwheels.setPower(.5);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 275);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 275);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Claw.setPower(.3);
        sleep(250);

        //                                                                             Cone 2

        Lwheels.setPower(.5);
        Rwheels.setPower(.5);
        Uarm.setTargetPosition(245);
        Barm.setTargetPosition(245);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 600);
        while (Lwheels.isBusy()) {
            sleep(15);
        }
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 135);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 950);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1190);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1190);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Claw.setPower(-.9);
        sleep(250);
        Uarm.setTargetPosition(620);
        Barm.setTargetPosition(620);
        while (Uarm.isBusy() && Barm.isBusy()){
            sleep(15);
        }
        Lclaw.setPower(-1);
        Rclaw.setPower(1);
        sleep(1000);
        Lclaw.setPower(0);
        Rclaw.setPower(0);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 1150);
        while (Lwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 200);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 200);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        Claw.setPower(.3);
        sleep(1000);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 200);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 200);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1100);
        Uarm.setTargetPosition(235);
        Barm.setTargetPosition(235);
        while (Lwheels.isBusy()) {
            sleep(15);
        }
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 120);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 120);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Claw.setPower(-.7);
        sleep(250);
        Uarm.setTargetPosition(680);
        Barm.setTargetPosition(680);
        while (Uarm.isBusy() && Barm.isBusy()){
            sleep(15);
        }
        Lclaw.setPower(-1);
        Rclaw.setPower(1);
        sleep(1000);
        Lclaw.setPower(0);
        Rclaw.setPower(0);

        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 1130);
        while (Lwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 200);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 200);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        Claw.setPower(.3);
        sleep(1000);
    }
}