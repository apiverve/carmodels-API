// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     CarModelsData data = Converter.fromJsonString(jsonString);

package com.apiverve.carmodels.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static CarModelsData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(CarModelsData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(CarModelsData.class);
        writer = mapper.writerFor(CarModelsData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// CarModelsData.java

package com.apiverve.carmodels.data;

import com.fasterxml.jackson.annotation.*;

public class CarModelsData {
    private long count;
    private String[] filteredOn;
    private Car[] cars;

    @JsonProperty("count")
    public long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(long value) { this.count = value; }

    @JsonProperty("filteredOn")
    public String[] getFilteredOn() { return filteredOn; }
    @JsonProperty("filteredOn")
    public void setFilteredOn(String[] value) { this.filteredOn = value; }

    @JsonProperty("cars")
    public Car[] getCars() { return cars; }
    @JsonProperty("cars")
    public void setCars(Car[] value) { this.cars = value; }
}

// Car.java

package com.apiverve.carmodels.data;

import com.fasterxml.jackson.annotation.*;

public class Car {
    private String make;
    private String cityMPG;
    private String cityELEC;
    private String combMPG;
    private String combELEC;
    private String cyl;
    private String displace;
    private String drive;
    private String fuel;
    private String highwELEC;
    private String highwMPG;
    private String trans;
    private String size;
    private String year;
    private String trim;
    private String model;

    @JsonProperty("Make")
    public String getMake() { return make; }
    @JsonProperty("Make")
    public void setMake(String value) { this.make = value; }

    @JsonProperty("CityMPG")
    public String getCityMPG() { return cityMPG; }
    @JsonProperty("CityMPG")
    public void setCityMPG(String value) { this.cityMPG = value; }

    @JsonProperty("CityELEC")
    public String getCityELEC() { return cityELEC; }
    @JsonProperty("CityELEC")
    public void setCityELEC(String value) { this.cityELEC = value; }

    @JsonProperty("CombMPG")
    public String getCombMPG() { return combMPG; }
    @JsonProperty("CombMPG")
    public void setCombMPG(String value) { this.combMPG = value; }

    @JsonProperty("CombELEC")
    public String getCombELEC() { return combELEC; }
    @JsonProperty("CombELEC")
    public void setCombELEC(String value) { this.combELEC = value; }

    @JsonProperty("Cyl")
    public String getCyl() { return cyl; }
    @JsonProperty("Cyl")
    public void setCyl(String value) { this.cyl = value; }

    @JsonProperty("Displace")
    public String getDisplace() { return displace; }
    @JsonProperty("Displace")
    public void setDisplace(String value) { this.displace = value; }

    @JsonProperty("Drive")
    public String getDrive() { return drive; }
    @JsonProperty("Drive")
    public void setDrive(String value) { this.drive = value; }

    @JsonProperty("Fuel")
    public String getFuel() { return fuel; }
    @JsonProperty("Fuel")
    public void setFuel(String value) { this.fuel = value; }

    @JsonProperty("HighwELEC")
    public String getHighwELEC() { return highwELEC; }
    @JsonProperty("HighwELEC")
    public void setHighwELEC(String value) { this.highwELEC = value; }

    @JsonProperty("HighwMPG")
    public String getHighwMPG() { return highwMPG; }
    @JsonProperty("HighwMPG")
    public void setHighwMPG(String value) { this.highwMPG = value; }

    @JsonProperty("Trans")
    public String getTrans() { return trans; }
    @JsonProperty("Trans")
    public void setTrans(String value) { this.trans = value; }

    @JsonProperty("Size")
    public String getSize() { return size; }
    @JsonProperty("Size")
    public void setSize(String value) { this.size = value; }

    @JsonProperty("Year")
    public String getYear() { return year; }
    @JsonProperty("Year")
    public void setYear(String value) { this.year = value; }

    @JsonProperty("Trim")
    public String getTrim() { return trim; }
    @JsonProperty("Trim")
    public void setTrim(String value) { this.trim = value; }

    @JsonProperty("Model")
    public String getModel() { return model; }
    @JsonProperty("Model")
    public void setModel(String value) { this.model = value; }
}