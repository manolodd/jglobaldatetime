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
package com.manolodominguez.globaldatetime.example;

import com.manolodominguez.globaldatetime.GlobalDateTime;
import com.manolodominguez.globaldatetime.GlobalDateTimeException;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements an example of use of GlobalDateTime.
 *
 * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
 * @version 1.0
 */
public class GlobalDateTimeExample {

    /**
     * This methods is the constructor of the class. It creates a new instance
     * of GlobalDateTime using as input the local date and time and the default
     * system zone ID. After that, it uses the default ZoneID and precission to
     * normalize the datetime.
     *
     * @param args parameter passed out as arguments to the main class. They are
     * not used.
     * @since 1.0
     */
    public static void main(String[] args) {
        
        GlobalDateTime gdt1;
        GlobalDateTime gdt2;
        GlobalDateTime gdt3;
        GlobalDateTime gdt4;
        Timestamp ts;
                
        try {
            // Imagine different situations:
            //
            // gdt1 will represent the current datetime of the system using the 
            // system default ZoneID.
            gdt1= new GlobalDateTime();
            // gdt2 will be a datetime that has arrived to the system via 
            // Internet in a JSON message, from Chicago. It comes in 
            // ZonedDateTime String format.
            gdt2 = new GlobalDateTime ("2017-08-20T14:20:18.811-05:00[America/Chicago]");
            // gdt3 will be a datetime that has been loaded from a local log 
            // file. It is in millis from Epoch format.
            gdt3 = new GlobalDateTime (1428348018845L);
            // gdt4 is a datetime that has been retrieved via JDBC from a 
            // database in Timestamp format.
            ts = new Timestamp(1428348018845L);
            gdt4 = new GlobalDateTime (ts);
            
            // Let's go
            
            // Printing all datetimes. They are normalized to the same reference
            // ZoneID (Europe/Madrid by default).
            System.out.println("gdt1: " + gdt1.toNormalizedDateTimeString());
            System.out.println("gdt2: " + gdt2.toNormalizedDateTimeString());
            System.out.println("gdt3: " + gdt3.toNormalizedDateTimeString());
            System.out.println("gdt4: " + gdt4.toNormalizedDateTimeString());
            System.out.println();
            
            // Now printing all datetimes. They are normalized to the same 
            // reference ZoneID (Europe/Madrid by default).
            gdt1.changeDefaultZone("Europe/Lisbon");
            gdt2.changeDefaultZone("Europe/Lisbon");
            gdt3.changeDefaultZone("Europe/Lisbon");
            gdt4.changeDefaultZone("Europe/Lisbon");
            System.out.println("The same but in Europe/Lisbon are:");
            System.out.println("gdt1: " + gdt1.toNormalizedDateTimeString());
            System.out.println("gdt2: " + gdt2.toNormalizedDateTimeString());
            System.out.println("gdt3: " + gdt3.toNormalizedDateTimeString());
            System.out.println("gdt4: " + gdt4.toNormalizedDateTimeString());
            System.out.println();

            // Let's compare.
            System.out.println("Comparing gdt1 and gdt2 (both normalized)");
            if (gdt1.isBefore(gdt2)) {
                System.out.println(gdt1.toNormalizedDateTimeString()+" happened before "+ gdt2.toNormalizedDateTimeString());
            } if (gdt1.isAfter(gdt2)) {
                System.out.println(gdt1.toNormalizedDateTimeString()+" happened after "+ gdt2.toNormalizedDateTimeString());
            } else {
                System.out.println(gdt1.toNormalizedDateTimeString()+" happened at the same time than "+ gdt2.toNormalizedDateTimeString());
            }
            System.out.println();


            // Let's compare again.
            System.out.println("Comparing again gdt1 and gdt2 (using different Zone ID)");
            gdt1.changeDefaultZone("Pacific/Tahiti");
            if (gdt1.isBefore(gdt2)) {
                System.out.println(gdt1.toNormalizedDateTimeString()+" happened before "+ gdt2.toNormalizedDateTimeString());
            } if (gdt1.isAfter(gdt2)) {
                System.out.println(gdt1.toNormalizedDateTimeString()+" happened after "+ gdt2.toNormalizedDateTimeString());
            } else {
                System.out.println(gdt1.toNormalizedDateTimeString()+" happened at the same time than "+ gdt2.toNormalizedDateTimeString());
            }
            System.out.println();

            // And again.
            System.out.println("Comparing gdt3 and gdt4 (both use different Zone ID)");
            gdt3.changeDefaultZone("Australia/Sydney");
            gdt4.changeDefaultZone("Antarctica/South_Pole");
            if (gdt3.isBefore(gdt4)) {
                System.out.println(gdt3.toNormalizedDateTimeString()+" happened before "+ gdt4.toNormalizedDateTimeString());
            } if (gdt3.isAfter(gdt4)) {
                System.out.println(gdt3.toNormalizedDateTimeString()+" happened after "+ gdt4.toNormalizedDateTimeString());
            } else {
                System.out.println(gdt3.toNormalizedDateTimeString()+" happened at the same time than "+ gdt4.toNormalizedDateTimeString());
            }
            System.out.println();

            // Now we generate Strings in MySQL DATETIME format.
            gdt1.resetToDefaultZone();
            gdt2.resetToDefaultZone();
            gdt3.resetToDefaultZone();
            gdt4.resetToDefaultZone();
            System.out.println("Generating MySQL DATETIME format to be used in an INSERT statement");
            System.out.println("gdt1: "+gdt1.toNormalizedMySQLDateTime());
            System.out.println("gdt2: "+gdt2.toNormalizedMySQLDateTime());
            System.out.println("gdt3: "+gdt3.toNormalizedMySQLDateTime());
            System.out.println("gdt4: "+gdt4.toNormalizedMySQLDateTime());
            System.out.println();

            // Now we modify our GlobalDateTimes a bit.
            gdt1.increase(3, ChronoUnit.DAYS);
            gdt2.increase(4, ChronoUnit.MINUTES);
            gdt3.decrease(1, ChronoUnit.YEARS);
            gdt4.decrease(1, ChronoUnit.MONTHS);
            System.out.println("Let's change our GlobalDateTimes a bit");
            System.out.println("gdt1 + 3 days: "+gdt1.toNormalizedMySQLDateTime());
            System.out.println("gdt2 + 4 minutes: "+gdt2.toNormalizedMySQLDateTime());
            System.out.println("gdt3 - 1 year: "+gdt3.toNormalizedMySQLDateTime());
            System.out.println("gdt4 - 1 month: "+gdt4.toNormalizedMySQLDateTime());
            System.out.println();

            // Fuzzy comparation.
            if (gdt3.isOverSinceMoreThan(3, ChronoUnit.YEARS)) {
                System.out.println("gdt3 "+gdt3.toNormalizedDateTimeString()+" happened since more than 4 years");
            } else {
                System.out.println("gdt3 "+gdt3.toNormalizedDateTimeString()+" happened since less than 4 years");
            }
            gdt1.resetToOriginal();
            gdt1.increase(3, ChronoUnit.DAYS);
            if (gdt1.isComingInLessThan(7, ChronoUnit.DAYS)) {
                System.out.println("gdt1 "+gdt1.toNormalizedDateTimeString()+" will happen in les than 7 days");
            } else {
                System.out.println("gdt1 "+gdt1.toNormalizedDateTimeString()+" will happen in more than 7 days");
            }

            System.out.println();

        } catch (GlobalDateTimeException ex) {
            Logger.getLogger(GlobalDateTimeExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}
