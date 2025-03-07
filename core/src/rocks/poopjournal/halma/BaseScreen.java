package rocks.poopjournal.halma;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import aurelienribon.tweenengine.TweenManager;
import rocks.poopjournal.halma.redesign.MainMenu;

public abstract class BaseScreen extends ScreenAdapter {
    protected Color bgColor;
    protected Skin skin;
    protected Stage stage;
    protected Table layout;
    protected Halma halma;
    protected Listener listener;
    protected Class nextScreen;

    public BaseScreen(Halma halma) {
        this.halma = halma;
        create();
    }

    private void create(){
        bgColor = Color.OLIVE;
        skin = new Skin(Gdx.files.internal("halma.json"));
        stage = new Stage(new ScreenViewport()){
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.BACK){
                    if(halma.getScreen().getClass() != MainMenu.class);
                    try {
                        halma.setScreen((Screen) nextScreen.getConstructors()[0].newInstance(halma));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return super.keyDown(keyCode);
            }
        };

        layout = new Table(skin);
        listener = new Listener();

        this.nextScreen = MainMenu.class;

        Gdx.input.setInputProcessor(stage);
        
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        stage.addActor(layout);

        layout.setFillParent(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void clicked(Actor a){
        System.out.println("clicked");
    }
    public class Listener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            BaseScreen.this.clicked(event.getListenerActor());
        }
    }
    protected void addButtonsToListener(Actor ... actors){
        for(Actor a : actors)
            a.addListener(listener);
    }
    protected void addButtonsToListener(LinkedList<Actor> list){
        for(Actor a : list)
            a.addListener(listener);
    }
    protected void addActorsToStage(Actor ... actors){
        for(Actor a : actors)
            stage.addActor(a);
    }
    protected void addActorsToStage(LinkedList<Actor> actors){
        for(Actor a : actors)
            stage.addActor(a);
    }
}
