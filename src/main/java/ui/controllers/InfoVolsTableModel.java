package ui.controllers;

import flights.Flight;
import flights.FlightsList;
import tools.TimeTools;

import javax.swing.table.AbstractTableModel;

/**
 * Table model for displaying flight information in a JTable.
 */
class InfoVolsTableModel extends AbstractTableModel {
    private final FlightsList flights;
    private final String[] columnNames = {
            "Numéro de vol", "Code", "Aeroport de départ",
            "Code", "Aeroport d'arrivée",
            "Heure de départ", "Heure d'arrivée", "Durée"
    };

    /**
     * Constructs a table model using the given list of flights.
     *
     * @param flights The list of flights to display in the table.
     */
    public InfoVolsTableModel(FlightsList flights) {
        this.flights = flights;
    }

    /**
     * Returns the number of rows in the table model, which is equal to the number of flights.
     *
     * @return The number of flights in the list.
     */
    @Override
    public int getRowCount() {
        return flights.size();
    }

    /**
     * Returns the number of columns in the table model.
     *
     * @return The number of columns.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the name of the column at the specified index.
     *
     * @param columnIndex The index of the column.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * Returns the value at the specified row and column index.
     *
     * @param rowIndex    The row index of the cell.
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
            case 2 -> flight.getDepAirport().getAirportLocation();
            case 3 -> flight.getArrAirportCode();
            case 4 -> flight.getArrAirport().getAirportLocation();
            case 5 -> timeTools1.toString();
            case 6 -> timeTools2.toString();
            case 7 -> flight.getDuration();
            default -> null;
        };
    }
}
