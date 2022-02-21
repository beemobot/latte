package dev.ayu.latte.ratelimit;

public enum RatelimitType {

    GLOBAL,
    IDENTIFY;

    private static final String KAFKA_GLOBAL_REQUESTS_TOPIC = "discord-global-ratelimit-requests";
    private static final String KAFKA_GLOBAL_GRANTS_TOPIC = "discord-global-ratelimit-grants";

    private static final String KAFKA_IDENTIFY_REQUESTS_TOPIC = "discord-identify-ratelimit-requests";
    private static final String KAFKA_IDENTIFY_GRANTS_TOPIC = "discord-identify-ratelimit-grants";

    public String getRequestsTopic() {
        switch (this) {
            case GLOBAL -> {
                return KAFKA_GLOBAL_REQUESTS_TOPIC;
            }
            case IDENTIFY -> {
                return KAFKA_IDENTIFY_REQUESTS_TOPIC;
            }
        }
        throw new AssertionError("Requests topic not implemented for "
                + this + " RatelimitType");
    }

    public String getGrantsTopic() {
        switch (this) {
            case GLOBAL -> {
                return KAFKA_GLOBAL_GRANTS_TOPIC;
            }
            case IDENTIFY -> {
                return KAFKA_IDENTIFY_GRANTS_TOPIC;
            }
        }
        throw new AssertionError("Grants topic not implemented for "
                + this + " RatelimitType");
    }

}
