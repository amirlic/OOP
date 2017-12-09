package levels;

import java.util.List;

/**
 * Created by Amir Lichter on 14/06/2016.
 */
public class SetLevels {
    private List<Sets> sets;

    /**
     * This function constructor SetLevels.
     *
     * @param sets is List <Sets>.
     */
    public SetLevels(List<Sets> sets) {
        this.sets = sets;
    }

    /**
     * This function return the set.
     *
     * @return the List<Sets>
     */
    public List<Sets> getSet() {
        return this.sets;
    }
}
