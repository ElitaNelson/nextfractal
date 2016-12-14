/*
 * NextFractal 2.0.0
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015-2017 Andrea Medeghini
 *
 * This file is part of NextFractal.
 *
 * NextFractal is an application for creating fractals and other graphics artifacts.
 *
 * NextFractal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NextFractal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NextFractal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.nextbreakpoint.nextfractal.core.javaFX;

import com.nextbreakpoint.Try;
import com.nextbreakpoint.nextfractal.core.Clip;
import com.nextbreakpoint.nextfractal.core.ImageGenerator;
import com.nextbreakpoint.nextfractal.core.renderer.RendererSize;
import com.nextbreakpoint.nextfractal.core.renderer.RendererTile;
import com.nextbreakpoint.nextfractal.core.renderer.javaFX.JavaFXRendererFactory;
import com.nextbreakpoint.nextfractal.core.utils.DefaultThreadFactory;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.nextbreakpoint.nextfractal.core.Plugins.tryFindFactory;

public class ExportPane extends BorderPane {
	private static Logger logger = Logger.getLogger(ExportPane.class.getName());
	private static final int PADDING = 8;

	private final RendererTile tile;
	private final ExecutorService executor;
	private final ListView<Bitmap> listView;
	private final BooleanObservableValue videoProperty;
	private ExportDelegate delegate;

	public ExportPane(RendererTile tile) {
		this.tile = tile;

		videoProperty = new BooleanObservableValue();
		videoProperty.setValue(false);

		ComboBox<Integer[]> presetsCombobox = new ComboBox<>();
		presetsCombobox.getStyleClass().add("text-small");
		presetsCombobox.setTooltip(new Tooltip("Select image or video size"));
		loadImagePresets(presetsCombobox);
		Integer[] item0 = presetsCombobox.getSelectionModel().getSelectedItem();
		AdvancedTextField widthField = new AdvancedTextField();
		widthField.getStyleClass().add("text-small");
		widthField.setRestrict(getRestriction());
		widthField.setEditable(false);
		widthField.setText(String.valueOf(item0[0]));
		AdvancedTextField heightField = new AdvancedTextField();
		heightField.setRestrict(getRestriction());
		heightField.setEditable(false);
		heightField.setText(String.valueOf(item0[1]));
		heightField.getStyleClass().add("text-small");

		ComboBox<String[]> formatCombobox = new ComboBox<>();
		formatCombobox.getStyleClass().add("text-small");
		formatCombobox.setTooltip(new Tooltip("Select format to export"));
		formatCombobox.getItems().add(new String[] { "PNG image", "png" });
		formatCombobox.getItems().add(new String[] { "MPEG4 video", "mpeg4" });
		formatCombobox.getSelectionModel().select(0);
		formatCombobox.setDisable(true);

		VBox formatBox = new VBox(5);
		formatBox.setAlignment(Pos.CENTER);
		formatBox.getChildren().add(formatCombobox);

		ToggleButton captureButton = new ToggleButton("Capture");
		Button exportButton = new Button("Export");
		Button removeButton = new Button("Remove");
		Button previewButton = new Button("Preview");
		exportButton.setTooltip(new Tooltip("Export image or video"));
		removeButton.setTooltip(new Tooltip("Remove all clips"));
		previewButton.setTooltip(new Tooltip("Show video preview"));
		captureButton.setTooltip(new Tooltip("Enable/disable capture"));

		VBox exportButtons = new VBox(4);
		exportButtons.getChildren().add(exportButton);
		exportButtons.getStyleClass().add("buttons");
		exportButtons.getStyleClass().add("text-small");

		VBox dimensionBox = new VBox(5);
		dimensionBox.setAlignment(Pos.CENTER);
		dimensionBox.getChildren().add(presetsCombobox);

		HBox sizeBox = new HBox(5);
		sizeBox.setAlignment(Pos.CENTER);
		sizeBox.getChildren().add(widthField);
		sizeBox.getChildren().add(heightField);

		VBox exportControls = new VBox(8);
		exportControls.setAlignment(Pos.CENTER_LEFT);
		exportControls.getChildren().add(new Label("Export format"));
		exportControls.getChildren().add(formatBox);
		exportControls.getChildren().add(new Label("Size in pixels"));
		exportControls.getChildren().add(dimensionBox);
		exportControls.getChildren().add(sizeBox);

		VBox clipButtons = new VBox(4);
		clipButtons.getChildren().add(captureButton);
		clipButtons.getChildren().add(removeButton);
		clipButtons.getChildren().add(previewButton);
		clipButtons.getStyleClass().add("buttons");
		clipButtons.getStyleClass().add("text-small");

		VBox clipControls = new VBox(8);
		listView = new ListView<>();
		listView.setFixedCellSize(tile.getTileSize().getHeight() + PADDING);
		listView.getStyleClass().add("clips");
		listView.setCellFactory(view -> new ClipListCell(tile));
		listView.setTooltip(new Tooltip("List of captured clips"));
		clipControls.getChildren().add(new Label("Captured clips"));
		clipControls.getChildren().add(listView);

		listView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Bitmap> c) -> itemSelected(listView));

		VBox box = new VBox(8);
		box.setAlignment(Pos.TOP_CENTER);
		box.getChildren().add(clipControls);
		box.getChildren().add(clipButtons);
		box.getChildren().add(exportControls);
		box.getChildren().add(exportButtons);
		setCenter(box);

		previewButton.setDisable(true);

		getStyleClass().add("export");
		
		presetsCombobox.setConverter(new StringConverter<Integer[]>() {
			@Override
			public String toString(Integer[] item) {
				if (item == null) {
					return null;
				} else {
					if (item[0] == 0 || item[1] == 0) {
						return "Custom";
					} else {
						return item[0] + "\u00D7" + item[1];
					}
				}
			}

			@Override
			public Integer[] fromString(String preset) {
				return null;
			}
		});

		formatCombobox.setConverter(new StringConverter<String[]>() {
			@Override
			public String toString(String[] item) {
				if (item == null) {
					return null;
				} else {
					return item[0];
				}
			}

			@Override
			public String[] fromString(String preset) {
				return null;
			}
		});

		presetsCombobox.setCellFactory(new Callback<ListView<Integer[]>, ListCell<Integer[]>>() {
			@Override
			public ListCell<Integer[]> call(ListView<Integer[]> p) {
				return new ListCell<Integer[]>() {
					private final Label label;
					{
						label = new Label();
					}
					
					@Override
					protected void updateItem(Integer[] item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							label.setText(presetsCombobox.getConverter().toString(item));
							setGraphic(label);
						}
					}
				};
			}
		});

		formatCombobox.setCellFactory(new Callback<ListView<String[]>, ListCell<String[]>>() {
			@Override
			public ListCell<String[]> call(ListView<String[]> p) {
				return new ListCell<String[]>() {
					private final Label label;
					{
						label = new Label();
					}

					@Override
					protected void updateItem(String[] item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							label.setText(formatCombobox.getConverter().toString(item));
							setGraphic(label);
						}
					}
				};
			}
		});

		presetsCombobox.valueProperty().addListener((value, oldItem, newItem) -> {
            if (newItem != null && (newItem[0] == 0 || newItem[1] == 0)) {
                widthField.setEditable(true);
                heightField.setEditable(true);
                if (listView.getItems().size() == 0) {
					widthField.setText("1024");
					heightField.setText("768");
				} else {
					widthField.setText("720");
					heightField.setText("570");
				}
            } else {
                widthField.setEditable(false);
                heightField.setEditable(false);
                if (newItem != null) {
					widthField.setText(String.valueOf(newItem[0]));
					heightField.setText(String.valueOf(newItem[1]));
				} else {
					widthField.setText("0");
					heightField.setText("0");
				}
            }
        });

		formatCombobox.valueProperty().addListener((value, oldItem, newItem) -> {
			if (newItem != null && newItem[1].equals("png")) {
				loadImagePresets(presetsCombobox);
			} else {
				loadVideoPresets(presetsCombobox);
			}
		});

		exportButton.setOnMouseClicked(e -> {
			if (delegate != null) {
				int renderWidth = Integer.parseInt(widthField.getText());
				int renderHeight = Integer.parseInt(heightField.getText());
				delegate.createSession(new RendererSize(renderWidth, renderHeight));
			}
		});

		captureButton.setOnMouseClicked(e -> {
			if (delegate != null) {
				if (captureButton.isSelected()) {
					delegate.startCaptureSession();
				} else {
					delegate.stopCaptureSession();
				}
			}
		});

		removeButton.setOnMouseClicked(e -> {
			if (listView.getItems().size() > 0) {
				listView.getItems().clear();
				videoProperty.setValue(false);
			}
		});

		previewButton.setOnMouseClicked(e -> {
			if (listView.getItems().size() > 0 && delegate != null) {
				delegate.showVideoPreview(listView.getItems().stream()
					.map(bitmap -> (Clip) bitmap.getProperty("clip")).collect(Collectors.toList()));
			}
		});

		videoProperty.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				previewButton.setDisable(false);
				formatCombobox.setDisable(false);
//				loadVideoPresets(presetsCombobox);
				formatCombobox.getSelectionModel().select(1);
			} else {
				previewButton.setDisable(true);
				formatCombobox.setDisable(true);
//				loadImagePresets(presetsCombobox);
				formatCombobox.getSelectionModel().select(0);
			}
		});

		widthProperty().addListener((observable, oldValue, newValue) -> {
			double width = newValue.doubleValue() - getInsets().getLeft() - getInsets().getRight();
			formatCombobox.setPrefWidth(width);
			presetsCombobox.setPrefWidth(width);
			box.setPrefWidth(width);
			box.setMaxWidth(width);
			exportButton.setPrefWidth(width);
			captureButton.setPrefWidth(width);
			removeButton.setPrefWidth(width);
			previewButton.setPrefWidth(width);
		});

		executor = Executors.newSingleThreadExecutor(new DefaultThreadFactory("Export Generator", true, Thread.MIN_PRIORITY));
	}

	private void loadImagePresets(ComboBox<Integer[]> presetsCombobox) {
		presetsCombobox.getItems().clear();
		presetsCombobox.getItems().add(new Integer[] { 0, 0 });
		presetsCombobox.getItems().add(new Integer[] { 8192, 8192 });
		presetsCombobox.getItems().add(new Integer[] { 4096, 4096 });
		presetsCombobox.getItems().add(new Integer[] { 2048, 2048 });
		presetsCombobox.getItems().add(new Integer[] { 1900, 1900 });
		presetsCombobox.getItems().add(new Integer[] { 1900, 1080 });
		presetsCombobox.getItems().add(new Integer[] { 1650, 1650 });
		presetsCombobox.getItems().add(new Integer[] { 1650, 1050 });
		presetsCombobox.getItems().add(new Integer[] { 1024, 1024 });
		presetsCombobox.getItems().add(new Integer[] { 1024, 768 });
		presetsCombobox.getItems().add(new Integer[] { 640, 640 });
		presetsCombobox.getItems().add(new Integer[] { 640, 480 });
		presetsCombobox.getItems().add(new Integer[] { 512, 512 });
		presetsCombobox.getItems().add(new Integer[] { 256, 256 });
		presetsCombobox.getSelectionModel().select(7);
	}

	private void loadVideoPresets(ComboBox<Integer[]> presetsCombobox) {
		presetsCombobox.getItems().clear();
		presetsCombobox.getItems().add(new Integer[] { 0, 0 });
		presetsCombobox.getItems().add(new Integer[] { 1920, 1080 });
		presetsCombobox.getItems().add(new Integer[] { 720, 570 });
		presetsCombobox.getSelectionModel().select(1);
	}

	private void itemSelected(ListView<Bitmap> listView) {
	}

	private DefaultThreadFactory createThreadFactory(String name) {
		return new DefaultThreadFactory(name, true, Thread.MIN_PRIORITY);
	}

	private void submitItem(Clip clip, ImageGenerator generator) {
		executor.submit(() -> Try.of(() -> generator.renderImage(clip.getLastEvent().getScript(), clip.getLastEvent().getMetadata()))
			.ifPresent(pixels -> Platform.runLater(() -> addItem(listView, clip, pixels, generator.getSize()))));
	}

	private void addItem(ListView<Bitmap> listView, Clip clip, IntBuffer pixels, RendererSize size) {
		BrowseBitmap bitmap = new BrowseBitmap(size.getWidth(), size.getHeight(), pixels);
		bitmap.setProperty("clip", clip);
		listView.getItems().add(0, bitmap);
		if (listView.getItems().size() == 1) {
			videoProperty.setValue(true);
		}
	}

	private void removeItem(ListView<Bitmap> listView, int index) {
		listView.getItems().remove(index);
		if (listView.getItems().size() == 0) {
			videoProperty.setValue(false);
		}
	}

	public void appendClip(Clip clip) {
		tryFindFactory(clip.getLastEvent().getPluginId()).map(factory -> factory.createImageGenerator(createThreadFactory("Export Renderer"),
			new JavaFXRendererFactory(), tile, true)).ifPresent(generator -> submitItem(clip, generator));
	}

	protected String getRestriction() {
		return "-?\\d*\\.?\\d*";
	}
	
	public void setExportDelegate(ExportDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	protected void finalize() throws Throwable {
		dispose();
		super.finalize();
	}

	public void dispose() {
		List<ExecutorService> executors = Arrays.asList(executor);
		executors.forEach(executor -> executor.shutdownNow());
		executors.forEach(executor -> await(executor));
	}

	private void await(ExecutorService executor) {
		Try.of(() -> executor.awaitTermination(5000, TimeUnit.MILLISECONDS)).onFailure(e -> logger.warning("Await termination timeout")).execute();
	}
}
