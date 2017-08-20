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
package com.manolodominguez.globaldatetime;

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
 * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
 * @version 1.0
 */
public class GlobalDateTime {

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the local date and time and the default
     * system zone ID. After that, it uses the default ZoneID and precission to
     * normalize the datetime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @throws GlobalDateTimeException if the default zone ID is not a valid
     * Zone ID.
     * @since 1.0
     */
    public GlobalDateTime() throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(GlobalDateTime.DEFAULT_ZONE);
            this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.now(ZoneId.systemDefault()).truncatedTo(this.referencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the one specified as an argument (in
     * ZonedDateTime format ), that includes its own Zone ID. After that, it
     * uses the default ZoneID and precission to normalize the datetime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param originalZonedDateTime The original ZonedDateTime that needs to be
     * normalized.
     * @throws GlobalDateTimeException if the default zone ID is not a valid
     * Zone ID.
     * @since 1.0
     */
    public GlobalDateTime(ZonedDateTime originalZonedDateTime) throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(GlobalDateTime.DEFAULT_ZONE);
            this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = originalZonedDateTime.truncatedTo(this.referencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the one specified as an argument (in
     * GlobalDateTime format), that includes its own Zone ID. After that, it
     * uses the default ZoneID and precission to normalize the datetime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param originalZonedDateTime The original GlobaleDateTime that needs to
     * be normalized.
     * @throws GlobalDateTimeException if the default zone ID is not a valid
     * Zone ID.
     * @since 1.0
     */
    public GlobalDateTime(GlobalDateTime originalZonedDateTime) throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(GlobalDateTime.DEFAULT_ZONE);
            this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = originalZonedDateTime.getOriginalDateTime().truncatedTo(this.referencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the one specified as an argument (in
     * Java String format), that includes its own Zone ID. After that, it uses
     * the default ZoneID and precission to normalize the datetime. Examples of
     * ZonedDateTime String representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param originalZonedDateTime The String representing the original
     * ZonedDateTime that needs to be normalized.
     * @throws GlobalDateTimeException if the default zone ID is not a valid
     * Zone ID and also if the specified string is not a valid ZonedDateTime
     * string representation.
     * @since 1.0
     */
    public GlobalDateTime(String originalZonedDateTime) throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(GlobalDateTime.DEFAULT_ZONE);
            this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.parse(originalZonedDateTime).truncatedTo(this.referencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        } catch (DateTimeParseException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, GlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the one specified as an argument (in
     * long format, as millisecond from Epoch) and the default Zone ID. After
     * that, it uses the default ZoneID and precission to normalize the
     * datetime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param originalDateTimeMillis The original instant, as number of
     * millisecond since Epoch withouth Zone ID, that needs to be normalized.
     * @throws GlobalDateTimeException if the default zone ID is not a valid
     * Zone ID.
     * @since 1.0
     */
    public GlobalDateTime(long originalDateTimeMillis) throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(GlobalDateTime.DEFAULT_ZONE);
            this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(originalDateTimeMillis), this.referenceZoneID).truncatedTo(this.referencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the one specified as an argument (in
     * Timestamp format) and the default Zone ID. After that, it uses the
     * default ZoneID and precission to normalize the datetime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param originalDateTime The original instant, as a Timestamp withouth
     * Zone ID, that needs to be normalized.
     * @throws GlobalDateTimeException if the default zone ID is not a valid
     * Zone ID.
     * @since 1.0
     */
    public GlobalDateTime(Timestamp originalDateTime) throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(GlobalDateTime.DEFAULT_ZONE);
            this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
            this.originalDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(originalDateTime.toInstant().toEpochMilli()), this.referenceZoneID).truncatedTo(this.referencePrecission);
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods gets the normalized date and time, in ZonedDateTime format.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @return the normalized date and time.
     * @since 1.0
     */
    public ZonedDateTime getNormalizedDateTime() {
        return this.normalizedDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
    }

    /**
     * This methods gets the not-normalized date and time used to create this
     * GlobalDateTime, in ZonedDateTime format.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @return the not-normalized date and time used to create this
     * GlobalDateTime
     * @since 1.0
     */
    public ZonedDateTime getOriginalDateTime() {
        return this.originalDateTime.withZoneSameInstant(this.originalDateTime.getZone()).truncatedTo(this.referencePrecission);
    }

    /**
     * This methods set the GlobalDateTime to its initial values, removing any
     * "minus" or "plus" operation that could be happened.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @since 1.0
     */
    public void resetToOriginal() {
        this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
    }

    /**
     * This methods gets the normalized date and time, in Java String format.
     * Examples of ZonedDateTime String representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @return originalDateTime the normalized date and time, in Java String
     * format.
     * @since 1.0
     */
    public String toNormalizedDateTimeString() {
        return this.normalizedDateTime.toString();
    }

    /**
     * This methods gets the instant represented by this GlobalDateTime, as a
     * the number of millisecond since Epoch.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @return the instant represented by this GlobalDateTime, as a the number
     * of millisecond since Epoch.
     * @since 1.0
     */
    public long toEpochMilli() {
        return this.normalizedDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods gets a clone of this GlobalDateTime object.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @return a clone of this GlobalDateTime object.
     * @since 1.0
     */
    public GlobalDateTime getACopy() {
        try {
            return new GlobalDateTime(this.normalizedDateTime.truncatedTo(this.referencePrecission));
        } catch (GlobalDateTimeException ex) {
            logger.warn("Cannot get a copy of a valid GlobalDateTime. So extrange!!");
            return null;
        }
    }

    /**
     * This methods gets a String representation of this ZonedDateTime to be
     * used as input for MySQL DATETIME fields.
     *
     * For instance "2015-02-16 18:01:12.022034"
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
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
        String microsecond = ((int) (this.normalizedDateTime.getNano() / 1000)) + "";
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + "." + microsecond;
    }

    /**
     * This methods increases this GlobalDateTime in a given number of units
     * (hours, days, minutes...).
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param amount The number of units the GlobalDateTime will be increased.
     * @param unit The type of units the GlobalDateTime will be increased
     * (Months, hours, secods...)
     * @since 1.0
     */
    public void increase(long amount, ChronoUnit unit) {
        this.normalizedDateTime = this.normalizedDateTime.plus(amount, unit).truncatedTo(this.referencePrecission);
    }

    /**
     * This methods decreases this GlobalDateTime in a given number of units
     * (hours, days, minutes...).
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param amount The number of units the GlobalDateTime will be decreased.
     * @param unit The type of units the GlobalDateTime will be decreased
     * (Months, hours, secods...)
     * @since 1.0
     */
    public void decrease(long amount, ChronoUnit unit) {
        this.normalizedDateTime = this.normalizedDateTime.minus(amount, unit).truncatedTo(this.referencePrecission);
    }

    /**
     * This methods checks if this GlobalDateTime is over (if current date is
     * greater).
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @return true, if this GlobalDateTime is over. Otherwise, false.
     * @since 1.0
     */
    public boolean isOver() {
        ZonedDateTime current = ZonedDateTime.now(this.referenceZoneID).truncatedTo(this.referencePrecission);
        return this.normalizedDateTime.toInstant().toEpochMilli() < current.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this GlobalDateTime is over since a given amount
     * of time.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param amount The period within which we want to check if the date is
     * over.
     * @param unit The period unit (days, months, hours...)
     * @return true, if this GlobalDateTime is over since the specified amount
     * of time. Otherwise, false.
     * @since 1.0
     */
    public boolean isOverSinceMoreThan(long amount, ChronoUnit unit) {
        ZonedDateTime current = ZonedDateTime.now(this.referenceZoneID).minus(amount, unit).truncatedTo(this.referencePrecission);
        return this.normalizedDateTime.toInstant().toEpochMilli() < current.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this GlobalDateTime is coming in a given amount of
     * time.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param amount The period within which we want to check if the is coming.
     * @param unit The period unit (days, months, hours...)
     * @return true, if this GlobalDateTime is coming in a specified amount of
     * time. Otherwise, false.
     * @since 1.0
     */
    public boolean isComingInLessThan(long amount, ChronoUnit unit) {
        ZonedDateTime current = ZonedDateTime.now(this.referenceZoneID).plus(amount, unit).truncatedTo(this.referencePrecission);
        return this.normalizedDateTime.toInstant().toEpochMilli() < current.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this GlobalDateTime is equal to another instant
     * represented in milliseconds from Epoch.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherDateTimeMillis an instant represented in milliseconds from
     * Epoch.
     * @return true, if the instant of this GlobalDateTime is the same than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(long anotherDateTimeMillis) {
        return this.normalizedDateTime.toInstant().toEpochMilli() == anotherDateTimeMillis;
    }

    /**
     * This methods checks if this GlobalDateTime is equal to another instant
     * represented a a Timestamp.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherDateTime an instant represented as a Timestamp.
     * @return true, if the instant of this GlobalDateTime is the same than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(Timestamp anotherDateTime) {
        return this.normalizedDateTime.toInstant().toEpochMilli() == anotherDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this GlobalDateTime is equal to another date time
     * represented a ZonedDateTime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a ZonedDateTime.
     * @return true, if the instant of this GlobalDateTime is the same than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(ZonedDateTime anotherZonedDateTime) {
        ZonedDateTime zdtAux = anotherZonedDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        return this.isEqualTo(zdtAux.toInstant().toEpochMilli());
    }

    /**
     * This methods checks if this GlobalDateTime is equal to another date time
     * represented as another GlobalDateTime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a GlobalDateTime.
     * @return true, if the instant of this GlobalDateTime is equal than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isEqualTo(GlobalDateTime anotherZonedDateTime) {
        return this.isEqualTo(anotherZonedDateTime.toEpochMilli());
    }

    /**
     * This methods checks if this GlobalDateTime is equal to another date time
     * represented as a Java datetime String. Examples of Java datetime String
     * representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a Java datetime
     * String.
     * @return true, if the instant of this GlobalDateTime is equal than the
     * datetime specified as a parameter. Otherwise, false.
     * @throws GlobalDateTimeException if the specified datetime string is not
     * valid.
     * @since 1.0
     */
    public boolean isEqualTo(String anotherZonedDateTime) throws GlobalDateTimeException {
        try {
            ZonedDateTime zdtAux = ZonedDateTime.parse(anotherZonedDateTime).withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
            return this.isEqualTo(zdtAux.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, GlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods checks if this GlobalDateTime is before another instant
     * represented in milliseconds from Epoch.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherDateTimeMillis an instant represented in milliseconds from
     * Epoch.
     * @return true, if the instant of this GlobalDateTime is before than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isBefore(long anotherDateTimeMillis) {
        return this.normalizedDateTime.toInstant().toEpochMilli() < anotherDateTimeMillis;
    }

    /**
     * This methods checks if this GlobalDateTime is before another instant
     * represented a a Timestamp.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherDateTime an instant represented as a Timestamp.
     * @return true, if the instant of this GlobalDateTime is before than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isBefore(Timestamp anotherDateTime) {
        return this.normalizedDateTime.toInstant().toEpochMilli() < anotherDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this GlobalDateTime is before another date time
     * represented a ZonedDateTime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a ZonedDateTime.
     * @return true, if the instant of this GlobalDateTime is before than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isBefore(ZonedDateTime anotherZonedDateTime) {
        ZonedDateTime zdtAux = anotherZonedDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        return this.isBefore(zdtAux.toInstant().toEpochMilli());
    }

    /**
     * This methods checks if this GlobalDateTime is before another date time
     * represented as another GlobalDateTime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a GlobalDateTime.
     * @return true, if the instant of this GlobalDateTime is before than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isBefore(GlobalDateTime anotherZonedDateTime) {
        return this.isBefore(anotherZonedDateTime.toEpochMilli());
    }

    /**
     * This methods checks if this GlobalDateTime is before another date time
     * represented as a Java datetime String. Examples of Java datetime String
     * representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a Java datetime
     * String.
     * @return true, if the instant of this GlobalDateTime before than the
     * datetime specified as a parameter. Otherwise, false.
     * @throws GlobalDateTimeException if the specified datetime string is not
     * valid.
     * @since 1.0
     */
    public boolean isBefore(String anotherZonedDateTime) throws GlobalDateTimeException {
        try {
            ZonedDateTime zdtAux = ZonedDateTime.parse(anotherZonedDateTime).withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
            return this.isBefore(zdtAux.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, GlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods checks if this GlobalDateTime is after another instant
     * represented in milliseconds from Epoch.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherDateTimeMillis an instant represented in milliseconds from
     * Epoch.
     * @return true, if the instant of this GlobalDateTime is after than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isAfter(long anotherDateTimeMillis) {
        return this.normalizedDateTime.toInstant().toEpochMilli() > anotherDateTimeMillis;
    }

    /**
     * This methods checks if this GlobalDateTime is after another instant
     * represented a a Timestamp.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherDateTime an instant represented as a Timestamp.
     * @return true, if the instant of this GlobalDateTime is after than the
     * instant specified as a parameter. Oterwise, false.
     * @since 1.0
     */
    public boolean isAfter(Timestamp anotherDateTime) {
        return this.normalizedDateTime.toInstant().toEpochMilli() > anotherDateTime.toInstant().toEpochMilli();
    }

    /**
     * This methods checks if this GlobalDateTime is after another date time
     * represented a ZonedDateTime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a ZonedDateTime.
     * @return true, if the instant of this GlobalDateTime is after than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isAfter(ZonedDateTime anotherZonedDateTime) {
        ZonedDateTime zdtAux = anotherZonedDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        return this.isAfter(zdtAux.toInstant().toEpochMilli());
    }

    /**
     * This methods checks if this GlobalDateTime is equal to another date time
     * represented as another GlobalDateTime.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a GlobalDateTime.
     * @return true, if the instant of this GlobalDateTime is after than the
     * datetime specified as a parameter. Otherwise, false.
     * @since 1.0
     */
    public boolean isAfter(GlobalDateTime anotherZonedDateTime) {
        return this.isAfter(anotherZonedDateTime.toEpochMilli());
    }

    /**
     * This methods checks if this GlobalDateTime is after another date time
     * represented as a Java datetime String. Examples of Java datetime String
     * representation in Java are:
     *
     * 2015-04-06T14:20:18.811-05:00[America/Chicago]
     * 2017-04-06T21:20:18.811+02:00[Europe/Madrid]
     * 2015-04-06T21:20:18.811+02:00[Europe/Madrid]
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param anotherZonedDateTime a datetime represented as a Java datetime
     * String.
     * @return true, if the instant of this GlobalDateTime is after than the
     * datetime specified as a parameter. Otherwise, false.
     * @throws GlobalDateTimeException if the specified datetime string is not
     * valid.
     * @since 1.0
     */
    public boolean isAfter(String anotherZonedDateTime) throws GlobalDateTimeException {
        try {
            ZonedDateTime zdtAux = ZonedDateTime.parse(anotherZonedDateTime).withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
            return this.isAfter(zdtAux.toInstant().toEpochMilli());
        } catch (DateTimeParseException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONEDDATETIME_STRING, GlobalDateTimeException.RC_INVALID_ZONEDDATETIME_STRING);
        }
    }

    /**
     * This methods changes the default Zone for this GlobalDateTime so that the
     * same instant is represented as a date and time for the selected new Zone.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param zoneId The new default Zone for this GlobalDateTime, as a string.
     * @throws GlobalDateTimeException if the specified Zone is not valid one.
     * @since 1.0
     */
    public void changeDefaultZone(String zoneId) throws GlobalDateTimeException {
        try {
            this.referenceZoneID = ZoneId.of(zoneId);
            if (this.originalDateTime != null) {
                this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
            }
        } catch (ZoneRulesException ex) {
            throw new GlobalDateTimeException(GlobalDateTimeException.RM_INVALID_ZONE, GlobalDateTimeException.RC_INVALID_ZONE);
        }
    }

    /**
     * This methods changes the default Zone for this GlobalDateTime so that the
     * same instant is represented as a date and time for the selected new Zone.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param zoneId The new default Zone for this GlobalDateTime.
     * @since 1.0
     */
    public void changeDefaultZone(ZoneId zoneId) {
        this.referenceZoneID = zoneId;
        if (this.originalDateTime != null) {
            this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
        }
    }

    /**
     * This methods changes the default precission for this GlobalDateTime. Its
     * value will be truncated to the specified precission.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @param precission The desired precission (seconds, minutes,
     * millisecond...)
     * @since 1.0
     */
    public void changeDefaultPrecission(ChronoUnit precission) {
        this.referencePrecission = GlobalDateTime.DEFAULT_PRECISSION;
        this.originalDateTime = this.originalDateTime.truncatedTo(this.referencePrecission);
        this.normalizedDateTime = this.originalDateTime.withZoneSameInstant(this.referenceZoneID).truncatedTo(this.referencePrecission);
    }

    /**
     * This methods changes reset the default Zone for this GlobalDateTime so
     * that the same instant is represented as a date and time for the default
     * new Zone. The new default Zone will be set to GlobalDateTime.DEFAULT_ZONE
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @since 1.0
     */
    public void resetToDefaultZone() {
        try {
            this.changeDefaultZone(GlobalDateTime.DEFAULT_ZONE);
        } catch (GlobalDateTimeException ex) {
            logger.warn("Cannot reset to default ZoneID. So extrange!!");
        }
    }

    /**
     * This methods changes the default precission for this GlobalDateTime. Its
     * value will be truncated to the new default precission. The new default
     * precission will be set to GlobalDateTime.DEFAULT_PRECISSION.
     *
     * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.com
     * @since 1.0
     */
    public void resetToDefaultPrecission() {
        this.changeDefaultPrecission(GlobalDateTime.DEFAULT_PRECISSION);
    }

    private ZonedDateTime originalDateTime;
    private ZonedDateTime normalizedDateTime;
    private ZoneId referenceZoneID;
    private ChronoUnit referencePrecission;

    private static final String DEFAULT_ZONE = "Europe/Madrid";
    private static final ChronoUnit DEFAULT_PRECISSION = ChronoUnit.MILLIS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
