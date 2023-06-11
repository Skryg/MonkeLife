package com.poproject.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.poproject.game.inventory.Inventory;
import com.poproject.game.inventory.InventoryActor;

public class GUI {
    private static Stage GUI_stage;
    private InventoryActor inventoryActor;

    private static HpBar healthBar;

    public static void setHP(int healhPoints) {
        GUI.healhPoints = healhPoints;
    }

    private static int healhPoints;

    public GUI(Stage gui_stage){
        this.GUI_stage = gui_stage;
        healthBar = new HpBar ();
        //new SkillWindow(player.getKlasa());
        //Skin uiSkin = Drop.assets.get("res/json/uiskin.json", Skin.class);
        new InventoryActor(new Inventory(), new DragAndDrop(), new Skin(Gdx.files.internal("json/uiskin.json")));
    }

    public static void render(SpriteBatch batch){
        healthBar.render(batch, 100, healhPoints);
        GUI_stage.draw();
        //skillBar.render(batch, skill);
    }

    public static Stage getGUI_stage() {
        return GUI_stage;
    }
}
