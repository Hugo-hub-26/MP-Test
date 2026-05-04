
package domain;

import java.io.Serializable;

/**
 *
 * @author Hugo Martínez González
 */
public interface Minion extends Serializable {
     String getName();
     int getHealth();
}
