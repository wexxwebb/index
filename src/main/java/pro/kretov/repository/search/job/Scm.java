
package pro.kretov.repository.search.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Scm {

    @SerializedName("_class")
    @Expose
    private String _class;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("_class", _class).toString();
    }

}
