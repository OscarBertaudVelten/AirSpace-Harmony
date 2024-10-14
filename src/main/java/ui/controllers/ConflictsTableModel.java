package ui.controllers;

import flights.Flight;
import flights.FlightsList;
import tools.TimeTools;

import javax.swing.table.AbstractTableModel;

/**
 * Table model for displaying conflicts between flights.
 */
class ConflictsTableModel extends AbstractTableModel {
    private final FlightsList flights;
    private final String[] columnNames = {"Flight ID", "Departure Airport"};

    /**
     * Constructor for ConflictsTableModel.
     * @param flights A list of flights to display conflicts for.
     */
    public ConflictsTableModel(FlightsList flights) {
        this.flights = flights;
    }

    /**
     * Returns the number of rows in the table, which corresponds to the number of flights.
     * @return The number of flights.
     */
    @Override
    public int getRowCount() {
        return flights.size();
    }

    /**
     * Returns the number of columns in the table.
     * @return The number of columns.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the name of the specified column.
     * @param columnIndex The index of the column.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * Returns the value at the specified cell in the table.
     * @param rowIndex The row index of the cell.
     * @param columnIndex The column index of the cell.
     * @return The value at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Flight flight = flights.get(rowIndex);
        TimeTools timeTools1 = new TimeTools();
        TimeTools timeTools2 = new TimeTools();
        timeTools1.M_to_HM(flight.gethDeparture());
        timeTools2.M_to_HM(flight.gethDeparture() + flight.getDuration());

        return switch (columnIndex) {
            case 0 -> flight.getFlightID();
            case 1 -> flight.getDepAirportCode();
            default -> null;
        };
    }
}
