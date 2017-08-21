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

/**
 * This class implements an exception to be used when an abnormal situation
 * happens during the use of GlobalDateTime class.
 *
 * @author Manuel Domínguez-Dorado - ingeniero@manolodominguez.com
 * @version 1.0
 */
public class GlobalDateTimeException extends Exception {

    /**
     * This is the constructor of the class. It creates a new instance of
     * GlobalDateTimeException.
     *
     * @param message The message associated to the exception.
     * @param reasonCode The reason code associated to the exception.
     * @author Manuel Domínguez-Dorado ingeniero@manolodominguez.com
     * @since 1.0
     */
    public GlobalDateTimeException(String message, int reasonCode) {
        super(message);
        this.reasonCode = reasonCode;
    }

    /**
     * This method gets the reason code associated to the exception.
     *
     * @return The reason code associated to the extension. Should be one of the
     * constants defined in GlobalDateTimeException class.
     * @author Manuel Domínguez-Dorado ingeniero@manolodominguez.com
     * @since 1.0
     */
    public int getReasonCode() {
        return this.reasonCode;
    }

    private final int reasonCode;

    /**
     * Supported reason codes for the exception.
     */
    public static final int RC_OK = 0;
    public static final int RC_UNEXPECTED_ERROR = 1;
    public static final int RC_INVALID_ZONE = 2;
    public static final int RC_INVALID_ZONEDDATETIME_STRING = 3;

    /**
     * Supported reason messages for the exception. To be internationalized.
     */
    public static final String RM_OK = "Everything OK";
    public static final String RM_UNEXPECTED_ERROR = "Unexpected error";
    public static final String RM_INVALID_ZONE = "The specified ZoneId is not valid";
    public static final String RM_INVALID_ZONEDDATETIME_STRING = "The specified string is not a valid ZonedDateTime string";
}
