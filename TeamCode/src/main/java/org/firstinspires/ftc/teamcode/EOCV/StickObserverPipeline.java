package org.firstinspires.ftc.teamcode.EOCV;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class StickObserverPipeline extends OpenCvPipeline {
    public StickObserverPipeline (){

    }

    @Override
    public Mat processFrame(Mat input) {

        Mat mat = new Mat();
        //mat turns into HSV value
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        if (mat.empty()) {
            return input;
        }

        Scalar lowHSV = new Scalar(20, 70, 80); // lenient lower bound HSV for yellow
        Scalar highHSV = new Scalar(32, 255, 255); // lenient higher bound HSV for yellow

        Mat thresh = new Mat();

        // Get a black and white image of yellow objets
        Core.inRange(mat, lowHSV, highHSV, thresh);

        Mat masked = new Mat();
        // Color the white portion of thresh in with HSV from mat
        // Output into masked
        Core.bitwise_and(mat, mat, masked, thresh);
        // Calculate average HSV values of the white thresh values
        Scalar average = Core.mean(masked, thresh);

        Mat scaledMask = new Mat();
        // Scale the average saturation to 150
        masked.convertTo(scaledMask, -1, 150/average.val[1], 0);

        Scalar strictLowHSV = new Scalar(0, 150, 100); //strict lower bound HSV for yellow
        Scalar strictHighHSV = new Scalar(255, 255, 255); // strict higher bound HSV for yellow

        Mat scaledThresh = new Mat();

        //apply strict HSV filter onto scaledMask to get rid of any yellow other than pole
        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, scaledThresh);

        Mat finalMask = new Mat();
        //color in scaledThresh with HSV(for showing results)
        Core.bitwise_and(mat, mat, finalMask, scaledThresh);

        Mat edges = new Mat();
        //detect edges of finalMask
        Imgproc.Canny(finalMask, edges, 100, 200);

        //release all the data

        input.release();
        scaledThresh.copyTo(input);
        scaledThresh.release();
        scaledMask.release();
        mat.release();
        masked.release();
        edges.release();
        thresh.release();
        finalMask.release();
        return input;
    }
}
