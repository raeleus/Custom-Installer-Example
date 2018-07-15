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
package com.ray3k.windowsinstaller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *
 * @author Raymond
 */
public class GraphicWidget extends Table {
    private Image bg, mountain1a, mountain1b, mountain2, mountain3, sun;
    private float timeScale;

    public GraphicWidget(Skin skin) {
        super(skin);
        setTouchable(Touchable.disabled);
        
        Stack stack = new Stack();
        add(stack).grow();
        
        bg = new Image(skin, "bg");
        bg.setColor(72 / 255.0f, 43 / 255.0f, 188 / 255.0f, 1.0f);
        stack.add(bg);
        
        sun = new Image(skin, "sun");
        Container container = new Container(sun);
        container.bottom();
        stack.add(container);
        
        mountain1a = new Image(skin, "mountain01");
        mountain1a.setColor(53 / 255.0f, 36 / 255.0f, 150 / 255.0f, 1.0f);
        container = new Container(mountain1a);
        container.left().bottom().padLeft(160).padBottom(78);
        stack.add(container);
        
        mountain1b = new Image(skin, "mountain01");
        mountain1b.setColor(30 / 255.0f, 30 / 255.0f, 114 / 255.0f, 1.0f);
        container = new Container(mountain1b);
        container.bottom().left().padLeft(253).padBottom(45);
        stack.add(container);
        
        mountain3 = new Image(skin, "mountain03");
        mountain3.setColor(97 / 255.0f, 56 / 255.0f, 186 / 255.0f, 1.0f);
        container = new Container(mountain3);
        container.left().bottom();
        stack.add(container);
        
        mountain2 = new Image(skin, "mountain02");
        mountain2.setColor(13 / 255.0f, 13 / 255.0f, 91 / 255.0f, 1.0f);
        container = new Container(mountain2);
        container.right().bottom();
        stack.add(container);
    }

    public float getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(float timeScale) {
        this.timeScale = timeScale;
        
        if (timeScale <= .5f) {
            Color color = new Color();
            
            color.set(72 / 255.0f, 43 / 255.0f, 188 / 255.0f, 1.0f);
            bg.setColor(color.lerp(255 / 255.0f, 191 / 255.0f, 62 / 255.0f, 1.0f, timeScale / .5f));
            
            color.set(53 / 255.0f, 36 / 255.0f, 150 / 255.0f, 1.0f);
            mountain1a.setColor(color.lerp(255 / 255.0f, 164 / 255.0f, 64 / 255.0f, 1.0f, timeScale / .5f));
            
            color.set(30 / 255.0f, 30 / 255.0f, 114 / 255.0f, 1.0f);
            mountain1b.setColor(color.lerp(211 / 255.0f, 133 / 255.0f, 55 / 255.0f, 1.0f, timeScale / .5f));
            
            color.set(13 / 255.0f, 13 / 255.0f, 91 / 255.0f, 1.0f);
            mountain2.setColor(color.lerp(226 / 255.0f, 106 / 255.0f, 59 / 255.0f, 1.0f, timeScale / .5f));
            
            color.set(97 / 255.0f, 56 / 255.0f, 186 / 255.0f, 1.0f);
            mountain3.setColor(color.lerp(255 / 255.0f, 209 / 255.0f, 64 / 255.0f, 1.0f, timeScale / .5f));
        } else {
            Color color = new Color();
            
            color.set(255 / 255.0f, 191 / 255.0f, 62 / 255.0f, 1.0f);
            bg.setColor(color.lerp(124 / 255.0f, 198 / 255.0f, 224 / 255.0f, 1.0f, (timeScale - .5f) / .5f));
            
            color.set(255 / 255.0f, 164 / 255.0f, 64 / 255.0f, 1.0f);
            mountain1a.setColor(color.lerp(55 / 255.0f, 186 / 255.0f, 221 / 255.0f, 1.0f, (timeScale - .5f) / .5f));
            
            color.set(211 / 255.0f, 133 / 255.0f, 55 / 255.0f, 1.0f);
            mountain1b.setColor(color.lerp(48 / 255.0f, 122 / 255.0f, 183 / 255.0f, 1.0f, (timeScale - .5f) / .5f));
            
            color.set(226 / 255.0f, 106 / 255.0f, 59 / 255.0f, 1.0f);
            mountain2.setColor(color.lerp(38 / 255.0f, 70 / 255.0f, 135 / 255.0f, 1.0f, (timeScale - .5f) / .5f));
            
            color.set(255 / 255.0f, 209 / 255.0f, 64 / 255.0f, 1.0f);
            mountain3.setColor(color.lerp(125 / 255.0f, 163 / 255.0f, 219 / 255.0f, 1.0f, (timeScale - .5f) / .5f));
        }
        
        ((Container) sun.getParent()).padBottom(timeScale * 334.0f);
        ((Container) sun.getParent()).invalidate();
    }
}
