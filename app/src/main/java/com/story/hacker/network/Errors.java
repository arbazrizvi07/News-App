package com.story.hacker.network;

import java.util.List;

/**
 * Error class to handle Retrofit Custom Errors
 * @author Arbaz Rizvi
 */
public class Errors {

    private List<Error> errors;


    public Errors() {
    }

    public List<Error> getErrors() {
        return errors;
    }

    public Errors(List<Error> errors) {
        this.errors = errors;
    }

    public Error getError() {
        return errors.get(0);
    }

    public static class Error {
        private String message;
        private String source;

        private Error() {
        }

        public Error(String message, String source) {
            this.message = message;
            this.source = source;
        }

        public String getMessage() {
            return message;
        }

        public String getSource() {
            return source;
        }
    }
}
