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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 *
 * @author Raymond
 */
public class EulaTable extends Table {

    public EulaTable(final Skin skin, final Stage stage) {
        pad(10.0f);
        
        Label label = new Label("EULA", skin);
        add(label).expandX().left();
        
        Button button = new Button(skin, "close");
        add(button).right().top();
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        
        row();
        Table bottom = new Table();
        bottom.pad(10.0f);
        add(bottom).grow().colspan(2);
        
        Table table = new Table();
        table.pad(5.0f);
        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setName("scrollPane");
        scrollPane.setFadeScrollBars(false);
        bottom.add(scrollPane).grow().space(15.0f).colspan(2);
        
        label = new Label(Core.readAndReplace("eula.txt"), skin, "eula");
        label.setWrap(true);
        table.add(label).growX().expandY().top();
        
        bottom.row();
        TextButton textButton = new TextButton("Accept", skin);
        bottom.add(textButton).minWidth(90);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Core.transition(EulaTable.this, new LocationTable(skin, stage));
            }
        });
        
        textButton = new TextButton("Cancel", skin);
        bottom.add(textButton).minWidth(90);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Core.transition(EulaTable.this, new MenuTable(skin, stage));
            }
        });
        
        stage.setScrollFocus(scrollPane);
    }
    
}
