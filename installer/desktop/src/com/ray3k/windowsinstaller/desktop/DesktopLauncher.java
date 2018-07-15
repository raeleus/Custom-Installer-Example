/*
 * The MIT License
 *
 * Copyright 2018 Raymond Buckley.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ray3k.windowsinstaller.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.files.FileHandle;
import com.ray3k.windowsinstaller.Core;
import com.ray3k.windowsinstaller.DesktopWorker;
import java.awt.SplashScreen;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

public class DesktopLauncher implements DesktopWorker {
    public static void main(String[] args) {
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
    
    @Override
    public FileHandle selectFolder(String title, FileHandle defaultPath) {
        //fix file path characters
        String path = defaultPath.path().replace("/", "\\");
        
        String result = TinyFileDialogs.tinyfd_selectFolderDialog(title, path);
        
        return result == null ? null : Gdx.files.absolute(result);
    }
}
