package bw.ac.biust.csis.st17001132.comp341.CheckPermit;

public class PermitObject {

    private String userId;
    private String departureZone;
    private String departureLocation;
    private String destinationZone;
    private String destinationLocation;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public PermitObject(String userId, String departureZone, String departureLocation, String destinationZone, String destinationLocation,String status) {
        this.userId = userId;
        this.departureZone = departureZone;
        this.departureLocation = departureLocation;
        this.destinationZone = destinationZone;
        this.destinationLocation = destinationLocation;
        this.status=status;
    }


    public PermitObject() {

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartureZone() {
        return departureZone;
    }

    public void setDepartureZone(String departureZone) {
        this.departureZone = departureZone;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationZone() {
        return destinationZone;
    }

    public void setDestinationZone(String destinationZone) {
        this.destinationZone = destinationZone;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }




}
