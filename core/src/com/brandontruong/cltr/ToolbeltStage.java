package com.brandontruong.cltr;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by btru on 5/22/15.
 */
public class ToolbeltStage extends Stage{
    Toolbelt toolbelt;

    public ToolbeltStage(Viewport viewport, Toolbelt t) {
        super(viewport);
        toolbelt = t;
    }
}
