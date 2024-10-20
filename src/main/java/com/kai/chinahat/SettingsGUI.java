package com.kai.chinahat;

import net.minecraft.client.gui.*;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class SettingsGUI extends GuiScreen implements GuiPageButtonList.GuiResponder {

	// Category selector variable
	private int category = 0;

	// Create buttons
	private GuiButton button0;
	private GuiSlider slider1;
	private GuiButton button2;
	private GuiSlider slider3;
	private GuiButton button4;
	private GuiButton button5;
	private GuiButton button6;
	private GuiButton button7;
	private GuiSlider slider8;
	private GuiButton button9;
	private GuiSlider slider10;
	private GuiTextField text11;
	private GuiTextField text12;
	private GuiTextField text13;
	private GuiButton button14;
	private GuiSlider slider15;
	private GuiTextField text16;
	private GuiTextField text17;
	private GuiTextField text18;
	private GuiButton button19;
	private GuiSlider slider20;
	private GuiTextField text21;
	private GuiTextField text22;
	private GuiTextField text23;

	private GuiButton button24;
	private GuiButton button25;
	private GuiButton button26;
	private GuiButton button27;

	@Override
	public void initGui() {
		super.initGui();

		// Button locations
		int x_middle = this.width / 2 - this.width / 10;
		int x_left = this.width / 2 - this.width / 10 - this.width / 5 - 25;
		int x_right = this.width / 2 - this.width / 10 + this.width / 5 + 25;

		// Set button data

		// Main toggle
		button0 = new GuiButton(0, x_middle, this.height / 10,this.width /5, 20, button0text());

		// Speed slider
		slider1 = new GuiSlider(this, 1, x_left, this.height / 10 + 30, "Speed: ", 1, 100, Settings.speed, new GuiSlider.FormatHelper() {
			@Override
			public String getText(int id, String displayString, float value) {
				return displayString + (int) value; // Display the current value
			}
		});

		// 1st Person toggle
		button2 = new GuiButton(2, x_middle, this.height / 10 + 30, this.width /5, 20, button2text());

		// Saturation slider
		slider3 = new GuiSlider(this, 3, x_right, this.height / 10 + 30, "Saturation: ", 0.00f, 1.00f, Settings.saturation, new GuiSlider.FormatHelper() {
			@Override
			public String getText(int id, String displayString, float value) {
				return displayString + Math.round(value * 100f) / 100f; // Display the current value
			}
		});



		// Categoreis
		button4 = new GuiButton(4, x_left, this.height / 10 + 60, this.width /5, 20, button4text());
		button5 = new GuiButton(5, x_middle, this.height / 10 + 60, this.width /5, 20, button5text());
		button6 = new GuiButton(6, x_right, this.height / 10 + 60, this.width /5, 20, button6text());



		// Outline category
		// Outline toggle
		button7 = new GuiButton(7, x_left, this.height / 10 + 120, this.width /5, 20, button7text());

		// Outline Thickness
		slider8 = new GuiSlider(this, 8, x_right, this.height / 10 + 120, "Thickness: ", 0.00f, 5.00f, Settings.outline_thickness, new GuiSlider.FormatHelper() {
			@Override
			public String getText(int id, String displayString, float value) {
				return displayString + Math.round(value * 100f) / 100f; // Display the current value
			}
		});

		// Outline Mode
		button9 = new GuiButton(9, x_left, this.height / 10 + 90, this.width /5, 20, button9text());

		// Outline Opacity
		slider10 = new GuiSlider(this, 10, x_right, this.height / 10 + 90, "Opacity: ", 0.00f, 1.00f, Settings.outline_opacity, new GuiSlider.FormatHelper() {
			@Override
			public String getText(int id, String displayString, float value) {
				return displayString + Math.round(value * 100f) / 100f; // Display the current value
			}
		});

		// Solid color text field
		text11 = new GuiTextField(11, this.fontRendererObj, this.width / 2 - this.width / 10, this.height / 10 + 105, 50, 18);
		// Gradient1 text field
		text12 = new GuiTextField(12, this.fontRendererObj, this.width / 2 - 25, this.height / 10 + 105, 50, 18);
		// Gradient2 text field
		text13 = new GuiTextField(13, this.fontRendererObj, this.width / 2 - 50 + this.width / 10, this.height / 10 + 105, 50, 18);





		// Top Hat
		// Top Hat Mode
		button14 = new GuiButton(14, x_left, this.height / 10 + 90, this.width /5, 20, button14text());

		// Top Hat Opacity
		slider15 = new GuiSlider(this, 15, x_right, this.height / 10 + 90, "Opacity: ", 0.00f, 1.00f, Settings.top_opacity, new GuiSlider.FormatHelper() {
			@Override
			public String getText(int id, String displayString, float value) {
				return displayString + Math.round(value * 100f) / 100f; // Display the current value
			}
		});

		// Solid color text field
		text16 = new GuiTextField(16, this.fontRendererObj, this.width / 2 - this.width / 10, this.height / 10 + 105, 50, 18);
		// Gradient1 text field
		text17 = new GuiTextField(17, this.fontRendererObj, this.width / 2 - 25, this.height / 10 + 105, 50, 18);
		// Gradient2 text field
		text18 = new GuiTextField(18, this.fontRendererObj, this.width / 2 - 50 + this.width / 10, this.height / 10 + 105, 50, 18);





		// Bottom Hat
		// Bottom Hat Mode
		button19 = new GuiButton(19, x_left, this.height / 10 + 90, this.width /5, 20, button19text());

		// Bottom Hat Opacity
		slider20 = new GuiSlider(this, 20, x_right, this.height / 10 + 90, "Opacity: ", 0.00f, 1.00f, Settings.bottom_opacity, new GuiSlider.FormatHelper() {
			@Override
			public String getText(int id, String displayString, float value) {
				return displayString + Math.round(value * 100f) / 100f; // Display the current value
			}
		});

		// Solid color text field
		text21 = new GuiTextField(21, this.fontRendererObj, this.width / 2 - this.width / 10, this.height / 10 + 105, 50, 18);
		// Gradient1 text field
		text22 = new GuiTextField(22, this.fontRendererObj, this.width / 2 - 25, this.height / 10 + 105, 50, 18);
		// Gradient2 text field
		text23 = new GuiTextField(23, this.fontRendererObj, this.width / 2 - 50 + this.width / 10, this.height / 10 + 105, 50, 18);





		// Cycle on_others
		button24 = new GuiButton(24, x_left, this.height / 10,this.width /5, 20, button24text());
		// Toggle on_self
		button25 = new GuiButton(25, x_right, this.height / 10,this.width /5, 20, button25text());
		// Toggle offset
		button26 = new GuiButton(26, x_left, this.height / 10 - 30,this.width /5, 20, button26text());
		// Toggle tilt
		button27 = new GuiButton(27, x_right, this.height / 10 - 30,this.width /5, 20, button27text());





		// Set slider size
		slider1.width = this.width /5;
		slider1.height = 20;

		slider3.width = this.width /5;
		slider3.height = 20;

		slider8.width = this.width /5;
		slider8.height = 20;

		slider10.width = this.width /5;
		slider10.height = 20;

		slider15.width = this.width /5;
		slider15.height = 20;

		slider20.width = this.width /5;
		slider20.height = 20;



		// Set text field properties
		// Outline
		text11.setMaxStringLength(7);
		text11.setText(ConfigHandler.getHex(Settings.outline_solid));
		text11.setFocused(false);

		text12.setMaxStringLength(7);
		text12.setText(ConfigHandler.getHex(Settings.outline_gradient1));
		text12.setFocused(false);

		text13.setMaxStringLength(7);
		text13.setText(ConfigHandler.getHex(Settings.outline_gradient2));
		text13.setFocused(false);

		// Top Hat
		text16.setMaxStringLength(7);
		text16.setText(ConfigHandler.getHex(Settings.top_solid));
		text16.setFocused(false);

		text17.setMaxStringLength(7);
		text17.setText(ConfigHandler.getHex(Settings.top_gradient1));
		text17.setFocused(false);

		text18.setMaxStringLength(7);
		text18.setText(ConfigHandler.getHex(Settings.top_gradient2));
		text18.setFocused(false);

		// Bottom Hat
		text21.setMaxStringLength(7);
		text21.setText(ConfigHandler.getHex(Settings.bottom_solid));
		text21.setFocused(false);

		text22.setMaxStringLength(7);
		text22.setText(ConfigHandler.getHex(Settings.bottom_gradient1));
		text22.setFocused(false);

		text23.setMaxStringLength(7);
		text23.setText(ConfigHandler.getHex(Settings.bottom_gradient2));
		text23.setFocused(false);



		// Render default buttons
		this.buttonList.add(button0);
		this.buttonList.add(slider1);
		this.buttonList.add(button2);
		this.buttonList.add(slider3);
		this.buttonList.add(button4);
		this.buttonList.add(button5);
		this.buttonList.add(button6);

		this.buttonList.add(button24);
		this.buttonList.add(button25);
		this.buttonList.add(button26);
		this.buttonList.add(button27);
	}


	// Re-Render buttons (triggered when category changes)
	private void updateButtons() {
		this.buttonList.clear();

		this.buttonList.add(button0);
		this.buttonList.add(slider1);
		this.buttonList.add(button2);
		this.buttonList.add(slider3);
		this.buttonList.add(button4);
		this.buttonList.add(button5);
		this.buttonList.add(button6);

		this.buttonList.add(button24);
		this.buttonList.add(button25);
		this.buttonList.add(button26);
		this.buttonList.add(button27);

		if (category == 1) {
			this.buttonList.add(button7);
			this.buttonList.add(slider8);
			this.buttonList.add(button9);
			this.buttonList.add(slider10);
		}

		else if (category == 2) {
			this.buttonList.add(button14);
			this.buttonList.add(slider15);
		}

		else if (category == 3) {
			this.buttonList.add(button19);
			this.buttonList.add(slider20);
		}
	}


	// Dynamic button names
	private String button0text() {
		return Settings.hat ? "Hat: ON" : "Hat: OFF";
	}
	private String button2text() {
		return Settings.first_person ? "1stPerson: ON" : "1stPerson: OFF";
	}

	private String button4text() {
		if (category == 1)
			return "Outline (Selected)";
		return "Outline";
	}
	private String button5text() {
		if (category == 2)
			return "Top Hat (Selected)";
		return "Top Hat";
	}
	private String button6text() {
		if (category == 3)
			return "Bottom Hat (Selected)";
		return "Bottom Hat";
	}

	private String button7text() {
		return Settings.outline ? "Outline: ON" : "Outline: OFF";
	}

	private String button9text() {
		return "Mode: " + Settings.outline_mode;
	}

	private String button14text() {
		return "Mode: " + Settings.top_mode;
	}

	private String button19text() {
		return "Mode: " + Settings.bottom_mode;
	}

	private String button24text() {
		return "On Others: " + Settings.on_others;
	}
	private String button25text() {
		return Settings.on_self ? "On Self: ON" : "On Self: OFF";
	}
	private String button26text() {
		return "Offset: " + Settings.offset;
	}
	private String button27text() {
		return Settings.tilt ? "Tilt: ON" : "Tilt: OFF";
	}


	// Actions on button press
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 0:
				Settings.hat = !Settings.hat;
				ConfigHandler.saveConfig();
				button0.displayString = button0text();
				break;
			case 2:
				Settings.first_person = !Settings.first_person;
				ConfigHandler.saveConfig();
				button2.displayString = button2text();
				break;
			case 4:
				category = 1;
				button4.displayString = button4text();
				button5.displayString = button5text();
				button6.displayString = button6text();
				updateButtons();
				break;
			case 5:
				category = 2;
				button4.displayString = button4text();
				button5.displayString = button5text();
				button6.displayString = button6text();
				updateButtons();
				break;
			case 6:
				category = 3;
				button4.displayString = button4text();
				button5.displayString = button5text();
				button6.displayString = button6text();
				updateButtons();
				break;
			case 7:
				Settings.outline = !Settings.outline;
				ConfigHandler.saveConfig();
				button7.displayString = button7text();
				break;
			case 9:
				switch (Settings.outline_mode) {
					case "Rainbow":
						Settings.outline_mode = "Solid";
						break;
					case "Solid":
						Settings.outline_mode = "Gradient";
						break;
					case "Gradient":
						Settings.outline_mode = "Rainbow";
						break;
				}
				ConfigHandler.saveConfig();
				button9.displayString = button9text();
				break;
			case 14:
				switch (Settings.top_mode) {
					case "Rainbow":
						Settings.top_mode = "Solid";
						break;
					case "Solid":
						Settings.top_mode = "Gradient";
						break;
					case "Gradient":
						Settings.top_mode = "Rainbow";
						break;
				}
				ConfigHandler.saveConfig();
				button14.displayString = button14text();
				break;
			case 19:
				switch (Settings.bottom_mode) {
					case "Rainbow":
						Settings.bottom_mode = "Solid";
						break;
					case "Solid":
						Settings.bottom_mode = "Gradient";
						break;
					case "Gradient":
						Settings.bottom_mode = "Rainbow";
						break;
				}
				ConfigHandler.saveConfig();
				button19.displayString = button19text();
				break;
			case 24:
				switch (Settings.on_others) {
					case "None":
						Settings.on_others = "Custom";
						break;
					case "Custom":
						Settings.on_others = "Party";
						break;
					case "Party":
						Settings.on_others = "Custom+Party";
						break;
					case "Custom+Party":
						Settings.on_others = "All";
						break;
					case "All":
						Settings.on_others = "None";
						break;
				}
				ConfigHandler.saveConfig();
				button24.displayString = button24text();
				break;
			case 25:
				Settings.on_self = !Settings.on_self;
				ConfigHandler.saveConfig();
				button25.displayString = button25text();
				break;
			case 26:
				switch (Settings.offset) {
					case "Everyone":
						Settings.offset = "Others";
						break;
					case "Others":
						Settings.offset = "Self";
						break;
					case "Self":
						Settings.offset = "Nobody";
						break;
					case "Nobody":
						Settings.offset = "Everyone";
						break;
				}
				ConfigHandler.saveConfig();
				button26.displayString = button26text();
				break;
			case 27:
				Settings.tilt = !Settings.tilt;
				ConfigHandler.saveConfig();
				button27.displayString = button27text();
				break;
		}
	}


	// Slider actions
	@Override
	public void onTick(int id, float value) {
		switch (id) {
			case 1:
				Settings.speed = (int) value;
				ConfigHandler.saveConfig();
				break;
			case 3:
				Settings.saturation = Math.round(value * 100f) / 100f;
				ConfigHandler.saveConfig();
				break;
			case 8:
				Settings.outline_thickness = Math.round(value * 100f) / 100f;
				ConfigHandler.saveConfig();
				break;
			case 10:
				Settings.outline_opacity = Math.round(value * 100f) / 100f;
				ConfigHandler.saveConfig();
				break;
			case 15:
				Settings.top_opacity = Math.round(value * 100f) / 100f;
				ConfigHandler.saveConfig();
				break;
			case 20:
				Settings.bottom_opacity = Math.round(value * 100f) / 100f;
				ConfigHandler.saveConfig();
				break;
		}
	}



	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (typedChar == '#')
			return;

		super.keyTyped(typedChar, keyCode);

		// Allow typing into the text field
		if (category == 1 && text11.isFocused()) {
			// If Ctrl+V and first character of the clipboard text is '#' empty textfield
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text11);
			// Let key go to textfield if conditions met
			text11.textboxKeyTyped(typedChar, keyCode);
			// Check if the first character is a '#' and if not set it to one
			checkFirstChar(text11);
			// Check if color should be saved and save it
			String text = text11.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.outline_solid = v);
				ConfigHandler.saveConfig();
			}
		}
		if (category == 1 && text12.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text12);
			text12.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text12);
			String text = text12.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.outline_gradient1 = v);
				ConfigHandler.saveConfig();
			}
		}
		if (category == 1 && text13.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text13);
			text13.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text13);
			String text = text13.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.outline_gradient2 = v);
				ConfigHandler.saveConfig();
			}
		}



		if (category == 2 && text16.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text16);
			text16.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text16);
			String text = text16.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.top_solid = v);
				ConfigHandler.saveConfig();
			}
		}
		if (category == 2 && text17.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text17);
			text17.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text17);
			String text = text17.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.top_gradient1 = v);
				ConfigHandler.saveConfig();
			}
		}
		if (category == 2 && text18.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text18);
			text18.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text18);
			String text = text18.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.top_gradient2 = v);
				ConfigHandler.saveConfig();
			}
		}



		if (category == 3 && text21.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text21);
			text21.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text21);
			String text = text21.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.bottom_solid = v);
				ConfigHandler.saveConfig();
			}
		}
		if (category == 3 && text22.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text22);
			text22.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text22);
			String text = text22.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.bottom_gradient1 = v);
				ConfigHandler.saveConfig();
			}
		}
		if (category == 3 && text23.isFocused()) {
			if ((isCtrlKeyDown()) && keyCode == Keyboard.KEY_V)
				checkClipboard(text23);
			text23.textboxKeyTyped(typedChar, keyCode);
			checkFirstChar(text23);
			String text = text23.getText();
			if (text.length() == 7 && text.charAt(0) == '#') {
				ConfigHandler.parseColor(text).ifPresent(v -> Settings.bottom_gradient2 = v);
				ConfigHandler.saveConfig();
			}
		}
	}

	// Check if clipboard text starts with a '#'
	private void checkClipboard(GuiTextField field) {
		String clipboardText = getClipboardString();
		if (!clipboardText.isEmpty())
			if (clipboardText.charAt(0) == '#')
				field.setText("");
	}

	// Check for "#"
	private void checkFirstChar(GuiTextField field) {
		String text = field.getText();
		if (!text.isEmpty()) {
			if (text.charAt(0) != '#') {
				text = "#" + text;
				if (text.length() > 7)
					text = text.substring(0, 7);
				field.setText(text);
			}
		}
		else
			field.setText("#");
	}



	// Handle text field focusing
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		// Make text fields clickable depending on category
		if (category == 1) {
			text11.mouseClicked(mouseX, mouseY, mouseButton);
			text12.mouseClicked(mouseX, mouseY, mouseButton);
			text13.mouseClicked(mouseX, mouseY, mouseButton);
		}
		if (category == 2) {
			text16.mouseClicked(mouseX, mouseY, mouseButton);
			text17.mouseClicked(mouseX, mouseY, mouseButton);
			text18.mouseClicked(mouseX, mouseY, mouseButton);
		}
		if (category == 3) {
			text21.mouseClicked(mouseX, mouseY, mouseButton);
			text22.mouseClicked(mouseX, mouseY, mouseButton);
			text23.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}



	// Idk, is needed for rendering the gui --- I know now, it renders shit
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// Draw the background and the buttons
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "ChinaHat Settings", this.width / 2, this.height / 20, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);


		// slider1 tooltip (speed)
		if (isMouseOverSlider(slider1, mouseX, mouseY))
			drawHoveringText(Arrays.asList("Speed (Rainbow/Gradient)", "Lower = Faster"), mouseX, mouseY);

		// slider3 tooltip (saturation)
		if (isMouseOverSlider(slider3, mouseX, mouseY))
			drawHoveringText(Arrays.asList("Saturation (Rainbow only)", "0 = Grayscale"), mouseX, mouseY);

		// button26 tooltip (offset)
		if (isMouseOverButton(button26, mouseX, mouseY))
			drawHoveringText(Arrays.asList("Offset hat depending on if helmet/skull/block is worn on head.", "The offset itself is automatic! This setting only decides to whom to apply it."), mouseX, mouseY);

		// button27 tooltip (tilt)
		if (isMouseOverButton(button27, mouseX, mouseY))
			drawHoveringText(Collections.singletonList("Tilt hat with head"), mouseX, mouseY);


		// Draw info text
		if (category != 0) {
			this.fontRendererObj.drawString("Solid Color:", this.width / 2 - this.width / 10, this.height / 10 + 90, 0xFFFFFF);
			this.fontRendererObj.drawString("Gradient 1:", this.width / 2 - 25, this.height / 10 + 90, 0xFFFFFF);
			this.fontRendererObj.drawString("Gradient 2:", this.width / 2 - 50 + this.width / 10, this.height / 10 + 90, 0xFFFFFF);
		}
		// Draw the text fields
		if (category == 1) {
			text11.drawTextBox();
			text12.drawTextBox();
			text13.drawTextBox();
		}
		if (category == 2) {
			text16.drawTextBox();
			text17.drawTextBox();
			text18.drawTextBox();
		}
		if (category == 3) {
			text21.drawTextBox();
			text22.drawTextBox();
			text23.drawTextBox();
		}
	}

	// Helper method to check if the mouse is over a slider
	private boolean isMouseOverSlider(GuiSlider slider, int mouseX, int mouseY) {
		return mouseX >= slider.xPosition && mouseX <= slider.xPosition + slider.width &&
		mouseY >= slider.yPosition && mouseY <= slider.yPosition + slider.height;
	}

	// Helper method to check if the mouse is over a button
	private boolean isMouseOverButton(GuiButton button, int mouseX, int mouseY) {
		return mouseX >= button.xPosition && mouseX <= button.xPosition + button.width &&
		mouseY >= button.yPosition && mouseY <= button.yPosition + button.height;
	}





	// Goofy ah random shit I can't delete cuz slider implement wants it

	@Override
	public void func_175321_a(int i, boolean b) {

	}

	@Override
	public void func_175319_a(int i, String s) {

	}

}