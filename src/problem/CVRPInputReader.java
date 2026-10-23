package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class CVRPInputReader {
    private record InputLine(int number, String content) {}

    private CVRPInputReader() {}

    public static CVRPProblem read(Path path) throws IOException {
        List<InputLine> lines = readDataLines(path);
        int[] cursor = {0};

        int numberOfCities = readSingleValue(lines, cursor, "CITIES");
        if (numberOfCities < 2) {
            throw new IllegalArgumentException("CITIES must be at least 2 (depot plus one customer).");
        }
        int numberOfVehicles = readSingleValue(lines, cursor, "VEHICLES");
        if (numberOfVehicles < 1) {
            throw new IllegalArgumentException("VEHICLES must be at least 1.");
        }
        int[] vehicleCapacities = readValues(lines, cursor, "CAPACITIES", numberOfVehicles);
        int[] demands = readValues(lines, cursor, "DEMANDS", numberOfCities);
        InputLine matrixHeader = nextLine(lines, cursor);
        if (!matrixHeader.content().equals("DISTANCE_MATRIX")) {
            throw invalidLine(matrixHeader, "Expected DISTANCE_MATRIX.");
        }

        long totalCapacity = 0;
        for (int i = 0; i < vehicleCapacities.length; i++) {
            if (vehicleCapacities[i] <= 0) {
                throw new IllegalArgumentException("Vehicle capacity " + (i + 1) + " must be positive.");
            }
            totalCapacity += vehicleCapacities[i];
        }
        if (demands[0] != 0) {
            throw new IllegalArgumentException("The depot (city 0) must have demand 0.");
        }
        long totalDemand = 0;
        int maximumCapacity = 0;
        for (int capacity : vehicleCapacities) {
            maximumCapacity = Math.max(maximumCapacity, capacity);
        }
        for (int city = 1; city < demands.length; city++) {
            if (demands[city] < 0) {
                throw new IllegalArgumentException("Demand for city " + city + " cannot be negative.");
            }
            if (demands[city] > maximumCapacity) {
                throw new IllegalArgumentException("Demand for city " + city + " exceeds every vehicle capacity.");
            }
            totalDemand += demands[city];
        }
        if (totalDemand > totalCapacity) {
            throw new IllegalArgumentException("Total customer demand exceeds total vehicle capacity.");
        }

        int[][] distanceMatrix = new int[numberOfCities][numberOfCities];
        for (int row = 0; row < numberOfCities; row++) {
            InputLine matrixRow = nextLine(lines, cursor);
            distanceMatrix[row] = parseValues(matrixRow, numberOfCities, "distance matrix row");
            for (int distance : distanceMatrix[row]) {
                if (distance < 0) {
                    throw invalidLine(matrixRow, "Distances cannot be negative.");
                }
            }
            if (distanceMatrix[row][row] != 0) {
                throw invalidLine(matrixRow, "Distance matrix diagonal entries must be 0.");
            }
        }
        if (cursor[0] < lines.size()) {
            throw invalidLine(lines.get(cursor[0]), "Unexpected data after the distance matrix.");
        }

        return new CVRPProblem(distanceMatrix, numberOfVehicles, vehicleCapacities, demands);
    }

    private static List<InputLine> readDataLines(Path path) throws IOException {
        List<InputLine> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                int commentStart = line.indexOf('#');
                String content = (commentStart >= 0 ? line.substring(0, commentStart) : line).trim();
                if (!content.isEmpty()) {
                    lines.add(new InputLine(lineNumber, content));
                }
            }
        }
        return lines;
    }

    private static int readSingleValue(List<InputLine> lines, int[] cursor, String label) {
        InputLine line = nextLine(lines, cursor);
        String[] tokens = line.content().split("\\s+");
        if (tokens.length != 2 || !tokens[0].equals(label)) {
            throw invalidLine(line, "Expected '" + label + " <integer>'.");
        }
        return parseInteger(tokens[1], line, label);
    }

    private static int[] readValues(List<InputLine> lines, int[] cursor, String label, int expectedCount) {
        InputLine line = nextLine(lines, cursor);
        String[] tokens = line.content().split("\\s+");
        if (tokens.length == 0 || !tokens[0].equals(label)) {
            throw invalidLine(line, "Expected '" + label + "' followed by " + expectedCount + " integers.");
        }
        if (expectedCount < 0 || tokens.length != expectedCount + 1) {
            throw invalidLine(line, label + " must contain exactly " + expectedCount + " integers.");
        }
        int[] values = new int[expectedCount];
        for (int i = 0; i < expectedCount; i++) {
            values[i] = parseInteger(tokens[i + 1], line, label);
        }
        return values;
    }

    private static int[] parseValues(InputLine line, int expectedCount, String description) {
        String[] tokens = line.content().split("\\s+");
        if (tokens.length != expectedCount) {
            throw invalidLine(line, "Each " + description + " must contain exactly " + expectedCount + " integers.");
        }
        int[] values = new int[expectedCount];
        for (int i = 0; i < expectedCount; i++) {
            values[i] = parseInteger(tokens[i], line, description);
        }
        return values;
    }

    private static int parseInteger(String value, InputLine line, String description) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw invalidLine(line, description + " values must be integers.");
        }
    }

    private static InputLine nextLine(List<InputLine> lines, int[] cursor) {
        if (cursor[0] >= lines.size()) {
            throw new IllegalArgumentException("Unexpected end of input file.");
        }
        return lines.get(cursor[0]++);
    }

    private static IllegalArgumentException invalidLine(InputLine line, String message) {
        return new IllegalArgumentException("Input line " + line.number() + ": " + message);
    }
}
