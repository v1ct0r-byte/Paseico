package app.paseico.data;


import java.util.List;

public class Route {
    private String name;
    private List<PointOfInterest> pointsOfInterest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void setPointsOfInterest(List<PointOfInterest> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }
}
