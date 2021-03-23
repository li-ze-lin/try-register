package cn.lzl.centre;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class ServiceData {

    private String name;
    private String ip;
    private int port;
    private String healthUrl;
    @Setter(AccessLevel.NONE)
    private State state;

    public void up() {
        this.state = State.UP;
    }

    public void down() {
        this.state = State.DOWN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceData that = (ServiceData) o;
        return port == that.port &&
                Objects.equals(name, that.name) &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ip, port);
    }

    public enum State {
        UP,
        DOWN
    }
}
