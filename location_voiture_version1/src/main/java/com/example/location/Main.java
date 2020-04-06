package com.example.location;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.location.config.StageManager;
import com.example.location.views.FxmlView;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


@SpringBootApplication
public class Main extends Application {

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
    	stage.initStyle(StageStyle.UNDECORATED);
        stageManager = springContext.getBean(StageManager.class, stage);
        
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

 
    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }

}
