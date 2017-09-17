/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manolodominguez.jglobaldatetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRulesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements functionalities to handle DateTime object taking into
 * account the international context and the corresponding time zones.
 *
 * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
 * @version 1.0
 */
public class JGlobalDateTime {

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of JGlobalDateTime using as input the local date and time and the default
     * system zone ID. After that, it uses the default ZoneID and precission to
     * generate the normalized version of that datetime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @throws JGlobalDateTimeException if the default reference zone ID is not
     * a valid Zone ID.
     * @since 1.0
     */
    public JGlobalDateTime() throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
            this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.now(ZoneId.systemDefault()).truncatedTo(this.currentReferencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of JGlobalDateTime using as input the one specified as an argument (in
     * ZonedDateTime format), that includes its own Zone ID. After that, it uses
     * the default reference ZoneID and precission generate the normalized
     * version of that datetime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param originalZonedDateTime The original ZonedDateTime that needs to be
     * normalized.
     * @throws JGlobalDateTimeException if the default reference zone ID is not
     * a valid Zone ID.
     * @since 1.0
     */
    public JGlobalDateTime(ZonedDateTime originalZonedDateTime) throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
            this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = originalZonedDateTime.truncatedTo(this.currentReferencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of JGlobalDateTime using as input the one specified as an argument (in
     * JGlobalDateTime format), that includes its own Zone ID. After that, it
     * uses the default referene ZoneID and precission to generate the
     * normalized version of that datetime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param originalZonedDateTime The original JGlobaleDateTime that needs to
     * be normalized.
     * @throws JGlobalDateTimeException if the default reference zone ID is not
     * a valid Zone ID.
     * @since 1.0
     */
    public JGlobalDateTime(JGlobalDateTime originalZonedDateTime) throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
            this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = originalZonedDateTime.getOriginalDateTime().truncatedTo(this.currentReferencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of JGlobalDateTime using as input the one specified as an argument (in
     * Java String format), that includes its own Zone ID. After that, it uses
     * the default reference ZoneID and precission to generate the normalized
     * version of that datetime. Examples of ZonedDateTime String representation
     * in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param originalZonedDateTime The String representing the original
     * ZonedDateTime that needs to be normalized.
     * @throws JGlobalDateTimeException if the default reference zone ID is not
     * a valid Zone ID and also if the specified string is not a valid
     * ZonedDateTime string representation.
     * @since 1.0
     */
    public JGlobalDateTime(String originalZonedDateTime) throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
            this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.parse(originalZonedDateTime).truncatedTo(this.currentReferencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        } catch (DateTimeParseException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, JGlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of JGlobalDateTime using as input the one specified as an argument (in
     * long format, as millisecond from Epoch). After that, it uses the default
     * reference ZoneID and precission to generate the normalized version of
     * that datetime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param originalDateTimeMillis The original instant, as number of
     * millisecond since Epoch withouth Zone ID, that needs to be normalized.
     * @throws JGlobalDateTimeException if the default reference zone ID is not
     * a valid Zone ID.
     * @since 1.0
     */
    public JGlobalDateTime(long originalDateTimeMillis) throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
            this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(originalDateTimeMillis), this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of JGlobalDateTime using as input the one specified as an argument (in
     * Timestamp format). After that, it uses the default reference ZoneID and
     * precission to generate the normalized version of that datetime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param originalDateTime The original instant, as a Timestamp withouth
     * Zone ID, that needs to be normalized.
     * @throws JGlobalDateTimeException if the default reference zone ID is not
     * a valid Zone ID.
     * @since 1.0
     */
    public JGlobalDateTime(Timestamp originalDateTime) throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
            this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(originalDateTime.toInstant().toEpochMilli()), this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods gets the normalized date and time, in ZonedDateTime format.
     * A normalized datetime is a datetime that has been referenced to the
     * current default reference ZoneID.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return the normalized date and time.
     * @since 1.0
     */
    public ZonedDateTime getNormalizedDateTime() {
        return this.normalizedDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
    }

    /**
     * This methods gets the not-normalized date and time used to create this
     * JGlobalDateTime, in ZonedDateTime format. Let's say, it returns the
     * original datetime that was used to create the JGlobalDateTime instance.
     * In those cases when the value used to create the instance has not
     * information relate to the ZoneID, the default reference ZoneID is used.
     * Otherwise, the original ZoneID is used instead.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return the not-normalized date and time used to create this
     * JGlobalDateTime
     * @since 1.0
     */
    public ZonedDateTime getOriginalDateTime() {
        return this.originalDateTime.withZoneSameInstant(this.originalDateTime.getZone()).truncatedTo(this.currentReferencePrecission);
    }

    /**
     * This methods set the JGlobalDateTime to its initial values, removing any
     * "minus" or "plus", "increase", "decrease" operation that could have
     * happened.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @since 1.0
     */
    public void resetToOriginal() {
        this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
    }

    /**
     * This methods gets the normalized date and time, in Java String format. A
     * normalized datetime is a datetime that has been referenced to the current
     * default reference ZoneID. Examples of ZonedDateTime String representation
     * in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return originalDateTime the normalized date and time, in Java String
     * format.
     * @since 1.0
     */
    public String toNormalizedDateTimeString() {
        return this.normalizedDateTime.toString();
    }

    /**
     * This methods gets the instant represented by this JGlobalDateTime, as a
     * the number of millisecond since Epoch.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return the instant represented by this JGlobalDateTime, as a the number
     * of millisecond since Epoch.
     * @since 1.0
     */
    public long toEpochMilli() {
        return this.normalizedDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods gets a clone of this JGlobalDateTime object with its current
     * status ("minus", "plus", "increase", "decrease" operations included).
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return a clone of this JGlobalDateTime object.
     * @since 1.0
     */
    public JGlobalDateTime getACopyWithCurrentStatus() {
        try {
            return new JGlobalDateTime(this.normalizedDateTime.truncatedTo(this.currentReferencePrecission));
        } catch (JGlobalDateTimeException ex) {
            logger.warn("Cannot get a copy of a valid JGlobalDateTime. So extrange!!");
            return null;
        }
    }

    /**
     * This methods gets a String representation of this ZonedDateTime to be
     * used as input for MySQL DATETIME fields.
     *
     * For instance "2015-02-16 18:01:12.022034"
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return a String representation of this ZonedDateTime to be used as input
     * for MySQL DATETIME fields.
     * @since 1.0
     */
    public String toNormalizedMySQLDateTime() {
        String year = this.normalizedDateTime.getYear() + "";
        String month = this.normalizedDateTime.getMonthValue() + "";
        String day = this.normalizedDateTime.getDayOfMonth() + "";
        String hour = this.normalizedDateTime.getHour() + "";
        String minute = this.normalizedDateTime.getMinute() + "";
        String second = this.normalizedDateTime.getSecond() + "";
        String nanosecond = this.normalizedDateTime.getNano() + "";
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + "." + nanosecond;
    }

    /**
     * This methods increases this JGlobalDateTime in a given number of units
     * (hours, days, minutes...).
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param amount The number of units the JGlobalDateTime will be increased.
     * @param unit The type of units the JGlobalDateTime will be increased
     * (Months, hours, secods...)
     * @since 1.0
     */
    public void increase(long amount, ChronoUnit unit) {
        this.normalizedDateTime = this.normalizedDateTime.plus(amount, unit).truncatedTo(this.currentReferencePrecission);
    }

    /**
     * This methods decreases this JGlobalDateTime in a given number of units
     * (hours, days, minutes...).
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param amount The number of units the JGlobalDateTime will be decreased.
     * @param unit The type of units the JGlobalDateTime will be decreased
     * (Months, hours, secods...)
     * @since 1.0
     */
    public void decrease(long amount, ChronoUnit unit) {
        this.normalizedDateTime = this.normalizedDateTime.minus(amount, unit).truncatedTo(this.currentReferencePrecission);
    }

    /**
     * This methods checks if this JGlobalDateTime is over (if current date is
     * greater).
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @return true, if this JGlobalDateTime is over. Otherwise, false.
     * @since 1.0
     */
    public boolean alreadyHappened() {
        ZonedDateTime current = ZonedDateTime.now(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        return this.normalizedDateTime.toInstant().toEpochMilli() < current.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this JGlobalDateTime is over since a given amount
     * of time.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param amount The period within which we want to check if the date is
     * over.
     * @param unit The period unit (days, months, hours...)
     * @return true, if this JGlobalDateTime is over since the specified amount
     * of time. Otherwise, false.
     * @since 1.0
     */
    public boolean happenedSinceMoreThan(long amount, ChronoUnit unit) {
        ZonedDateTime nowMinusAmountUnit = ZonedDateTime.now(this.currentReferenceZoneID).minus(amount, unit).truncatedTo(this.currentReferencePrecission);
        return this.normalizedDateTime.toInstant().toEpochMilli() < nowMinusAmountUnit.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this JGlobalDateTime happened since less than a
     * given amount of time.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param amount The period within which we want to check if the date is
     * over.
     * @param unit The period unit (days, months, hours...)
     * @return true, if this JGlobalDateTime happened since less than the
     * specified amount of time. Otherwise, false.
     * @since 1.0
     */
    public boolean happenedSinceLessThan(long amount, ChronoUnit unit) {
        ZonedDateTime nowMinusAmountUnit = ZonedDateTime.now(this.currentReferenceZoneID).minus(amount, unit).truncatedTo(this.currentReferencePrecission);
        ZonedDateTime now = ZonedDateTime.now(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        if (this.normalizedDateTime.toInstant().toEpochMilli() < now.toInstant().toEpochMilli()) {
            return this.normalizedDateTime.toInstant().toEpochMilli() < nowMinusAmountUnit.toInstant().toEpochMilli();
        }
        return false;
    }

    /**
     * This methods checks if this JGlobalDateTime is coming in a given amount
     * of time.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param amount The period within which we want to check if the is coming.
     * @param unit The period unit (days, months, hours...)
     * @return true, if this JGlobalDateTime is coming in a specified amount of
     * time. Otherwise, false.
     * @since 1.0
     */
    public boolean isGoingToHappenInLessThan(long amount, ChronoUnit unit) {
        ZonedDateTime nowPlusAmountUnit = ZonedDateTime.now(this.currentReferenceZoneID).plus(amount, unit).truncatedTo(this.currentReferencePrecission);
        ZonedDateTime now = ZonedDateTime.now(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        if (this.normalizedDateTime.toInstant().toEpochMilli() > now.toInstant().toEpochMilli()) {
            return this.normalizedDateTime.toInstant().toEpochMilli() < nowPlusAmountUnit.toInstant().toEpochMilli();
        }
        return false;
    }

    /**
     * This methods checks if this JGlobalDateTime is equal to another instant
     * represented in milliseconds from Epoch.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherDateTimeMillis an instant represented in milliseconds from
     * Epoch.
     * @return true, if the instant of this JGlobalDateTime is the same than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(long anotherDateTimeMillis) {
        return this.normalizedDateTime.toInstant().toEpochMilli() == anotherDateTimeMillis;
    }

    /**
     * This methods checks if this JGlobalDateTime is equal to another instant
     * represented a a Timestamp.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherDateTime an instant represented as a Timestamp.
     * @return true, if the instant of this JGlobalDateTime is the same than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(Timestamp anotherDateTime) {
        return this.normalizedDateTime.toInstant().toEpochMilli() == anotherDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this JGlobalDateTime is equal to another date time
     * represented a ZonedDateTime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a ZonedDateTime.
     * @return true, if the instant of this JGlobalDateTime is the same than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(ZonedDateTime anotherZonedDateTime) {
        ZonedDateTime zdtAux = anotherZonedDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        return this.isEqualTo(zdtAux.toInstant().toEpochMilli());
    }

    /**
     * This methods checks if this JGlobalDateTime is equal to another date time
     * represented as another JGlobalDateTime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a JGlobalDateTime.
     * @return true, if the instant of this JGlobalDateTime is equal than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(JGlobalDateTime anotherZonedDateTime) {
        return this.isEqualTo(anotherZonedDateTime.toEpochMilli());
    }

    /**
     * This methods checks if this JGlobalDateTime is equal to another date time
     * represented as a Java datetime String. Examples of Java datetime String
     * representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a Java datetime
     * String.
     * @return true, if the instant of this JGlobalDateTime is equal than the
     * datetime specified as a parameter. Otherwise, false.
     * @throws JGlobalDateTimeException if the specified datetime string is not
     * valid.
     * @since 1.0
     */
    public boolean isEqualTo(String anotherZonedDateTime) throws JGlobalDateTimeException {
        try {
            ZonedDateTime zdtAux = ZonedDateTime.parse(anotherZonedDateTime).withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
            return this.isEqualTo(zdtAux.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, JGlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods checks if this JGlobalDateTime is before another instant
     * represented in milliseconds from Epoch.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherDateTimeMillis an instant represented in milliseconds from
     * Epoch.
     * @return true, if the instant of this JGlobalDateTime is before than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isBefore(long anotherDateTimeMillis) {
        return this.normalizedDateTime.toInstant().toEpochMilli() < anotherDateTimeMillis;
    }

    /**
     * This methods checks if this JGlobalDateTime is before another instant
     * represented a a Timestamp.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherDateTime an instant represented as a Timestamp.
     * @return true, if the instant of this JGlobalDateTime is before than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isBefore(Timestamp anotherDateTime) {
        return this.normalizedDateTime.toInstant().toEpochMilli() < anotherDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this JGlobalDateTime is before another date time
     * represented a ZonedDateTime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a ZonedDateTime.
     * @return true, if the instant of this JGlobalDateTime is before than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isBefore(ZonedDateTime anotherZonedDateTime) {
        ZonedDateTime zdtAux = anotherZonedDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        return this.isBefore(zdtAux.toInstant().toEpochMilli());
    }

    /**
     * This methods checks if this JGlobalDateTime is before another date time
     * represented as another JGlobalDateTime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a JGlobalDateTime.
     * @return true, if the instant of this JGlobalDateTime is before than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isBefore(JGlobalDateTime anotherZonedDateTime) {
        return this.isBefore(anotherZonedDateTime.toEpochMilli());
    }

    /**
     * This methods checks if this JGlobalDateTime is before another date time
     * represented as a Java datetime String. Examples of Java datetime String
     * representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a Java datetime
     * String.
     * @return true, if the instant of this JGlobalDateTime before than the
     * datetime specified as a parameter. Otherwise, false.
     * @throws JGlobalDateTimeException if the specified datetime string is not
     * valid.
     * @since 1.0
     */
    public boolean isBefore(String anotherZonedDateTime) throws JGlobalDateTimeException {
        try {
            ZonedDateTime zdtAux = ZonedDateTime.parse(anotherZonedDateTime).withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
            return this.isBefore(zdtAux.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, JGlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods checks if this JGlobalDateTime is after another instant
     * represented in milliseconds from Epoch.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherDateTimeMillis an instant represented in milliseconds from
     * Epoch.
     * @return true, if the instant of this JGlobalDateTime is after than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isAfter(long anotherDateTimeMillis) {
        return this.normalizedDateTime.toInstant().toEpochMilli() > anotherDateTimeMillis;
    }

    /**
     * This methods checks if this JGlobalDateTime is after another instant
     * represented a a Timestamp.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherDateTime an instant represented as a Timestamp.
     * @return true, if the instant of this JGlobalDateTime is after than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isAfter(Timestamp anotherDateTime) {
        return this.normalizedDateTime.toInstant().toEpochMilli() > anotherDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this JGlobalDateTime is after another date time
     * represented a ZonedDateTime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a ZonedDateTime.
     * @return true, if the instant of this JGlobalDateTime is after than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isAfter(ZonedDateTime anotherZonedDateTime) {
        ZonedDateTime zdtAux = anotherZonedDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        return this.isAfter(zdtAux.toInstant().toEpochMilli());
    }

    /**
     * This methods checks if this JGlobalDateTime is equal to another date time
     * represented as another JGlobalDateTime.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a JGlobalDateTime.
     * @return true, if the instant of this JGlobalDateTime is after than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isAfter(JGlobalDateTime anotherZonedDateTime) {
        return this.isAfter(anotherZonedDateTime.toEpochMilli());
    }

    /**
     * This methods checks if this JGlobalDateTime is after another date time
     * represented as a Java datetime String. Examples of Java datetime String
     * representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param anotherZonedDateTime a datetime represented as a Java datetime
     * String.
     * @return true, if the instant of this JGlobalDateTime is after than the
     * datetime specified as a parameter. Otherwise, false.
     * @throws JGlobalDateTimeException if the specified datetime string is not
     * valid.
     * @since 1.0
     */
    public boolean isAfter(String anotherZonedDateTime) throws JGlobalDateTimeException {
        try {
            ZonedDateTime zdtAux = ZonedDateTime.parse(anotherZonedDateTime).withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
            return this.isAfter(zdtAux.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, JGlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods changes the default reference Zone ID for this
     * JGlobalDateTime so that the same instant is represented as a date and
     * time for the selected new reference Zone ID.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param zoneId The new default reference Zone ID for this JGlobalDateTime,
     * as a string.
     * @throws JGlobalDateTimeException if the specified default reference Zone
     * is not valid one.
     * @since 1.0
     */
    public void changeZoneID(String zoneId) throws JGlobalDateTimeException {
        try {
            this.currentReferenceZoneID = ZoneId.of(zoneId);
            if (this.originalDateTime != null) {
                this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
            }
        } catch (ZoneRulesException ex) {
            throw new JGlobalDateTimeException(JGlobalDateTimeException.RM_INVALID_ZONE, JGlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods changes the default reference Zone ID for this
     * JGlobalDateTime so that the same instant is represented as a date and
     * time for the selected new reference Zone ID.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param zoneId The new default reference Zone for this JGlobalDateTime.
     * @since 1.0
     */
    public void changeZoneID(ZoneId zoneId) {
        this.currentReferenceZoneID = zoneId;
        if (this.originalDateTime != null) {
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
        }
    }

    /**
     * This methods changes the default precission for this JGlobalDateTime. Its
     * value will be truncated to the specified precission. Therefore, once a
     * lower precission is selected some data are lost and will not recovered
     * until a higher precission is selected and the value of the instance is
     * reset to the original one (in this specific order).
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @param precission The desired precission (seconds, minutes,
     * millisecond...)
     * @since 1.0
     */
    public void changeDefaultPrecission(ChronoUnit precission) {
        this.currentReferencePrecission = JGlobalDateTime.DEFAULT_PRECISSION;
        this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.currentReferenceZoneID).truncatedTo(this.currentReferencePrecission);
    }

    /**
     * This methods changes reset the default Zone for this JGlobalDateTime so
     * that the same instant is represented as a date and time for the default
     * new Zone. The new default Zone will be set to
     * JGlobalDateTime.DEFAULT_ZONE
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @since 1.0
     */
    public void resetToDefaultReferenceZoneID() {
        try {
            this.changeZoneID(JGlobalDateTime.DEFAULT_REFERENCE_ZONEID);
        } catch (JGlobalDateTimeException ex) {
            logger.warn("Cannot reset to default ZoneID. So extrange!!");
        }
    }

    /**
     * This methods changes the default precission for this JGlobalDateTime. Its
     * value will be truncated to the new default precission. The new default
     * precission will be set to JGlobalDateTime.DEFAULT_PRECISSION.
     *
     * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
     * @since 1.0
     */
    public void resetToDefaultPrecission() {
        this.changeDefaultPrecission(JGlobalDateTime.DEFAULT_PRECISSION);
    }

    private ZonedDateTime originalDateTime;
    private ZonedDateTime normalizedDateTime;
    private ZoneId currentReferenceZoneID;
    private ChronoUnit currentReferencePrecission;

    private static final String DEFAULT_REFERENCE_ZONEID = "Europe/Madrid";
    private static final ChronoUnit DEFAULT_PRECISSION = ChronoUnit.NANOS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
