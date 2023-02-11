package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class AAAautostraight extends LinearOpMode {
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
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 350);
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

        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 40);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 40);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }

        Uarm.setTargetPosition(235);
        Barm.setTargetPosition(235);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 790);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 790);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Lwheels.setPower(0.75);
        Rwheels.setPower(0.75);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1580);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1580);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(250);
        Claw.setPower(-.6);
        sleep(500);
        Uarm.setPower(1);
        Barm.setPower(1);
        Uarm.setTargetPosition(540);
        Barm.setTargetPosition(540);
        Lclaw.setPower(-1);
        Rclaw.setPower(1);
        sleep(1200);
        Claw.setPower(-.6);
        Lclaw.setPower(0);
        Rclaw.setPower(0);
        while (Uarm.isBusy() && Barm.isBusy()) {
            sleep(15);
        }

        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 1750);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 1750);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(500);
        Lwheels.setPower(.75);
        Rwheels.setPower(.75);
        Uarm.setTargetPosition(1440);
        Barm.setTargetPosition(1440);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 600);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 600);
        while (Uarm.isBusy() && Barm.isBusy()) {
            sleep(15);
        }
        Lwheels.setPower(.5);
        Rwheels.setPower(.5);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 110);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 110);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(800);
        Claw.setPower(.3);
        sleep(250);
        //                                                                             Cone 3
        /*
        Uarm.setTargetPosition(165);
        Barm.setTargetPosition(165);

        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 105);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 105);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 600);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 600);
        Uarm.setTargetPosition(165);
        Barm.setTargetPosition(165);
        while (Uarm.isBusy() && Barm.isBusy() && Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        sleep(1000);
        Lwheels.setPower(0.75);
        Rwheels.setPower(0.75);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1650);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1650);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
        /*
        sleep(500);
        Claw.setPower(-.6);
        sleep(500);
        Uarm.setPower(1);
        Barm.setPower(1);
        Uarm.setTargetPosition(540);
        Barm.setTargetPosition(540);
        Lclaw.setPower(-1);
        Rclaw.setPower(1);
        sleep(1200);
        Claw.setPower(-.6);
        Lclaw.setPower(0);
        Rclaw.setPower(0);
        while (Uarm.isBusy() && Barm.isBusy()) {
            sleep(15);
        }
        Lwheels.setPower(.75);
        Rwheels.setPower(.75);
        Uarm.setTargetPosition(680);
        Barm.setTargetPosition(680);
        Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 100);
        Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 100);
        while (Lwheels.isBusy() && Rwheels.isBusy()) {
            sleep(15);
        }
            //                                                                             Parking
*/
    }
}