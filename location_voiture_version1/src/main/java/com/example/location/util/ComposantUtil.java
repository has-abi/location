package com.example.location.util;




import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class ComposantUtil {
		public static Label label;
		public static Button button;
		public static ImageView imageView;
		public static Text text;
		public static Pane pane;
		public static Text createText(double layoutX,double layoutY,String texte,double wrappingWidth,String style) {
			text = new Text();
			text.setLayoutX(layoutX);
			text.setLayoutY(layoutY);
			text.setText(texte);
			text.setWrappingWidth(wrappingWidth);
			text.setStrokeType(StrokeType.OUTSIDE);
			text.setStrokeWidth(0);
			text.setStyle(style);
			text.setFill(Color.rgb(255, 255, 255));
			return text;
		}
		public static Label createLabel( double layoutX,double layoutY,String text,String style) {
			label = new Label(text);
			label.setLayoutX(layoutX);
			label.setLayoutY(layoutY);
			label.setStyle(style);
			label.setAlignment(Pos.CENTER);
			return label;
		}
		public static Button createButton( double layoutX,double layoutY,String text,String style) {
			button = new Button(text);
			button.setLayoutX(layoutX);
			button.setLayoutY(layoutY);
			button.setStyle(style);
			return button;
		}
		public static Pane createPane( double layoutX,double layoutY,double prefHeight,double prefWidth,String style) {
			pane = new Pane();
			pane.setLayoutX(layoutX);
			pane.setLayoutY(layoutY);
			pane.setPrefHeight(prefHeight);
			pane.setPrefWidth(prefWidth);
			pane.setStyle(style);
			return pane;
		}
		public static ImageView createImageView(double layoutX,double layoutY,double fitHeight,double fitWidth,String image) {
			imageView = new ImageView();
			imageView.setLayoutX(layoutX);
			imageView.setLayoutY(layoutY);
			imageView.setFitHeight(fitHeight);
			imageView.setFitWidth(fitWidth);
				imageView.setImage(new Image(image));
			
			//imageView.setPickOnBounds(true);
			//imageView.setPreserveRatio(true);
			return imageView;
		}
		
		
}
