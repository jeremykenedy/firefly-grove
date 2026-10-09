package com.jeremykenedy.fireflygrove;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;

import java.util.Random;

final class FireflySceneView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Random random = new Random(241109L);
    private final float[] x = new float[110];
    private final float[] y = new float[110];
    private final float[] phase = new float[110];
    private final float[] size = new float[110];
    private FireflyOptions options;
    private LinearGradient background;
    private RadialGradient glow;
    private final Matrix glowMatrix = new Matrix();
    private long startedAt;
    private boolean running;

    FireflySceneView(Context context) {
        super(context);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextFloat();
            y[i] = random.nextFloat();
            phase[i] = random.nextFloat() * 6.283f;
            size[i] = 1.1f + random.nextFloat() * 2.1f;
        }
        loadOptions();
    }

    void start() {
        if (!running) {
            running = true;
            startedAt = SystemClock.uptimeMillis();
            postInvalidateOnAnimation();
        }
    }

    void stop() {
        running = false;
        removeCallbacks(invalidator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() <= 0 || getHeight() <= 0) return;
        float time = (SystemClock.uptimeMillis() - startedAt) / 1000f;
        drawBackground(canvas);
        drawFireflies(canvas, time);
        if (running) postDelayed(invalidator, 33L);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        int top = options.habitat == 1 ? 0xff17201b : options.habitat == 2 ? 0xff071b20 : 0xff10191a;
        int bottom = options.habitat == 1 ? 0xff263025 : options.habitat == 2 ? 0xff071116 : 0xff18211d;
        background = new LinearGradient(0, 0, 0, Math.max(height, 1), top, bottom, Shader.TileMode.CLAMP);
    }

    private final Runnable invalidator = new Runnable() {
        @Override public void run() { if (running) invalidate(); }
    };

    private void loadOptions() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        options = FireflyOptions.resolve(preferences.getString("density", "handful"),
                preferences.getString("motion", "natural"), preferences.getString("glow_color", "warm"),
                preferences.getString("habitat", "woodland"), preferences.getBoolean("randomize_all", false),
                new Random(System.currentTimeMillis()));
        glow = new RadialGradient(0f, 0f, 1f,
                new int[] {withAlpha(options.glowColor, 108), withAlpha(options.glowColor, 0)},
                null, Shader.TileMode.CLAMP);
    }

    private void drawBackground(Canvas canvas) {
        paint.setShader(background);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setShader(null);
        paint.setColor(options.habitat == 2 ? 0x22108e92 : 0x222b4732);
        canvas.drawOval(-getWidth() * 0.12f, getHeight() * 0.72f, getWidth() * 1.12f, getHeight() * 1.24f, paint);
        if (options.habitat != 2) {
            paint.setColor(0x332f4934);
            canvas.drawOval(-getWidth() * 0.2f, getHeight() * 0.82f, getWidth() * 0.58f, getHeight() * 1.18f, paint);
        }
    }

    private void drawFireflies(Canvas canvas, float time) {
        for (int i = 0; i < options.count; i++) {
            float drift = time * options.speed * (0.006f + (i % 7) * 0.0008f);
            float px = (x[i] + (float) Math.sin(time * 0.19f + phase[i]) * 0.018f + drift) % 1f;
            float py = (y[i] + (float) Math.sin(time * 0.27f + phase[i]) * 0.035f
                    - (float) Math.cos(time * 0.11f + phase[i]) * 0.008f + 1f) % 1f;
            float pulse = 0.58f + 0.42f * (0.5f + 0.5f * (float) Math.sin(time * (1.2f + size[i] * 0.13f) + phase[i]));
            float radius = (3.5f + size[i] * 2.5f) * pulse;
            float cx = px * getWidth();
            float cy = py * getHeight();
            glowMatrix.setScale(radius * 6f, radius * 6f);
            glowMatrix.postTranslate(cx, cy);
            glow.setLocalMatrix(glowMatrix);
            paint.setAlpha((int) (255 * pulse));
            paint.setShader(glow);
            canvas.drawCircle(cx, cy, radius * 6f, paint);
            paint.setShader(null);
            paint.setAlpha(255);
            paint.setColor(withAlpha(options.glowColor, (int) (105 * pulse)));
            canvas.drawOval(cx - radius * 1.8f, cy - radius * 0.42f, cx + radius * 1.8f, cy + radius * 0.42f, paint);
            paint.setColor(withAlpha(0xfffff2bd, (int) (230 * pulse)));
            canvas.drawCircle(cx, cy, Math.max(1.5f, radius * 0.23f), paint);
        }
    }

    private static int withAlpha(int color, int alpha) {
        return (color & 0x00ffffff) | (Math.max(0, Math.min(255, alpha)) << 24);
    }
}
