package com.ray3k.windowsinstaller.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ray3k.windowsinstaller.game.Core;
import java.io.File;
import java.io.IOException;

public class DesktopLauncher {

    public static void main(String[] args) {
        for (String arg : args) {
            File file = new File(arg);
            if (file.exists() && file.getName().toLowerCase().endsWith(".jar")) {
                try {
                    Runtime.getRuntime().exec("java -jar " + file.getPath());
                    break;
                } catch (IOException e) {
                    Gdx.app.error(DesktopLauncher.class.getName(), "Error launching jar file from argument String", e);
                }
            }
        }
        
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Core(), config);
    }
}
