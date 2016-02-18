package com.ga.hive.domain.util;

/**
 * The Response class will contain data which will be sent to the front end processor in terms of JSON object.
 * Controller will convert this object to the JASON.
 * 
 * @author knilay
 */
public class Response {

    private Integer status;
    private String message;
    private Object data;

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status the new status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the message which will contain the response status message as String.
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message to the processor as success of failure.
     * 
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the response data.
     * 
     * @return the response data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the response data.
     * 
     * @param data the new response data
     */
    public void setData(Object data) {
        this.data = data;
    }
}