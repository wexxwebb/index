
package pro.kretov.repository.search.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HealthReport {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("iconClassName")
    @Expose
    private String iconClassName;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("score")
    @Expose
    private Integer score;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconClassName() {
        return iconClassName;
    }

    public void setIconClassName(String iconClassName) {
        this.iconClassName = iconClassName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("description", description).append("iconClassName", iconClassName).append("iconUrl", iconUrl).append("score", score).toString();
    }

}
