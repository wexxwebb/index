
package pro.kretov.repository.search.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Job {

    @SerializedName("_class")
    @Expose
    private String _class;
    @SerializedName("actions")
    @Expose
    private List<Action> actions = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("displayNameOrNull")
    @Expose
    private Object displayNameOrNull;
    @SerializedName("fullDisplayName")
    @Expose
    private String fullDisplayName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("buildable")
    @Expose
    private Boolean buildable;
    @SerializedName("builds")
    @Expose
    private List<Build> builds = null;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("firstBuild")
    @Expose
    private FirstBuild firstBuild;
    @SerializedName("healthReport")
    @Expose
    private List<HealthReport> healthReport = null;
    @SerializedName("inQueue")
    @Expose
    private Boolean inQueue;
    @SerializedName("keepDependencies")
    @Expose
    private Boolean keepDependencies;
    @SerializedName("lastBuild")
    @Expose
    private LastBuild lastBuild;
    @SerializedName("lastCompletedBuild")
    @Expose
    private LastCompletedBuild lastCompletedBuild;
    @SerializedName("lastFailedBuild")
    @Expose
    private Object lastFailedBuild;
    @SerializedName("lastStableBuild")
    @Expose
    private LastStableBuild lastStableBuild;
    @SerializedName("lastSuccessfulBuild")
    @Expose
    private LastSuccessfulBuild lastSuccessfulBuild;
    @SerializedName("lastUnstableBuild")
    @Expose
    private Object lastUnstableBuild;
    @SerializedName("lastUnsuccessfulBuild")
    @Expose
    private Object lastUnsuccessfulBuild;
    @SerializedName("nextBuildNumber")
    @Expose
    private Integer nextBuildNumber;
    @SerializedName("property")
    @Expose
    private List<Property> property = null;
    @SerializedName("queueItem")
    @Expose
    private Object queueItem;
    @SerializedName("concurrentBuild")
    @Expose
    private Boolean concurrentBuild;
    @SerializedName("downstreamProjects")
    @Expose
    private List<Object> downstreamProjects = null;
    @SerializedName("scm")
    @Expose
    private Scm scm;
    @SerializedName("upstreamProjects")
    @Expose
    private List<Object> upstreamProjects = null;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Object getDisplayNameOrNull() {
        return displayNameOrNull;
    }

    public void setDisplayNameOrNull(Object displayNameOrNull) {
        this.displayNameOrNull = displayNameOrNull;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getBuildable() {
        return buildable;
    }

    public void setBuildable(Boolean buildable) {
        this.buildable = buildable;
    }

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Build> builds) {
        this.builds = builds;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public FirstBuild getFirstBuild() {
        return firstBuild;
    }

    public void setFirstBuild(FirstBuild firstBuild) {
        this.firstBuild = firstBuild;
    }

    public List<HealthReport> getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(List<HealthReport> healthReport) {
        this.healthReport = healthReport;
    }

    public Boolean getInQueue() {
        return inQueue;
    }

    public void setInQueue(Boolean inQueue) {
        this.inQueue = inQueue;
    }

    public Boolean getKeepDependencies() {
        return keepDependencies;
    }

    public void setKeepDependencies(Boolean keepDependencies) {
        this.keepDependencies = keepDependencies;
    }

    public LastBuild getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(LastBuild lastBuild) {
        this.lastBuild = lastBuild;
    }

    public LastCompletedBuild getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public void setLastCompletedBuild(LastCompletedBuild lastCompletedBuild) {
        this.lastCompletedBuild = lastCompletedBuild;
    }

    public Object getLastFailedBuild() {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(Object lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
    }

    public LastStableBuild getLastStableBuild() {
        return lastStableBuild;
    }

    public void setLastStableBuild(LastStableBuild lastStableBuild) {
        this.lastStableBuild = lastStableBuild;
    }

    public LastSuccessfulBuild getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(LastSuccessfulBuild lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public Object getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public void setLastUnstableBuild(Object lastUnstableBuild) {
        this.lastUnstableBuild = lastUnstableBuild;
    }

    public Object getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    public void setLastUnsuccessfulBuild(Object lastUnsuccessfulBuild) {
        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
    }

    public Integer getNextBuildNumber() {
        return nextBuildNumber;
    }

    public void setNextBuildNumber(Integer nextBuildNumber) {
        this.nextBuildNumber = nextBuildNumber;
    }

    public List<Property> getProperty() {
        return property;
    }

    public void setProperty(List<Property> property) {
        this.property = property;
    }

    public Object getQueueItem() {
        return queueItem;
    }

    public void setQueueItem(Object queueItem) {
        this.queueItem = queueItem;
    }

    public Boolean getConcurrentBuild() {
        return concurrentBuild;
    }

    public void setConcurrentBuild(Boolean concurrentBuild) {
        this.concurrentBuild = concurrentBuild;
    }

    public List<Object> getDownstreamProjects() {
        return downstreamProjects;
    }

    public void setDownstreamProjects(List<Object> downstreamProjects) {
        this.downstreamProjects = downstreamProjects;
    }

    public Scm getScm() {
        return scm;
    }

    public void setScm(Scm scm) {
        this.scm = scm;
    }

    public List<Object> getUpstreamProjects() {
        return upstreamProjects;
    }

    public void setUpstreamProjects(List<Object> upstreamProjects) {
        this.upstreamProjects = upstreamProjects;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("_class", _class).append("actions", actions).append("description", description).append("displayName", displayName).append("displayNameOrNull", displayNameOrNull).append("fullDisplayName", fullDisplayName).append("fullName", fullName).append("name", name).append("url", url).append("buildable", buildable).append("builds", builds).append("color", color).append("firstBuild", firstBuild).append("healthReport", healthReport).append("inQueue", inQueue).append("keepDependencies", keepDependencies).append("lastBuild", lastBuild).append("lastCompletedBuild", lastCompletedBuild).append("lastFailedBuild", lastFailedBuild).append("lastStableBuild", lastStableBuild).append("lastSuccessfulBuild", lastSuccessfulBuild).append("lastUnstableBuild", lastUnstableBuild).append("lastUnsuccessfulBuild", lastUnsuccessfulBuild).append("nextBuildNumber", nextBuildNumber).append("property", property).append("queueItem", queueItem).append("concurrentBuild", concurrentBuild).append("downstreamProjects", downstreamProjects).append("scm", scm).append("upstreamProjects", upstreamProjects).toString();
    }

}
