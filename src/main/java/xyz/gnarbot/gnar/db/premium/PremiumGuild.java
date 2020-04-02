package xyz.gnarbot.gnar.db.premium;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import xyz.gnarbot.gnar.db.ManagedObject;

import java.beans.ConstructorProperties;
import java.time.Duration;
import java.time.Instant;

public class PremiumGuild extends ManagedObject {
    // The ID of the user that enabled premium for the guild.
    @JsonSerialize
    @JsonDeserialize
    private String redeemer;

    // The epoch timestamp of when premium status expires.
    @JsonSerialize
    @JsonDeserialize
    private long added;

    @ConstructorProperties("id")
    public PremiumGuild(String id) {
        super(id, "premiumguilds");
    }

    @JsonIgnore
    public PremiumGuild setRedeemer(String redeemer) {
        this.redeemer = redeemer;
        return this;
    }

    @JsonIgnore
    public PremiumGuild setAdded(long added) {
        this.added = added;
        return this;
    }

    @JsonIgnore
    public String getRedeemerId() {
        return redeemer;
    }

    @JsonIgnore
    public long getDaysSinceAdded() {
        Instant current = Instant.now();
        Instant then = Instant.ofEpochMilli(added);

        return Duration.between(then, current).toDays();
    }
}