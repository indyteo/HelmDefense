package fr.helmdefense.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.helmdefense.controller.Controller.Gamemode;
import fr.helmdefense.controller.Controls.ControlsGroup;
import fr.helmdefense.model.level.Difficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OptionsController implements Initializable {
	private Controller main;
	private Controls selectedControl;
	private Button selectedButton;
	private Difficulty selectedDifficulty;
	
	ScrollPane root;
	
	@FXML
	MenuButton difficultyButton;
	@FXML
	ToggleGroup difficulty;
	@FXML
	RadioMenuItem easyDifficulty;
	@FXML
	RadioMenuItem normalDifficulty;
	@FXML
	RadioMenuItem hardDifficulty;

	@FXML
	Slider speedness;
	
	@FXML
	MenuButton gamemodeButton;
	@FXML
	ToggleGroup gamemode;
	@FXML
	RadioMenuItem normalGamemode;
	@FXML
	RadioMenuItem demoGamemode;

	@FXML
	VBox controlsLabelBox;
	@FXML
	VBox controlsButtonsBox;

	@FXML
	Button restartLevelButton;
	
	public OptionsController(Controller main) {
		this.main = main;
		
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/helmdefense/view/Options.fxml"));
			loader.setController(this);
			this.root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setDifficulty(Difficulty.DEFAULT);
		this.setGamemode(Gamemode.DEFAULT);
	}

	@FXML
	void restartLevelAction(ActionEvent event) {
		this.main.restartLevel();
	}

	@FXML
	void returnToMenuAction(ActionEvent event) {
		this.main.returnToMenu();
	}
	
	void handleKeyboardInput(KeyEvent event) {
		if (this.selectedControl != null) {
			this.selectedControl.setKey(event.getCode());
			this.cancelControlSelection();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.easyDifficulty.setOnAction(event -> this.setDifficulty(Difficulty.EASY, false));
		this.normalDifficulty.setOnAction(event -> this.setDifficulty(Difficulty.NORMAL, false));
		this.hardDifficulty.setOnAction(event -> this.setDifficulty(Difficulty.HARD, false));
		this.speedness.valueProperty().bindBidirectional(this.main.speedness.valueProperty());
		this.normalGamemode.setOnAction(event -> this.setGamemode(Gamemode.NORMAL, false));
		this.demoGamemode.setOnAction(event -> this.setGamemode(Gamemode.DEMO, false));
		
		for (ControlsGroup group : ControlsGroup.values()) {
			this.createControlsLine(group.getName(), null);
			for (Controls control : Controls.inGroup(group))
				this.createControlsLine(control.getName(), control);
		}
	}
	
	void setDifficulty(Difficulty difficulty) {
		this.setDifficulty(difficulty, true);
	}
	
	private void setDifficulty(Difficulty difficulty, boolean extern) {
		this.selectedDifficulty = difficulty;
		this.difficultyButton.setText(difficulty.toString());
		if (extern) {
			switch (difficulty) {
			case EASY:
				this.difficulty.selectToggle(this.easyDifficulty);
				break;
			case NORMAL:
				this.difficulty.selectToggle(this.normalDifficulty);
				break;
			case HARD:
				this.difficulty.selectToggle(this.hardDifficulty);
				break;
			}
		}
	}
	
	void setGamemode(Gamemode mode) {
		this.setGamemode(mode, true);
	}
	
	private void setGamemode(Gamemode mode, boolean extern) {
		this.main.setGamemode(mode);
		this.gamemodeButton.setText(mode.toString());
		if (extern) {
			switch (mode) {
			case NORMAL:
				this.gamemode.selectToggle(this.normalGamemode);
				break;
			case DEMO:
				this.gamemode.selectToggle(this.demoGamemode);
				break;
			}
		}
	}
	
	private void createControlsLine(String name, Controls control) {
		Label label = new Label(name);
		Region button;
		
		if (control != null) {
			label.setFont(new Font(18));
			button = new Button();
			((Button) button).textProperty().bind(control.keyProperty().asString());
			button.setOnMouseClicked(event -> {
				this.cancelControlSelection();
				this.selectedButton = (Button) button;
				button.setBorder(new Border(new BorderStroke(Color.DODGERBLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(3))));
				this.selectedControl = control;
				this.main.main.setOnMouseClicked(e -> this.cancelControlSelection());
			});
		}
		else {
			label.setFont(new Font("System Bold", 24));
			button = new Region();
		}
		
		label.setPrefHeight(30);
		button.setPrefSize(150, 30);
		this.controlsLabelBox.getChildren().add(label);
		this.controlsButtonsBox.getChildren().add(button);
	}
	
	private void cancelControlSelection() {
		this.selectedControl = null;
		this.main.main.setOnMouseClicked(null);
		if (this.selectedButton != null) {
			this.selectedButton.setBorder(null);
			this.selectedButton = null;
		}
	}
	
	Difficulty getSelectedDifficulty() {
		return this.selectedDifficulty;
	}
	
	void show() {
		this.main.main.setCenter(this.root);
		Controller.setNodesVisibility(false, this.main.moneyBox, this.main.main.getLeft(), this.main.main.getRight(),
				this.main.buyInfoLabel, this.main.levelControlButtons);
		this.restartLevelButton.setDisable(! this.main.hasLevelLoaded());
		this.difficultyButton.setDisable(this.main.hasLevelLoaded());
	}
}