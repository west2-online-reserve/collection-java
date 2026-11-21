package core.lib;

import core.data.Event;
import core.data.JsonData;

import java.util.Locale;

public class Collector {
    public static Event findEvent(JsonData data, String gender, String discipline, String eventType) {
        for (Event e : data.getEvents()) {
            if (gender.equals(e.getGender().toLowerCase(Locale.ROOT)) &&
                discipline.equals(e.getDiscipline().toLowerCase(Locale.ROOT)) &&
                eventType.equals(e.getEventType().toLowerCase(Locale.ROOT))
            ) {
                return e;
            }
        }

        return null;
    }
}
