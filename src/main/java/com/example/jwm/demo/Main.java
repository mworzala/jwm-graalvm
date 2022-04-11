package com.example.jwm.demo;

import io.github.humbleui.jwm.*;
import io.github.humbleui.jwm.skija.EventFrameSkija;
import io.github.humbleui.jwm.skija.LayerMetalSkija;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Surface;
import io.github.humbleui.types.Rect;

import java.util.function.Consumer;

public class Main implements Consumer<Event> {
    private final Window window;

    public Main() {
        window = App.makeWindow();
        window.setEventListener(this);
        window.setTitle("Empty");
        window.setLayer(new LayerMetalSkija());

        var screen = App.getPrimaryScreen();
        var scale = screen.getScale();

        window.setWindowSize((int) (300 * scale), (int) (600 * scale));
        window.setWindowPosition((int) (300 * scale), (int) (200 * scale));
        window.setVisible(true);
    }


    @Override
    public void accept(Event e) {
        if (e instanceof EventWindowClose) {
            if (App._windows.size() == 0)
                App.terminate();
        } else if (e instanceof EventFrameSkija ee) {
            Surface s = ee.getSurface();
            paint(s.getCanvas(), s.getWidth(), s.getHeight());
        } else if (e instanceof EventWindowCloseRequest) {
            window.close();
        }
    }

    public void paint(Canvas canvas, int width, int height) {
        float scale = window.getScreen().getScale();
        canvas.clear(0xFF264653);
        try (var paint = new Paint()) {
            paint.setColor(0xFFe76f51);

            canvas.drawRect(Rect.makeXYWH(10 * scale, 10 * scale, 10 * scale, 10 * scale), paint);
            canvas.drawRect(Rect.makeXYWH(width - 20 * scale, 10 * scale, 10 * scale, 10 * scale), paint);
            canvas.drawRect(Rect.makeXYWH(10 * scale, height - 20 * scale, 10 * scale, 10 * scale), paint);
            canvas.drawRect(Rect.makeXYWH(width - 20 * scale, height - 20 * scale, 10 * scale, 10 * scale), paint);
            canvas.drawRect(Rect.makeXYWH(width / 2 - 5 * scale, height / 2 - 5 * scale, 10 * scale, 10 * scale), paint);
        }
    }

    public static void main(String[] args) {
        App.start(Main::new);
    }
}
