package com.poproject.game.inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.poproject.game.GUI.GUI;

import java.awt.*;

import static com.badlogic.gdx.scenes.scene2d.ui.Cell.defaults;

public class InventoryActor extends com.badlogic.gdx.scenes.scene2d.ui.Window {
    public static Window mInventoryWindow;
    public Array<SlotActor> slots;

    public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
        super("Inventory...", skin);

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        add(closeButton).height(getPadTop());
        setName("Inventory");

        setPosition(400, 100);
        defaults().space(8);
        row().fill().expandX();

        int i = 0;
        for (Slot slot : inventory.getSlots()) {
            SlotActor slotActor = new SlotActor(skin, slot);
            dragAndDrop.addSource(new SlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));
            add(slotActor);

            i++;
            if (i % 10 == 0) {
                row();
            }
        }

        pack();


        setVisible(false);
        mInventoryWindow = this;
        GUI.getGUI_stage().addActor(this);
    }
}
