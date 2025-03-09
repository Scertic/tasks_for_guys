package com.epam.rd.autocode.assessment.basics.service;

import com.epam.rd.autocode.assessment.basics.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Mapper {

    public static Client csvToClient(String[] values) {
        Client client = new Client();
        if (values[0] == null || values[0].isEmpty()) {
            client.setId(0);
        } else {
            client.setId(Long.parseLong(values[0]));
        }
        client.setEmail(getStringForEntity(values[1]));
        client.setPassword(getStringForEntity(values[2]));
        client.setName(getStringForEntity(values[3]));
        if (values[4] == null || values[4].isEmpty()) {
            client.setBalance(null);
        } else {
            client.setBalance(new BigDecimal(values[4]));
        }
        client.setDriverLicense(getStringForEntity(values[5]));
        return client;
    }

    public static Employee csvToEmployee(String[] values) {
        Employee employee = new Employee();
        if (values[0] == null || values[0].isEmpty()) {
            employee.setId(0);
        } else {
            employee.setId(Long.parseLong(values[0]));
        }
        employee.setEmail(getStringForEntity(values[1]));
        employee.setPassword(getStringForEntity(values[2]));
        employee.setName(getStringForEntity(values[3]));
        employee.setPhone(getStringForEntity(values[4]));
        if (values[5] == null || values[5].isEmpty()) {
            employee.setDateOfBirth(null);
        } else {
            employee.setDateOfBirth(LocalDate.parse(values[5]));
        }
        return employee;
    }

    public static Vehicle csvToVehicle(String[] values) {
        Vehicle vehicle = new Vehicle();
        if (values[0] == null || values[0].isEmpty()) {
            vehicle.setId(0);
        } else {
            vehicle.setId(Long.parseLong(values[0]));
        }
        vehicle.setMake(getStringForEntity(values[1]));
        vehicle.setModel(getStringForEntity(values[2]));
        vehicle.setCharacteristics(getStringForEntity(values[3]));
        if (values[4] == null || values[4].isEmpty()) {
            vehicle.setYearOfProduction(0);
        } else {
            vehicle.setYearOfProduction(Integer.parseInt(values[4]));
        }
        if (values[5] == null || values[5].isEmpty()) {
            vehicle.setOdometer(0);
        } else {
            vehicle.setOdometer(Long.parseLong(values[5]));
        }
        vehicle.setColor(getStringForEntity(values[6]));
        vehicle.setLicensePlate(getStringForEntity(values[7]));
        if (values[8] == null || values[8].isEmpty()) {
            vehicle.setNumberOfSeats(0);
        } else {
            vehicle.setNumberOfSeats(Integer.parseInt(values[8]));
        }
        if (values[9] == null || values[9].isEmpty()) {
            vehicle.setPrice(null);
        } else {
            vehicle.setPrice(new BigDecimal(values[9]));
        }
        if (values[10] == null || values[10].isEmpty()) {
            vehicle.setRequiredLicense((char) 0);
        } else {
            vehicle.setRequiredLicense(values[10].toCharArray()[0]);
        }
        if (values[10] == null || values[10].isEmpty()) {
            vehicle.setBodyType(null);
        } else {
            vehicle.setBodyType(BodyType.valueOf(values[11]));
        }
        return vehicle;
    }

    public static Order csvToOrder(String[] values) {
        Order order = new Order();
        if (values[0] == null || values[0].isEmpty()) {
            order.setId(0);
        } else {
            try {
                order.setId(Long.parseLong(values[0]));
            } catch (NumberFormatException e) {
                order.setId(0);
            }
        }
        if (values[1] == null || values[1].isEmpty()) {
            order.setClientId(0);
        } else {
            try {
                order.setClientId(Long.parseLong(values[1]));
            } catch (NumberFormatException e) {
                order.setClientId(0);
            }
        }
        if (values[2] == null || values[2].isEmpty()) {
            order.setEmployeeId(0);
        } else {
            try {
                order.setEmployeeId(Long.parseLong(values[2]));
            } catch (NumberFormatException e) {
                order.setEmployeeId(0);
            }
        }
        if (values[3] == null || values[3].isEmpty()) {
            order.setVehicleId(0);
        } else {
            try {
                order.setVehicleId(Long.parseLong(values[3]));
            } catch (NumberFormatException e) {
                order.setVehicleId(0);
            }
        }
        if (values[4] == null || values[4].isEmpty()) {
            order.setStartTime(null);
        } else {
            try {
                order.setStartTime(LocalDateTime.parse(values[4]));
            } catch (DateTimeParseException e) {
                order.setStartTime(null);
            }
        }
        if (values[5] == null || values[5].isEmpty()) {
            order.setEndTime(null);
        } else {
            try {
                order.setEndTime(LocalDateTime.parse(values[5]));
            } catch (DateTimeParseException e) {
                order.setEndTime(null);
            }
        }
        if (values[6] == null || values[6].isEmpty()) {
            order.setPrice(null);
        } else {
            try {
                order.setPrice(new BigDecimal(values[6]));
            } catch (NumberFormatException e) {
                order.setPrice(null);
            }
        }
        return order;
    }

    public static String[] orderToCsv(Order order) {
        String[] result = new String[7];
        result[0] = String.valueOf(order.getId());
        result[1] = String.valueOf(order.getClientId());
        result[2] = String.valueOf(order.getEmployeeId());
        result[3] = String.valueOf(order.getVehicleId());
        result[4] = order.getStartTime() == null ? null : order.getStartTime().toString();
        result[5] = order.getEndTime() == null ? null : order.getEndTime().toString();
        result[6] = order.getPrice() == null ? null : order.getPrice().toString();
        return result;
    }

    public static String[] vehicleToCsv(Vehicle vehicle) {
        String[] result = new String[12];
        result[0] = String.valueOf(vehicle.getId());
        result[1] = getStringToCSV(vehicle.getMake());
        result[2] = getStringToCSV(vehicle.getModel());
        result[3] = getStringToCSV(vehicle.getCharacteristics());
        result[4] = String.valueOf(vehicle.getYearOfProduction());
        result[5] = String.valueOf(vehicle.getOdometer());
        result[6] = getStringToCSV(vehicle.getColor());
        result[7] = getStringToCSV(vehicle.getLicensePlate());
        result[8] = String.valueOf(vehicle.getNumberOfSeats());
        result[9] = vehicle.getPrice() == null ? null : vehicle.getPrice().toString();
        result[10] = String.valueOf(vehicle.getRequiredLicense());
        result[11] = vehicle.getBodyType() == null ? null : vehicle.getBodyType().toString();
        return result;
    }

    public static String[] clientToCsv(Client client) {
        String[] result = new String[6];
        result[0] = String.valueOf(client.getId());
        result[1] = getStringToCSV(client.getEmail());
        result[2] = getStringToCSV(client.getPassword());
        result[3] = getStringToCSV(client.getName());
        result[4] = client.getBalance() == null ? null : client.getBalance().toString();
        result[5] = getStringToCSV(client.getDriverLicense());
        return result;
    }

    public static String[] employeeToCsv(Employee employee) {
        String[] result = new String[6];
        result[0] = String.valueOf(employee.getId());
        result[1] = getStringToCSV(employee.getEmail());
        result[2] = getStringToCSV(employee.getPassword());
        result[3] = getStringToCSV(employee.getName());
        result[4] = getStringToCSV(employee.getPhone());
        result[5] = employee.getDateOfBirth() == null ? null : employee.getDateOfBirth().toString();
        return result;
    }

    public static String getStringForEntity(String value) {
        if (value.isEmpty()) {
            return null;
        }
        if (value.equals("\"\"")) {
            return "";
        }
//        if (value.contains(",")) {
//            return  "\""+value+"\"";
//        }
        return value;
    }

    public static String getStringToCSV(String string) {
//        if (string == null) {
//            return "";
//        }
//        if (string.isEmpty()) {
//            return "\"\"";
//        }
        return string;
    }

}
