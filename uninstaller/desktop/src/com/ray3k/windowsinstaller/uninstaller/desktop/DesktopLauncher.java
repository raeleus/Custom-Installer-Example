package com.ray3k.windowsinstaller.uninstaller.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.ray3k.windowsinstaller.uninstaller.Core;
import com.ray3k.windowsinstaller.uninstaller.DesktopWorker;
import java.awt.SplashScreen;

public class DesktopLauncher implements DesktopWorker {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(655, 655);
        config.setDecorated(false);
        Core.desktopWorker = new DesktopLauncher();
        new Lwjgl3Application(new Core(), config);
    }

    @Override
    public void dragWindow(int x, int y) {
        Lwjgl3Window window = ((Lwjgl3Graphics) Gdx.graphics).getWindow();
        window.setPosition(x, y);
    }

    @Override
    public int getWindowX() {
        return ((Lwjgl3Graphics) Gdx.graphics).getWindow().getPositionX();
    }

    @Override
    public int getWindowY() {
        return ((Lwjgl3Graphics) Gdx.graphics).getWindow().getPositionY();
    }

    @Override
    public void closeSplash() {
        SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash != null) {
            splash.close();
        }
    }
}
