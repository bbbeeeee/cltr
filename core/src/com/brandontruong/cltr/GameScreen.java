package com.brandontruong.cltr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by btroo on 5/18/15.
 */
public class GameScreen implements Screen, InputProcessor{
    private Environment environment;
    private EnvironmentRenderer environmentRenderer;
    OrthographicCamera camera;
    private BitmapFont font;
    private ShapeRenderer sr = new ShapeRenderer();
    private Stage stage;
    private Toolbelt toolbelt;
    private ToolbeltStage toolbeltStage;
    private Table table = new Table();
    private int _i;
    private BlockActor selected;
    private float x, y;
    private float leftOffset;
    private Viewport viewport;

    /**
     * GameScreen constructor. Has environment, renderer, toolbelt, and toolbelt stage.
     * @param grid
     * @param v
     */
    public GameScreen(Grid grid, Viewport v) {
        viewport = v;
        environment = new Environment(grid, viewport, this);
        environmentRenderer = new EnvironmentRenderer(environment, viewport);
        toolbelt = grid.toolbelt;
        toolbeltStage = new ToolbeltStage(viewport, toolbelt);
        leftOffset = environmentRenderer.leftOffset;
    }


    @Override
    public void show() {
        int amount;

        // make the table be oriented from top to bottom
        table.top().left();
        table.setX(0);
        table.setY(0);

        table.setWidth(environmentRenderer.leftOffset);
        table.setHeight(Gdx.graphics.getHeight());

        // offsets for the blocks
        x = (environmentRenderer.leftOffset - environmentRenderer.blockWidth * 2) / 2;
        y = Gdx.graphics.getHeight();

        changeSelect(0);

        toolbeltStage.addActor(table);

        Gdx.input.setInputProcessor(toolbeltStage);

        ClickListener tStageTouch = new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int absoluteX = (int)
                        Math.ceil((x - leftOffset)
                                / environmentRenderer.blockWidth);

                int absoluteY = (int)
                        Math.ceil(y
                                / environmentRenderer.blockWidth);


                if(environment.grid.isNotOutOfBounds(absoluteX, absoluteY) ){
                    if(toolbelt.blocks[toolbelt.selected] < 0){
                        toolbelt.selected = 0;
                    } else {
                        environment.grid.g[absoluteX - 1][absoluteY - 1].replace(toolbelt.selected);
                        toolbelt.blocks[toolbelt.selected] -= 1;
                        L.CLTR(toolbelt.blocks[toolbelt.selected]);
                        L.CLTR(toolbelt.selected);
                        changeSelect(toolbelt.selected);
                    }
                }

                return true;
            }
        };

        toolbeltStage.addListener(tStageTouch);
    }

    /**
     * Changes the selected block.
     * @param newIndex
     */
    public void changeSelect(int newIndex){
        int amount;

        table.clear();

        for(int i = 0, j = 0; i < toolbelt.blocks.length; i++){
            amount = toolbelt.blocks[i];

            BlockActor b = new BlockActor(i,
                    x,
                    y,
                    environmentRenderer.blockWidth,
                    environmentRenderer.blockHeight);

            // Use as variable to denote current index for ClickListener
            final int _i = i;
            ClickListener c = new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    changeSelect(_i);
                    toolbelt.selected = _i;
                    L.CLTR(toolbelt.selected);
                    return true;
                }
            };

            // b.setTouchable();
            b.addListener(c);

            if(amount > 0) {

                // Top piece should be selected
                if(newIndex == i){
                    selected = new BlockActor(Block.SELECTEDBLOCK,
                            x,
                            y,
                            environmentRenderer.blockWidth,
                            environmentRenderer.blockHeight);

                    Stack stack = new Stack();
                    stack.add(b);
                    stack.add(selected);
                    table.add(stack).padTop(20).padLeft(x);

                } else{
                    table.add(b).padTop(20).padLeft(x);
                }

                table.row();

                j++;
            }

            y -= environmentRenderer.blockHeight;
        }

    }

    @Override
    public void render(float delta) {
        environment.update(delta);
        environmentRenderer.render();
        toolbeltStage.act();
        toolbeltStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        environmentRenderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
