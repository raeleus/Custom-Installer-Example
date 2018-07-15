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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 *
 * @author Raymond
 */
public class LocationTable extends Table {

    public LocationTable(final Skin skin, final Stage stage) {
        super(skin);
        pad(10.0f);
        
        Button button = new Button(skin, "close");
        add(button).expandX().right();
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        
        row();
        Table bottom = new Table();
        bottom.pad(10.0f);
        bottom.defaults().space(10);
        add(bottom).expandY();
        
        Label label = new Label("Install Location", skin);
        bottom.add(label).expandX().left();
        
        bottom.row();
        Table table = new Table();
        table.defaults().space(10);
        bottom.add(table);
        
        final TextField textField = new TextField(Core.installationPath.path(), skin);
        textField.setDisabled(true);
        table.add(textField).width(380);
        
        TextButton textButton = new TextButton("Browse...", skin);
        table.add(textButton).minWidth(10);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                FileHandle file = Core.desktopWorker.selectFolder("Installation Directory...", Core.installationPath);
                if (file != null) {
                    Core.installationPath = file;
                    textField.setText(Core.installationPath.path());
                }
            }
        });
        
        bottom.row();
        final CheckBox iconCheckBox = new CheckBox("Create desktop icon", skin);
        iconCheckBox.setChecked(Core.installationCreateDesktopIcon);
        bottom.add(iconCheckBox).left();
        iconCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Core.installationCreateDesktopIcon = iconCheckBox.isChecked();
            }
        });
        
        bottom.row();
        final CheckBox startMenuCheckBox = new CheckBox("Create start menu entry", skin);
        startMenuCheckBox.setChecked(Core.installationCreateStartIcon);
        bottom.add(startMenuCheckBox).left();
        startMenuCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Core.installationCreateStartIcon = startMenuCheckBox.isChecked();
            }
        });
        
        bottom.row();
        table = new Table();
        table.defaults().space(10);
        bottom.add(table).expandX().right();
        
        textButton = new TextButton("Cancel", skin);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Core.transition(LocationTable.this, new MenuTable(skin, stage));
            }
        });
        
        textButton = new TextButton("Install", skin);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (Core.installationPath.exists() && Core.installationPath.list().length > 0) {
                    showOverwriteDialog();
                } else {
                    Core.transition(LocationTable.this, new InstallationTable(skin, stage), .5f, 0.0f);
                }
            }
        });
    }
    
    public void showOverwriteDialog() {
        Dialog dialog = new Dialog("", getSkin()) {
            @Override
            protected void result(Object object) {
                if ((Boolean) object) {
                    Core.transition(LocationTable.this, new InstallationTable(getSkin(), getStage()), .5f, 0.0f);
                }
            }
        };
        
        dialog.getContentTable().pad(10.0f);
        dialog.getButtonTable().pad(10.0f);
        dialog.text("Path exists. Overwrite files?");
        dialog.button("Install", true).button("Cancel", false);
        dialog.key(Input.Keys.ENTER, true).key(Input.Keys.ESCAPE, false);
        dialog.show(getStage());
    }
}
