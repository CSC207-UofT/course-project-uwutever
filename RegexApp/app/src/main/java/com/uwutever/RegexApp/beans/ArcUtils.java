package com.uwutever.RegexApp.beans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class ArcUtils {

    private ArcUtils() {
    }

    /**
     * https://www.tbray.org/ongoing/When/200x/2009/01/02/Android-Draw-a-Curved-Line
     *
     * Draw arc.
     */
    public static void drawArc(PointF point1, PointF point2, float radius, Canvas canvas, Paint paint, Paint textPaint, Paint recPaint, String label) {
        double a1 = Math.toRadians(radius + 5);
        double dx = point2.x - point1.x, dy = point2.y - point1.y;
        double l = Math.sqrt((dx * dx) + (dy * dy));
        double l1 = l / 2.0;
        double h = l1 / (Math.tan(a1 / 2.0));
        double r = l1 / (Math.sin(a1 / 2.0));
        double a2 = Math.atan2(dy, dx);
        double a3 = (Math.PI / 2.0) - a2;
        double mX = (point1.x + point2.x) / 2.0;
        double mY = (point1.y + point2.y) / 2.0;
        double cY = mY + (h * Math.sin(a3));
        double cX = mX - (h * Math.cos(a3));
        RectF oval = new RectF((float) (cX - r), (float) (cY - r), (float) (cX + r), (float) (cY + r));
        double rawA4 = Math.atan2(point1.y - cY, point1.x - cX);
        float a4 = (float) Math.toDegrees(rawA4);
        paint.setStrokeWidth(2);
        drawArrow(point2.x, point2.y, a4 + radius + 45f, paint, canvas);
        canvas.drawArc(oval, a4, radius, false, paint);
        double deltay = -Math.sin(a3) * (r - h);
        double deltax = Math.cos(a3) * (r - h);

        // size 40f is used since width of word 'epsilon' needed
        canvas.drawRect(
                (float) (mX + deltax) - 40f,
                (float) (mY + deltay) + 40f,
                (float) (mX + deltax) + 40f, (float) (mY + deltay) - 40f, recPaint);

        canvas.drawText(label, (float) (mX + deltax),
                (float) (mY + deltay) + 10, textPaint);
    }

    private static void drawArrow(float x, float y, float degrees, Paint paint, Canvas canvas) {
        canvas.save();
        canvas.rotate(degrees, x, y);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(x - 40f, y - 40f);
        path.lineTo(x - 60f, y - 40f);
        path.lineTo(x - 40f, y - 60f);
        path.lineTo(x - 40f, y - 40f);
        path.close();
        canvas.drawPath(path, paint);
        canvas.restore();
        paint.setStyle(Paint.Style.STROKE);
    }
}