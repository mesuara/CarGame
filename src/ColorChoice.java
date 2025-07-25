import java.awt.Color;

public class ColorChoice {

	public static Color getColorFromString(String colorName) {
        if (colorName == null) {
            return Color.BLACK; // Default color if input is null
        }

        colorName = colorName.trim().toUpperCase(); // Normalize the input

        switch (colorName) {
            case "BLACK":
                return Color.BLACK;
            case "BLUE":
                return Color.BLUE;
            case "CYAN":
                return Color.CYAN;
            case "DARK_GRAY":
                return Color.DARK_GRAY;
            case "GRAY":
                return Color.GRAY;
            case "GREEN":
                return Color.GREEN;
            case "LIGHT_GRAY":
                return Color.LIGHT_GRAY;
            case "MAGENTA":
                return Color.MAGENTA;
            case "ORANGE":
                return Color.ORANGE;
            case "PINK":
                return Color.PINK;
            case "RED":
                return Color.RED;
            case "WHITE":
                return Color.WHITE;
            case "YELLOW":
                return Color.YELLOW;
            default:
                throw new IllegalArgumentException("Unknown color: " + colorName);
        }
	}
}
