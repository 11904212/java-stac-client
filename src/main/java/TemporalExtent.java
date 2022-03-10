import java.time.LocalDateTime;
import java.util.List;

public interface TemporalExtent {
    List<List<LocalDateTime>> getInterval();
}
