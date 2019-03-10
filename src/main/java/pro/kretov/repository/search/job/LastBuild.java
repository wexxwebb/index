
package pro.kretov.repository.search.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LastBuild {

    @SerializedName("_class")
    @Expose
    private String _class;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("url")
    @Expose
    private String url;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("_class", _class).append("number", number).append("url", url).toString();
    }

}
