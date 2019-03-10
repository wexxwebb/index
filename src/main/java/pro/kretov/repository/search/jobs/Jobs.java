
package pro.kretov.repository.search.jobs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Jobs {

    @SerializedName("_class")
    @Expose
    private String _class;
    @SerializedName("assignedLabels")
    @Expose
    private List<AssignedLabel> assignedLabels = null;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("nodeDescription")
    @Expose
    private String nodeDescription;
    @SerializedName("nodeName")
    @Expose
    private String nodeName;
    @SerializedName("numExecutors")
    @Expose
    private Integer numExecutors;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("jobs")
    @Expose
    private List<Job> jobs = null;
    @SerializedName("overallLoad")
    @Expose
    private OverallLoad overallLoad;
    @SerializedName("primaryView")
    @Expose
    private PrimaryView primaryView;
    @SerializedName("quietingDown")
    @Expose
    private Boolean quietingDown;
    @SerializedName("slaveAgentPort")
    @Expose
    private Integer slaveAgentPort;
    @SerializedName("unlabeledLoad")
    @Expose
    private UnlabeledLoad unlabeledLoad;
    @SerializedName("useCrumbs")
    @Expose
    private Boolean useCrumbs;
    @SerializedName("useSecurity")
    @Expose
    private Boolean useSecurity;
    @SerializedName("views")
    @Expose
    private List<View> views = null;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public List<AssignedLabel> getAssignedLabels() {
        return assignedLabels;
    }

    public void setAssignedLabels(List<AssignedLabel> assignedLabels) {
        this.assignedLabels = assignedLabels;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNodeDescription() {
        return nodeDescription;
    }

    public void setNodeDescription(String nodeDescription) {
        this.nodeDescription = nodeDescription;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getNumExecutors() {
        return numExecutors;
    }

    public void setNumExecutors(Integer numExecutors) {
        this.numExecutors = numExecutors;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public OverallLoad getOverallLoad() {
        return overallLoad;
    }

    public void setOverallLoad(OverallLoad overallLoad) {
        this.overallLoad = overallLoad;
    }

    public PrimaryView getPrimaryView() {
        return primaryView;
    }

    public void setPrimaryView(PrimaryView primaryView) {
        this.primaryView = primaryView;
    }

    public Boolean getQuietingDown() {
        return quietingDown;
    }

    public void setQuietingDown(Boolean quietingDown) {
        this.quietingDown = quietingDown;
    }

    public Integer getSlaveAgentPort() {
        return slaveAgentPort;
    }

    public void setSlaveAgentPort(Integer slaveAgentPort) {
        this.slaveAgentPort = slaveAgentPort;
    }

    public UnlabeledLoad getUnlabeledLoad() {
        return unlabeledLoad;
    }

    public void setUnlabeledLoad(UnlabeledLoad unlabeledLoad) {
        this.unlabeledLoad = unlabeledLoad;
    }

    public Boolean getUseCrumbs() {
        return useCrumbs;
    }

    public void setUseCrumbs(Boolean useCrumbs) {
        this.useCrumbs = useCrumbs;
    }

    public Boolean getUseSecurity() {
        return useSecurity;
    }

    public void setUseSecurity(Boolean useSecurity) {
        this.useSecurity = useSecurity;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("_class", _class).append("assignedLabels", assignedLabels).append("mode", mode).append("nodeDescription", nodeDescription).append("nodeName", nodeName).append("numExecutors", numExecutors).append("description", description).append("jobs", jobs).append("overallLoad", overallLoad).append("primaryView", primaryView).append("quietingDown", quietingDown).append("slaveAgentPort", slaveAgentPort).append("unlabeledLoad", unlabeledLoad).append("useCrumbs", useCrumbs).append("useSecurity", useSecurity).append("views", views).toString();
    }

}
