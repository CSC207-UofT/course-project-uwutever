package com.uwutever.RegexApp;

import net.xqhs.graphs.graph.Edge;
import net.xqhs.graphs.graph.Node;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.uwutever.RegexApp.beans.Dimension;
import com.uwutever.RegexApp.beans.RegexGraph;
import com.uwutever.RegexApp.beans.Point2D;
import com.uwutever.RegexApp.beans.ArcUtils;
import com.uwutever.RegexApp.beans.RegexLayout;

public class GraphSurfaceView extends SurfaceView {

    private static final String TAG = "";
    private final ScaleGestureDetector mScaleDetector;

    private TypedArray attributes;

    private float mScaleFactor = 1.f;

    public GraphSurfaceView(Context context) {
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public GraphSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        attributes = getContext().obtainStyledAttributes(attrs, R.styleable.GraphSurfaceView);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public GraphSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attributes = getContext().obtainStyledAttributes(attrs, R.styleable.GraphSurfaceView);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public void init(final RegexGraph graph) {
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas(null);
                canvas.drawARGB(0, 225, 225, 255);
                drawGraph(canvas, graph);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {}
        });
    }

    private void drawGraph(final Canvas canvas, final RegexGraph graph) {
        Paint paint = new Paint();
        Paint whitePaint = new Paint();
        paint.setAntiAlias(true);
        RegexLayout layout = new RegexLayout(graph, new Dimension(getWidth(), getHeight()));
        whitePaint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_nodeBgColor, graph.getNodeBgColor()));
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint.setStrokeWidth(2f);
        whitePaint.setShadowLayer(5, 0, 0, attributes.getColor(R.styleable.GraphSurfaceView_defaultColor, graph
                .getDefaultColor()));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(20f);
        paint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_defaultColor, graph.getDefaultColor()));
        for (Edge edge : graph.getEdges()) {
            Point2D p1 = layout.transform(edge.getFrom());
            Point2D p2 = layout.transform(edge.getTo());
            paint.setStrokeWidth(1f);
            paint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_edgeColor, graph.getEdgeColor()));
            Paint curve = new Paint();
            curve.setAntiAlias(true);
            curve.setStyle(Paint.Style.STROKE);
            curve.setStrokeWidth(2);
            curve.setColor(attributes.getColor(R.styleable.GraphSurfaceView_edgeColor, graph.getEdgeColor()));
            PointF e1 = new PointF((float) p1.getX(), (float) p1.getY());
            PointF e2 = new PointF((float) p2.getX(), (float) p2.getY());
            ArcUtils.drawArc(e1, e2, 40f, canvas, curve, paint, whitePaint, edge.getLabel());
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(30f);
        paint.setStrokeWidth(5f);
        paint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_nodeColor, graph.getNodeColor()));
        for (Node node : graph.getNodes()) {
            Log.d(TAG, "drawNodes!");
            Point2D position = layout.transform(node);
            canvas.drawCircle((float) position.getX(), (float) position.getY(), 40, whitePaint);
            canvas.drawRect(
                    (float) position.getX() - 20,
                    (float) position.getY() - 20,
                    (float) position.getX() + 20, (float) position.getY() + 20, whitePaint);
            canvas.drawText(node.getLabel(), (float) position.getX(),
                    (float) position.getY() + 10, paint);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    public float getScaleFactor() {
        return this.mScaleFactor;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            invalidate();
            return true;
        }
    }

}